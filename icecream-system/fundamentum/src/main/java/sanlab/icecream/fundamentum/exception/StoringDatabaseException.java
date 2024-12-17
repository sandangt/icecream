package sanlab.icecream.fundamentum.exception;

import java.io.Serial;

public class StoringDatabaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6252192257123585505L;

    public StoringDatabaseException(String message) {
        super(message);
    }

}
