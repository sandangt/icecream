package sanlab.icecream.fundamentum.exception;

import java.io.Serial;

public class StoringImageException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6252192257123585505L;

    private static final String STORING_IMAGE_ERROR_MSG = "Error processing to store image";

    public StoringImageException() {
        super(STORING_IMAGE_ERROR_MSG);
    }

}
