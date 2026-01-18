package sanlab.icecream.consul.exception;

import lombok.Getter;
import sanlab.icecream.fundamentum.exception.IcErrorModel;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum ConsulErrorModel implements IcErrorModel {

    //region General errors
    INTERNAL_SERVER_ERROR("CONSUL-1000", "Internal server error", -3),
    BAD_INPUT_REQUEST("CONSUL-1001", "Bad request", -4),
    //endregion

    //region Security layer
    SECURITY_USER_PRINCIPAL_INVALID("CONSUL-0000", "Invalid user principal", 770),
    SECURITY_API_KEY_INVALID("CONSUL-0001", "Invalid api key", 771),
    //region

    //region Repository layer
    REPOSITORY_CUSTOMER_NOT_FOUND("CONSUL-2000", "Customer not found", 790),
    REPOSITORY_PRODUCT_NOT_FOUND("CONSUL-2001", "Product not found", 791),
    REPOSITORY_CATEGORY_NOT_FOUND("CONSUL-2002", "Category not found", 792),
    REPOSITORY_STOCK_NOT_FOUND("CONSUL-2003", "Stock not found", 793),
    REPOSITORY_FEEDBACK_NOT_FOUND("CONSUL-2004", "Feedback not found", 794),
    REPOSITORY_ADDRESS_NOT_FOUND("CONSUL-2005", "Address not found", 795),
    REPOSITORY_CART_NOT_FOUND("CONSUL-2006", "Cart not found", 796),
    REPOSITORY_IMAGE_NOT_FOUND("CONSUL-2007", "Image not found", 797),
    REPOSITORY_PAYMENT_NOT_FOUND("CONSUL-2008", "Payment ID not found", 798),
    REPOSITORY_STORE_IMAGE_FAILED("CONSUL-2009", "Failed to store image file", 799),
    REPOSITORY_PERSIST_DATA_FAILED("CONSUL-2010", "Error occurs when persisting data", 800),
    //endregion

    //region Service layer
    //endregion

    //region External service identity
    IDENTITY_IDENTITY_ADMIN_TOKEN_FAILED("CONSUL-3000", "Failed to get admin token", 810),
    IDENTITY_UPDATE_USER_INFO_REQUEST_FAILED("CONSUL-3001", "Invalid request for updating user info", 811),
    IDENTITY_IDENTITY_SERVICE_UNAVAILABLE("CONSUL-3002", "Identity service currently not available", 812),
    //endregion

    //region External service payment
    PAYMENT_REQUEST_INVOICE_FAILED("CONSUL-4000", "Failed to create invoice", 830);
    //endregion

    ;

    private static final Map<Integer, ConsulErrorModel> MAP_BY_RSOCKET_CODE = Arrays.stream(values())
        .collect(
            Collectors.toMap(IcErrorModel::getRSocketCode, Function.identity(), (_, item2) -> item2)
        );

    private final String code;
    private final String msg;
    private final int rSocketCode;

    ConsulErrorModel(String code, String msg, int rSocketCode) {
        this.code = code;
        this.msg = msg;
        this.rSocketCode = rSocketCode;
    }

    public static ConsulErrorModel fromRSocketCode(int rSocketCode) {
        return MAP_BY_RSOCKET_CODE.getOrDefault(rSocketCode, INTERNAL_SERVER_ERROR);
    }

}
