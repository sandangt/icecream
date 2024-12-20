package sanlab.icecream.frontier.controller.watcher;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.frontier.dto.extended.FeedbackExtendedDto;
import sanlab.icecream.frontier.service.FeedbackService;
import sanlab.icecream.frontier.viewmodel.request.CollectionQueryRequest;
import sanlab.icecream.frontier.viewmodel.response.CollectionQueryResponse;

import java.util.UUID;

import static sanlab.icecream.fundamentum.constant.PreAuthorizedAuthExp.WATCHER;

@RestController
@RequestMapping("/watcher/feedbacks")
@PreAuthorize(WATCHER)
@RequiredArgsConstructor
public class WatchFeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public CollectionQueryResponse<FeedbackExtendedDto> getAll(@ModelAttribute CollectionQueryRequest request) {
        return feedbackService.getAll(request.getPageRequest());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackExtendedDto> getById(@PathVariable UUID id) {
        var result = feedbackService.getById(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FeedbackExtendedDto> delete(@PathVariable UUID id) {
        var result = feedbackService.delete(id);
        return ResponseEntity.ok(result);
    }

}
