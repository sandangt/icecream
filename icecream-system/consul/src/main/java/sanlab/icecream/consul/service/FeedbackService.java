package sanlab.icecream.consul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanlab.icecream.fundamentum.dto.core.FeedbackDto;
import sanlab.icecream.fundamentum.dto.exntended.FeedbackExtendedDto;
import sanlab.icecream.consul.mapper.FeedbackMapper;
import sanlab.icecream.consul.model.Feedback;
import sanlab.icecream.consul.repository.crud.FeedbackRepository;
import sanlab.icecream.consul.viewmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.List;
import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_PERSIST_DATA;
import static sanlab.icecream.consul.exception.ConsulErrorModel.FEEDBACK_NOT_FOUND;
import static sanlab.icecream.fundamentum.utils.ObjectUtils.copyNotNull;
import static sanlab.icecream.fundamentum.utils.RequestUtils.calculateTotalPage;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

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
    public FeedbackExtendedDto getById(UUID id) {
        return feedbackRepository.findById(id)
            .map(feedbackMapper::entityToExtendedDto)
            .orElseThrow(() -> new IcRuntimeException(FEEDBACK_NOT_FOUND, "id: %s".formatted(id)));
    }

    @Transactional
    public FeedbackExtendedDto create(FeedbackDto request) {
        try {
            Feedback feedback = feedbackRepository.save(feedbackMapper.dtoToEntity(request));
            return feedbackMapper.entityToExtendedDto(feedback);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "feedback");
        }
    }

    @Transactional
    public FeedbackExtendedDto update(UUID id, FeedbackDto request) {
        Feedback targetFeedback = feedbackRepository.findById(id)
            .orElseThrow(() -> new IcRuntimeException(FEEDBACK_NOT_FOUND, "id: %s".formatted(id)));
        try {
            Feedback sourceFeedback = feedbackMapper.dtoToEntity(request);
            copyNotNull(sourceFeedback, targetFeedback);
            return feedbackMapper.entityToExtendedDto(feedbackRepository.save(targetFeedback));
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "feedback");
        }
    }

    @Transactional
    public FeedbackExtendedDto delete(UUID id) {
        Feedback feedback = feedbackRepository.findById(id)
            .orElseThrow(() -> new IcRuntimeException(FEEDBACK_NOT_FOUND, "id: %s".formatted(id)));
        try {
            feedbackRepository.deleteById(id);
            return feedbackMapper.entityToExtendedDto(feedback);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "feedback");
        }
    }

}
