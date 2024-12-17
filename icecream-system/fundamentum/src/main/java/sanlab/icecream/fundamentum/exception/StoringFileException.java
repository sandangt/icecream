package sanlab.icecream.fundamentum.exception;

import java.io.Serial;

public class StoringFileException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public StoringFileException(String message) {
        super(message);
    }

}
