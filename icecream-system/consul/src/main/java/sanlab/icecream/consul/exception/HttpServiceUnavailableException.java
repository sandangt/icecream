package sanlab.icecream.consul.exception;

import lombok.Getter;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

@Getter
public class HttpServiceUnavailableException extends RuntimeException {

    private final IcRuntimeException innerEx;

    public HttpServiceUnavailableException(IcRuntimeException ex) {
        super(ex);
        innerEx = ex;
    }

}
