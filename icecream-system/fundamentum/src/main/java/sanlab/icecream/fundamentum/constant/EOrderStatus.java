package sanlab.icecream.fundamentum.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EOrderStatus {
    PENDING,
    ACCEPTED,
    PENDING_PAYMENT,
    PAID,
    SHIPPING,
    COMPLETED,
    REFUND,
    CANCELLED,
    REJECTED,
    UNKNOWN,
    ;

    private static final Map<String, EOrderStatus> MAP_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(EOrderStatus::name, Function.identity()));

    public static String toName(EOrderStatus status) {
        return Optional.ofNullable(status).map(Enum::name).orElse(UNKNOWN.name());
    }

    public static EOrderStatus fromName(String name) {
        return MAP_BY_NAME.getOrDefault(name, UNKNOWN);
    }

}
