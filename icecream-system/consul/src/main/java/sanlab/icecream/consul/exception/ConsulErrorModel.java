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
    BAD_INPUT_REQUEST("CONSUL-1001", "Bad request", 770),
    //endregion

    //region Security layer
    INVALID_USER_PRINCIPAL("CONSUL-0000", "Invalid user principal", 771),
    INVALID_API_KEY("CONSUL-0001", "Invalid api key", 0),
    EMPTY_API_KEY("CONSUL-0002", "Empty api key", 0),
    //region

    //region Repository layer
    CUSTOMER_NOT_FOUND("CONSUL-2000", "Customer not found", 772),
    PRODUCT_NOT_FOUND("CONSUL-2001", "Product not found", 773),
    CATEGORY_NOT_FOUND("CONSUL-2002", "Category not found", 774),
    STOCK_NOT_FOUND("CONSUL-2003", "Stock not found", 775),
    FEEDBACK_NOT_FOUND("CONSUL-2004", "Feedback not found", 776),
    ADDRESS_NOT_FOUND("CONSUL-2005", "Address not found", 777),
    CART_NOT_FOUND("CONSUL-2006", "Cart not found", 778),
    IMAGE_NOT_FOUND("CONSUL-2007", "Image not found", 784),
    FAIL_TO_STORE_IMAGE_FILE("CONSUL-2100", "Failed to store image file", 779),
    FAIL_TO_PERSIST_DATA("CONSUL-2101", "Error occurs when persisting data", 780),
    //endregion

    //region External service identity
    INVALID_IDENTITY_ADMIN_TOKEN("CONSUL-3000", "Failed to get admin token", 781),
    INVALID_UPDATE_USER_INFO_REQUEST("CONSUL-3001", "Invalid request for updating user info", 782),
    UNAVAILABLE_IDENTITY_SERVICE("CONSUL-3002", "Identity service currently not available", 783),
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
