package sanlab.icecream.consul.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.consul.utils.CollectionQueryUtils;
import sanlab.icecream.fundamentum.dto.core.FeedbackDto;
import sanlab.icecream.fundamentum.dto.exntended.FeedbackExtendedDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.service.FeedbackService;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest;
import sanlab.icecream.fundamentum.contractmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PERSIST_DATA_FAILED;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_FEEDBACK_NOT_FOUND;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public CollectionQueryResponse<FeedbackExtendedDto> getAll(@ModelAttribute CollectionQueryRequest request) {
        return feedbackService.getAll(CollectionQueryUtils.getPageRequest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackExtendedDto> getById(@PathVariable UUID id) {
        try {
            var result = feedbackService.getById(id);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (REPOSITORY_FEEDBACK_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
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
                case REPOSITORY_FEEDBACK_NOT_FOUND -> new HttpNotFoundException(ex);
                case REPOSITORY_PERSIST_DATA_FAILED -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

}
