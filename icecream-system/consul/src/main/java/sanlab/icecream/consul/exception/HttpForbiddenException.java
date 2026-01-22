package sanlab.icecream.consul.exception;

import lombok.Getter;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

@Getter
public class HttpForbiddenException extends RuntimeException {

    private final IcRuntimeException innerEx;

    public HttpForbiddenException(IcRuntimeException ex) {
        super(ex);
        innerEx = ex;
    }

}
