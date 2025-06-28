package sanlab.icecream.consul.exception;

import sanlab.icecream.fundamentum.exception.IcErrorModel;

public enum ConsulErrorModel implements IcErrorModel {

    //region Security layer
    INVALID_USER_PRINCIPAL("FT0000", "Invalid user principal"),
    //region

    //region IO
    //endregion

    //region Repository layer
    CUSTOMER_NOT_FOUND("FT2000", "Customer not found"),
    PRODUCT_NOT_FOUND("FT2001", "Product not found"),
    CATEGORY_NOT_FOUND("FT2002", "Category not found"),
    STOCK_NOT_FOUND("FT2003", "Stock not found"),
    FEEDBACK_NOT_FOUND("FT2004", "Feedback not found"),
    FAIL_TO_STORE_IMAGE_FILE("FT2100", "Failed to store image file"),
    FAIL_TO_PERSIST_DATA("FT2101", "Error occurs when persisting data"),
    //endregion
    ;

    private final String code;
    private final String msg;

    ConsulErrorModel(String code, String msg) {
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
