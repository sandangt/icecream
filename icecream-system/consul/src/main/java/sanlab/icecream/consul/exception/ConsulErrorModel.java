package sanlab.icecream.consul.exception;

import sanlab.icecream.fundamentum.exception.IcErrorModel;

public enum ConsulErrorModel implements IcErrorModel {

    //region Security layer
    INVALID_USER_PRINCIPAL("FT0000", "Invalid user principal"),
    //region

    //region Repository layer
    CUSTOMER_NOT_FOUND("FT2000", "Customer not found"),
    PRODUCT_NOT_FOUND("FT2001", "Product not found"),
    CATEGORY_NOT_FOUND("FT2002", "Category not found"),
    STOCK_NOT_FOUND("FT2003", "Stock not found"),
    FEEDBACK_NOT_FOUND("FT2004", "Feedback not found"),
    ADDRESS_NOT_FOUND("FT2005", "Address not found"),
    FAIL_TO_STORE_IMAGE_FILE("FT2100", "Failed to store image file"),
    FAIL_TO_PERSIST_DATA("FT2101", "Error occurs when persisting data"),
    //endregion

    //region External service identity
    INVALID_IDENTITY_ADMIN_TOKEN("FT3000", "Failed to get admin token"),
    INVALID_UPDATE_USER_INFO_REQUEST("FT3001", "Invalid request for updating user info"),
    UNAVAILABLE_IDENTITY_SERVICE("FT3002", "Identity service currently not available"),
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
