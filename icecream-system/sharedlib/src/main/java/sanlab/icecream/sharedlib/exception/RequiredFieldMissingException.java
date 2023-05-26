package sanlab.icecream.sharedlib.exception;

import lombok.Getter;


@Getter
public class RequiredFieldMissingException extends RuntimeException {
    private final Integer errorCode;
    public RequiredFieldMissingException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public Integer getErrorCode() {
        return this.errorCode;
    }
}
