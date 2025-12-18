package sanlab.icecream.consul.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.fundamentum.dto.exntended.FeedbackExtendedDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.service.FeedbackService;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest;
import sanlab.icecream.fundamentum.contractmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_PERSIST_DATA;
import static sanlab.icecream.consul.exception.ConsulErrorModel.FEEDBACK_NOT_FOUND;
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
        try {
            var result = feedbackService.getById(id);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (FEEDBACK_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FeedbackExtendedDto> delete(@PathVariable UUID id) {
        try {
            var result = feedbackService.delete(id);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case FEEDBACK_NOT_FOUND -> throw new HttpNotFoundException(ex);
                case FAIL_TO_PERSIST_DATA -> throw new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

}
