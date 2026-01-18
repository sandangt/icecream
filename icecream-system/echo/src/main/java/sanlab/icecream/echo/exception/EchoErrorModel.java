package sanlab.icecream.echo.exception;

import lombok.Getter;
import sanlab.icecream.fundamentum.exception.IcErrorModel;

@Getter
public enum EchoErrorModel implements IcErrorModel {
    SERVICE_SUCCESSFUL_PAYMENT_PAYLOAD_INVALID("ECHO-1000", "Invalid payload from successful payment message", 770),

    MAIL_SEND_EMAIL_FAILED("ECHO-2000", "Failed to send email", -3),
    ;

    private final String code;
    private final String msg;
    private final int rSocketCode;

    EchoErrorModel(String code, String msg, int rSocketCode) {
        this.code = code;
        this.msg = msg;
        this.rSocketCode = rSocketCode;
    }

}
