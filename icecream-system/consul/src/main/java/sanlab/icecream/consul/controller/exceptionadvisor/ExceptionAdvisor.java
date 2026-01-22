package sanlab.icecream.consul.controller.exceptionadvisor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sanlab.icecream.consul.exception.HttpBadRequestException;
import sanlab.icecream.consul.exception.HttpForbiddenException;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.exception.HttpUnauthorizedException;
import sanlab.icecream.fundamentum.contractmodel.response.ErrorResponse;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class ExceptionAdvisor {

    //region Custom exceptions
    @ExceptionHandler({HttpNotFoundException.class})
    public ResponseEntity<ErrorResponse> notFound(HttpNotFoundException ex) {
        var innerEx = ex.getInnerEx();
        return new ResponseEntity<>(new ErrorResponse(innerEx.code(), innerEx.message()), NOT_FOUND);
    }

    @ExceptionHandler({HttpBadRequestException.class})
    public ResponseEntity<ErrorResponse> badRequest(HttpBadRequestException ex) {
        var innerEx = ex.getInnerEx();
        return new ResponseEntity<>(new ErrorResponse(innerEx.code(), innerEx.message()), BAD_REQUEST);
    }

    @ExceptionHandler({HttpUnauthorizedException.class})
    public ResponseEntity<ErrorResponse> unauthorized(HttpUnauthorizedException ex) {
        var innerEx = ex.getInnerEx();
        return new ResponseEntity<>(new ErrorResponse(innerEx.code(), innerEx.message()), UNAUTHORIZED);
    }

    @ExceptionHandler({HttpForbiddenException.class})
    public ResponseEntity<ErrorResponse> forbidden(HttpForbiddenException ex) {
        var innerEx = ex.getInnerEx();
        return new ResponseEntity<>(new ErrorResponse(innerEx.code(), innerEx.message()), FORBIDDEN);
    }

    @ExceptionHandler({HttpInternalServerErrorException.class})
    public ResponseEntity<ErrorResponse> internalServerError(HttpInternalServerErrorException ex) {
        var innerEx = ex.getInnerEx();
        return new ResponseEntity<>(new ErrorResponse(innerEx.code(), innerEx.message()), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpServiceUnavailableException.class})
    public ResponseEntity<ErrorResponse> serviceUnavailable(HttpServiceUnavailableException ex) {
        var innerEx = ex.getInnerEx();
        return new ResponseEntity<>(new ErrorResponse(innerEx.code(), innerEx.message()), SERVICE_UNAVAILABLE);
    }
    //endregion

    //region Lib exceptions
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> validationError(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ErrorResponse(StringUtils.EMPTY, ex.getMessage()), BAD_REQUEST);
    }
    //endregion

}
