package sanlab.icecream.gateway.exception.controller;

public class InternalError extends RuntimeException {
    private final Integer errorCode;
    public InternalError(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public Integer getErrorCode() {
        return this.errorCode;
    }
}
