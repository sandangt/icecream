package sanlab.icecream.fundamentum.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ECustomerStatus {
    ACTIVE,
    INACTIVE,
    UNKNOWN,
    ;

    private static final Map<String, ECustomerStatus> MAP_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(ECustomerStatus::name, Function.identity()));

    public static String toName(ECustomerStatus status) {
        return Optional.ofNullable(status).map(Enum::name).orElse(UNKNOWN.name());
    }

    public static ECustomerStatus fromName(String name) {
        return MAP_BY_NAME.getOrDefault(name, UNKNOWN);
    }

}
