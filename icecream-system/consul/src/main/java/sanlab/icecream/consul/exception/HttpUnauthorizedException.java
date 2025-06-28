package sanlab.icecream.consul.exception;

import lombok.Getter;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

@Getter
public class HttpUnauthorizedException extends RuntimeException {

    private final IcRuntimeException innerEx;

    public HttpUnauthorizedException(IcRuntimeException ex) {
        super(ex);
        innerEx = ex;
    }

}
