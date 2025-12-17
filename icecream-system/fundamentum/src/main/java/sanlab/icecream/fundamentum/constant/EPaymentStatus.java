package sanlab.icecream.fundamentum.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EPaymentStatus {
    PENDING,
    COMPLETED,
    CANCELLED,
    UNKNOWN,
    ;

    private static final Map<String, EPaymentStatus> MAP_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(EPaymentStatus::name, Function.identity()));

    public static String toName(EPaymentStatus status) {
        return Optional.ofNullable(status).map(Enum::name).orElse(UNKNOWN.name());
    }

    public static EPaymentStatus fromName(String name) {
        return MAP_BY_NAME.getOrDefault(name, UNKNOWN);
    }

}
