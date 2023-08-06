package sanlab.icecream.sharedlib.exception;


public class ItemNotFoundException extends RuntimeException {
    private final Integer errorCode;
    public ItemNotFoundException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public Integer getErrorCode() {
        return this.errorCode;
    }
}
