package sanlab.icecream.fundamentum.exception;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class IcRuntimeException extends RuntimeException {

    private final IcErrorModel error;
    private final String dynamicMsg;
    private final String[] debugLogs;
    private static final String MSG_FORMAT = "%s, %s";

    public IcRuntimeException(IcErrorModel error) {
        super(error.getMsg());
        this.error = error;
        dynamicMsg = StringUtils.EMPTY;
        debugLogs = new String[]{};
    }

    public IcRuntimeException(Throwable throwable, IcErrorModel error) {
        super(error.getMsg(), throwable);
        this.error = error;
        dynamicMsg = StringUtils.EMPTY;
        debugLogs = new String[]{};
    }

    public IcRuntimeException(IcErrorModel error, String msg) {
        super(error.getMsg());
        this.error = error;
        dynamicMsg = msg;
        debugLogs = new String[]{};
    }

    public IcRuntimeException(Throwable throwable, IcErrorModel error, String msg) {
        super(error.getMsg(), throwable);
        this.error = error;
        dynamicMsg = msg;
        debugLogs = new String[]{};
    }

    public IcRuntimeException(IcErrorModel error, String msg, String... args) {
        super(error.getMsg());
        this.error = error;
        dynamicMsg = msg;
        debugLogs = args;
    }

    public IcRuntimeException(Throwable throwable, IcErrorModel error, String msg, String... args) {
        super(error.getMsg(), throwable);
        this.error = error;
        dynamicMsg = msg;
        debugLogs = args;
    }

    public String message() {
        return !StringUtils.isEmpty(dynamicMsg) ?
            MSG_FORMAT.formatted(error.getMsg(), dynamicMsg) :
            error.getMsg() + dynamicMsg;
    }

    public String code() {
        return error.getCode();
    }

}
