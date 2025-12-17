package sanlab.icecream.fundamentum.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EDeliveryMethod {
    VIETTEL_POST,
    GRAB_EXPRESS,
    ICECREAM_EXPRESS,
    UNKNOWN,
    ;

    private static final Map<String, EDeliveryMethod> MAP_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(EDeliveryMethod::name, Function.identity()));

    public static String toName(EDeliveryMethod status) {
        return Optional.ofNullable(status).map(Enum::name).orElse(UNKNOWN.name());
    }

    public static EDeliveryMethod fromName(String name) {
        return MAP_BY_NAME.getOrDefault(name, UNKNOWN);
    }

}
