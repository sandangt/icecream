package sanlab.icecream.consul.internalcontroller.exceptionadvisor;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import reactor.core.publisher.Mono;
import sanlab.icecream.consul.exception.HttpBadRequestException;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.exception.HttpUnauthorizedException;
import sanlab.icecream.fundamentum.contractmodel.response.ErrorResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import static sanlab.icecream.consul.exception.ConsulErrorModel.BAD_INPUT_REQUEST;

public interface RSocketExceptionAdvisorMixin {

    //region Custom exceptions
    @MessageExceptionHandler
    default Mono<ErrorResponse> notFound(HttpNotFoundException ex) {
        return composeErrorResponse(ex.getInnerEx());
    }

    @MessageExceptionHandler
    default Mono<ErrorResponse> badRequest(HttpBadRequestException ex) {
        return composeErrorResponse(ex.getInnerEx());
    }

    @MessageExceptionHandler
    default Mono<ErrorResponse> unauthorized(HttpUnauthorizedException ex) {
        return composeErrorResponse(ex.getInnerEx());
    }

    @MessageExceptionHandler
    default Mono<ErrorResponse> internalServerError(HttpInternalServerErrorException ex) {
        return composeErrorResponse(ex.getInnerEx());
    }

    @MessageExceptionHandler
    default Mono<ErrorResponse> serviceUnavailable(HttpServiceUnavailableException ex) {
        return composeErrorResponse(ex.getInnerEx());
    }
    //endregion

    //region Lib exceptions
    @MessageExceptionHandler
    default Mono<ErrorResponse> validationError(MethodArgumentNotValidException ex) {
        return Mono.just(new ErrorResponse(BAD_INPUT_REQUEST.getCode(), ex.getMessage()));
    }
    //endregion

    private static Mono<ErrorResponse> composeErrorResponse(IcRuntimeException ex) {
        return Mono.error(ex.toRSocketException());
    }

}
