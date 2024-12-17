package sanlab.icecream.frontier.exception.advisor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sanlab.icecream.fundamentum.exception.ItemNotFoundException;
import sanlab.icecream.fundamentum.exception.ReadWriteObjectException;
import sanlab.icecream.fundamentum.exception.StoringDatabaseException;
import sanlab.icecream.frontier.viewmodel.response.ErrorResponse;

@RestControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<ErrorResponse> itemNotFound(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> badRequest(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StoringDatabaseException.class, ReadWriteObjectException.class})
    public ResponseEntity<ErrorResponse> storingDatabase(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
