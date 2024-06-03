package sanlab.icecream.frontier.exception;

import java.io.Serial;

public class ItemNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -7255509391253688046L;

    public ItemNotFoundException(String message) {
        super(message);
    }
}
