package sanlab.icecream.conflux.exception;

import lombok.Getter;
import sanlab.icecream.fundamentum.exception.IcErrorModel;

@Getter
public enum ConfluxErrorModel implements IcErrorModel {

    //region Consumer layer
    CONSUMER_PARSING_ENRICHED_PRODUCT_FAILED("CONFLUX-0000", "Failed to parse enriched product message", -1);
    //endregion
    ;

    ConfluxErrorModel(String code, String msg, int rSocketCode) {
        this.code = code;
        this.msg = msg;
        this.rSocketCode = rSocketCode;
    }

    private final String code;
    private final String msg;
    private final int rSocketCode;

}
