package sanlab.icecream.consul.exception;

import sanlab.icecream.fundamentum.exception.IcErrorModel;

public enum ConsulErrorModel implements IcErrorModel {

    //region Security layer
    INVALID_USER_PRINCIPAL("CONSUL-0000", "Invalid user principal"),
    //region

    //region Repository layer
    CUSTOMER_NOT_FOUND("CONSUL-2000", "Customer not found"),
    PRODUCT_NOT_FOUND("CONSUL-2001", "Product not found"),
    CATEGORY_NOT_FOUND("CONSUL-2002", "Category not found"),
    STOCK_NOT_FOUND("CONSUL-2003", "Stock not found"),
    FEEDBACK_NOT_FOUND("CONSUL-2004", "Feedback not found"),
    ADDRESS_NOT_FOUND("CONSUL-2005", "Address not found"),
    CART_NOT_FOUND("CONSUL_2006", "Cart not found"),
    FAIL_TO_STORE_IMAGE_FILE("CONSUL-2100", "Failed to store image file"),
    FAIL_TO_PERSIST_DATA("CONSUL-2101", "Error occurs when persisting data"),
    //endregion

    //region External service identity
    INVALID_IDENTITY_ADMIN_TOKEN("CONSUL-3000", "Failed to get admin token"),
    INVALID_UPDATE_USER_INFO_REQUEST("CONSUL-3001", "Invalid request for updating user info"),
    UNAVAILABLE_IDENTITY_SERVICE("CONSUL-3002", "Identity service currently not available"),
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
