package sanlab.icecream.frontier.exception;

import java.io.Serial;

public class MalfunctionMultipartFileException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public MalfunctionMultipartFileException(String message) {
        super(message);
    }

}
