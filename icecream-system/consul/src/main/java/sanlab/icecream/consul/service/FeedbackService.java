package sanlab.icecream.consul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanlab.icecream.consul.model.Customer;
import sanlab.icecream.consul.model.Product;
import sanlab.icecream.consul.repository.crud.CustomerRepository;
import sanlab.icecream.consul.repository.crud.ProductRepository;
import sanlab.icecream.fundamentum.dto.aggregated.FeedbackStatDto;
import sanlab.icecream.fundamentum.dto.core.FeedbackDto;
import sanlab.icecream.fundamentum.dto.exntended.FeedbackExtendedDto;
import sanlab.icecream.consul.mapper.FeedbackMapper;
import sanlab.icecream.consul.model.Feedback;
import sanlab.icecream.consul.repository.crud.FeedbackRepository;
import sanlab.icecream.fundamentum.contractmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_CUSTOMER_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PERSIST_DATA_FAILED;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_FEEDBACK_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PRODUCT_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.SERVICE_UPDATE_FEEDBACK_FORBIDDEN;
import static sanlab.icecream.fundamentum.utils.ObjectUtils.copyNotNull;
import static sanlab.icecream.fundamentum.utils.RequestUtils.calculateTotalPage;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    private final FeedbackMapper feedbackMapper;

    @Transactional(readOnly = true)
    public CollectionQueryResponse<FeedbackExtendedDto> getAll(Pageable pageable) {
        Page<Feedback> paginatedFeedbacks = feedbackRepository.findAll(pageable);
        long total = feedbackRepository.count();
        List<FeedbackExtendedDto> feedbackList = feedbackMapper.entityToExtendedDto(
            paginatedFeedbacks.stream().toList());
        return CollectionQueryResponse.<FeedbackExtendedDto>builder()
            .total(total)
            .page(pageable.getPageNumber())
            .totalPages(calculateTotalPage(total, pageable.getPageSize()))
            .data(feedbackList)
            .build();
    }

    @Transactional(readOnly = true)
    public CollectionQueryResponse<FeedbackExtendedDto> getAllFeedbacks(UUID productId, Pageable pageable) {
        if (!productRepository.existsById(productId)) {
            throw new IcRuntimeException(REPOSITORY_PRODUCT_NOT_FOUND, "id: %s".formatted(productId));
        }
        var feedbackPage = feedbackRepository.findAllByProduct_Id(productId, pageable);
        return CollectionQueryResponse.<FeedbackExtendedDto>builder()
            .total(feedbackPage.getTotalElements())
            .page(feedbackPage.getNumber())
            .totalPages(feedbackPage.getTotalPages())
            .data(feedbackMapper.entityToExtendedDto(feedbackPage.getContent()))
            .build();
    }

    @Transactional(readOnly = true)
    public FeedbackStatDto getFeedbackStatByProductId(UUID productId) {
        if (!productRepository.existsById(productId)) {
            throw new IcRuntimeException(REPOSITORY_PRODUCT_NOT_FOUND, "id: %s".formatted(productId));
        }
        Long total = feedbackRepository.countByProduct_Id(productId);
        Double averageStar = feedbackRepository.averageStarByProduct_Id(productId);
        return new FeedbackStatDto(productId, total, averageStar);
    }

    @Transactional(readOnly = true)
    public FeedbackExtendedDto getById(UUID id) {
        return feedbackRepository.findById(id)
            .map(feedbackMapper::entityToExtendedDto)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_FEEDBACK_NOT_FOUND, "id: %s".formatted(id)));
    }

    @Transactional
    public FeedbackExtendedDto create(UUID userId, UUID productId, FeedbackDto request) {
        Customer customer = customerRepository.findFirstByUserId(userId)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_CUSTOMER_NOT_FOUND, "id: %s".formatted(userId)));;
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_PRODUCT_NOT_FOUND, "id: %s".formatted(productId)));
        Feedback feedback = Feedback.builder()
            .star(request.getStar())
            .content(request.getContent())
            .product(product)
            .customer(customer)
            .build();
        try {
            Feedback savedFeedback = feedbackRepository.save(feedback);
            return feedbackMapper.entityToExtendedDto(savedFeedback);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "feedback");
        }
    }

    @Transactional
    public FeedbackExtendedDto update(UUID userId, FeedbackDto request) {
        Feedback targetFeedback = feedbackRepository.findById(request.getId())
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_FEEDBACK_NOT_FOUND, "id: %s".formatted(request.getId())));
        Customer customer = targetFeedback.getCustomer();
        if (!Objects.equals(customer.getUserId(), userId)) {
            throw new IcRuntimeException(SERVICE_UPDATE_FEEDBACK_FORBIDDEN, null,
                "Feedback ID: %s".formatted(targetFeedback.getId()),
                "Customer User ID: %s".formatted(customer.getUserId())
            );
        }
        try {
            Feedback sourceFeedback = feedbackMapper.dtoToEntity(request);
            copyNotNull(sourceFeedback, targetFeedback);
            return feedbackMapper.entityToExtendedDto(feedbackRepository.save(targetFeedback));
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "feedback");
        }
    }

    @Transactional
    public FeedbackExtendedDto delete(UUID userId, UUID id, UUID feedbackId) {
        if (!customerRepository.existsByUserId(userId)) {
            throw new IcRuntimeException(REPOSITORY_CUSTOMER_NOT_FOUND, "id: %s".formatted(userId));
        }
        if (!productRepository.existsById(id)) {
            throw new IcRuntimeException(REPOSITORY_PRODUCT_NOT_FOUND, "id: %s".formatted(id));
        }
        Feedback feedback = feedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_FEEDBACK_NOT_FOUND, "id: %s".formatted(id)));
        Customer customer = feedback.getCustomer();
        if (!Objects.equals(customer.getUserId(), userId)) {
            throw new IcRuntimeException(SERVICE_UPDATE_FEEDBACK_FORBIDDEN, null,
                "Feedback ID: %s".formatted(feedback.getId()),
                "Customer User ID: %s".formatted(userId)
            );
        }
        try {
            feedbackRepository.deleteById(feedbackId);
            return feedbackMapper.entityToExtendedDto(feedback);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "feedback");
        }
    }
}
