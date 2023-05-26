package sanlab.icecream.gateway.exception.controller;

public class BadRequest extends RuntimeException {
    private final Integer errorCode;
    public BadRequest(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public Integer getErrorCode() {
        return this.errorCode;
    }
}
