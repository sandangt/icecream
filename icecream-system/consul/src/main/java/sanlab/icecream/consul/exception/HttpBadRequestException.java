package sanlab.icecream.consul.exception;

import lombok.Getter;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

@Getter
public class HttpBadRequestException extends RuntimeException {

    private final IcRuntimeException innerEx;

    public HttpBadRequestException(IcRuntimeException ex) {
        super(ex);
        innerEx = ex;
    }

}
