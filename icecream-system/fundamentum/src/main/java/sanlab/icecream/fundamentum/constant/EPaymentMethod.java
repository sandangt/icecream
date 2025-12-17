package sanlab.icecream.fundamentum.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EPaymentMethod {
    COD, BANKING, PAYPAL, UNKNOWN,
    ;

    private static final Map<String, EPaymentMethod> MAP_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(EPaymentMethod::name, Function.identity()));

    public static String toName(EPaymentMethod status) {
        return Optional.ofNullable(status).map(Enum::name).orElse(UNKNOWN.name());
    }

    public static EPaymentMethod fromName(String name) {
        return MAP_BY_NAME.getOrDefault(name, UNKNOWN);
    }
}
