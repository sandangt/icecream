package sanlab.icecream.fundamentum.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EDeliveryStatus {
    PREPARING,
    DELIVERING,
    DELIVERED,
    CANCELLED,
    UNKNOWN,
    ;

    private static final Map<String, EDeliveryStatus> MAP_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(EDeliveryStatus::name, Function.identity()));

    public static String toName(EDeliveryStatus status) {
        return Optional.ofNullable(status).map(Enum::name).orElse(UNKNOWN.name());
    }

    public static EDeliveryStatus fromName(String name) {
        return MAP_BY_NAME.getOrDefault(name, UNKNOWN);
    }
}
