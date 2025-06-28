package sanlab.icecream.consul.exception;

import lombok.Getter;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

@Getter
public class HttpInternalServerErrorException extends RuntimeException {

    private final IcRuntimeException innerEx;

    public HttpInternalServerErrorException(IcRuntimeException ex) {
        super(ex);
        innerEx = ex;
    }

}
