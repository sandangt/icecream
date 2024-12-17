package sanlab.icecream.frontier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sanlab.icecream.frontier.dto.core.FeedbackDto;
import sanlab.icecream.frontier.dto.extended.FeedbackExtendedDto;
import sanlab.icecream.fundamentum.exception.ItemNotFoundException;
import sanlab.icecream.fundamentum.exception.StoringDatabaseException;
import sanlab.icecream.frontier.mapper.IFeedbackMapper;
import sanlab.icecream.frontier.model.Feedback;
import sanlab.icecream.frontier.repository.crud.IFeedbackRepository;
import sanlab.icecream.frontier.viewmodel.response.CollectionQueryResponse;

import java.util.List;
import java.util.UUID;

import static sanlab.icecream.fundamentum.utils.ObjectUtils.copyNotNull;
import static sanlab.icecream.fundamentum.utils.RequestUtils.calculateTotalPage;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final IFeedbackRepository feedbackRepository;

    private final IFeedbackMapper feedbackMapper;

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

    public FeedbackExtendedDto getById(UUID id) {
        return feedbackRepository.findById(id)
            .map(feedbackMapper::entityToExtendedDto)
            .orElseThrow(() -> ItemNotFoundException.stock(id));
    }

    public FeedbackExtendedDto create(FeedbackDto request) {
        try {
            Feedback feedback = feedbackRepository.save(feedbackMapper.dtoToEntity(request));
            return feedbackMapper.entityToExtendedDto(feedback);
        } catch (Exception ignore) {
            throw new StoringDatabaseException("Error occurs when creating feedback");
        }
    }

    public FeedbackExtendedDto update(UUID id, FeedbackDto request) {
        Feedback targetFeedback = feedbackRepository.findById(id)
            .orElseThrow(() -> ItemNotFoundException.stock(id));
        Feedback sourceFeedback = feedbackMapper.dtoToEntity(request);
        copyNotNull(sourceFeedback, targetFeedback);
        try {
            return feedbackMapper.entityToExtendedDto(feedbackRepository.save(targetFeedback));
        } catch (Exception ignore) {
            throw new StoringDatabaseException("Error occurs when creating feedback");
        }
    }

}
