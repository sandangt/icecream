package sanlab.icecream.sharedlib.exception;


public class IllegalValueException extends RuntimeException {
    private final Integer errorCode;
    public IllegalValueException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public Integer getErrorCode() {
        return this.errorCode;
    }
}
