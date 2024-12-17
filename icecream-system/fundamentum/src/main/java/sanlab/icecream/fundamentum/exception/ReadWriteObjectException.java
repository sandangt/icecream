package sanlab.icecream.fundamentum.exception;

import java.io.Serial;

public class ReadWriteObjectException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ReadWriteObjectException(String message) {
        super(message);
    }

}
