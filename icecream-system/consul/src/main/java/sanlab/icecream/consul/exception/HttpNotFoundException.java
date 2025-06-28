package sanlab.icecream.consul.exception;

import lombok.Getter;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

@Getter
public class HttpNotFoundException extends RuntimeException {

    private final IcRuntimeException innerEx;

    public HttpNotFoundException(IcRuntimeException ex) {
        super(ex);
        innerEx = ex;
    }

}
