package sanlab.icecream.frontier.exception.advisor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sanlab.icecream.frontier.exception.ItemNotFoundException;
import sanlab.icecream.frontier.exception.StoringDatabaseException;
import sanlab.icecream.frontier.viewmodel.response.ErrorResponse;

@RestControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<ErrorResponse> itemNotFound(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({StoringDatabaseException.class})
    public ResponseEntity<ErrorResponse> storingDatabase(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
