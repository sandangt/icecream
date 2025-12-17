package sanlab.icecream.chronos.exception;

import lombok.Getter;
import sanlab.icecream.fundamentum.exception.IcErrorModel;

@Getter
public enum ChronosErrorModel implements IcErrorModel {

    //region Repository layer
    FAIL_TO_PROCESS_STORAGE_SERVICE("CHRONOS-1000", "Fail to process with storage service", 770),
    //endregion

    ;
    private final String code;
    private final String msg;
    private final int rSocketCode;

    ChronosErrorModel(String code, String msg, int rSocketCode) {
        this.code = code;
        this.msg = msg;
        this.rSocketCode = rSocketCode;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
