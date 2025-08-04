package sanlab.icecream.chronos.exception;

import sanlab.icecream.fundamentum.exception.IcErrorModel;

public enum ChronosErrorModel implements IcErrorModel {

    //region Repository layer
    FAIL_TO_PROCESS_STORAGE_SERVICE("CHRONOS-1000", "Fail to process with storage service"),
    //endregion

    ;
    private final String code;
    private final String msg;

    ChronosErrorModel(String code, String msg) {
        this.code = code;
        this.msg = msg;
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
