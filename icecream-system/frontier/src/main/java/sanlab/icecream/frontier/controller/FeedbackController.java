package sanlab.icecream.frontier.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.frontier.dto.core.FeedbackDto;
import sanlab.icecream.frontier.dto.extended.FeedbackExtendedDto;
import sanlab.icecream.frontier.service.FeedbackService;
import sanlab.icecream.frontier.viewmodel.request.CollectionQueryRequest;
import sanlab.icecream.frontier.viewmodel.response.CollectionQueryResponse;

import java.util.UUID;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

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

    @PostMapping
    public ResponseEntity<FeedbackExtendedDto> create(@Valid @RequestBody FeedbackDto requestBody) {
        var result = feedbackService.create(requestBody);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackExtendedDto> update(@PathVariable UUID id,
                                                   @Valid @RequestBody FeedbackDto requestBody) {
        var result = feedbackService.update(id, requestBody);
        return ResponseEntity.ok(result);
    }

}
