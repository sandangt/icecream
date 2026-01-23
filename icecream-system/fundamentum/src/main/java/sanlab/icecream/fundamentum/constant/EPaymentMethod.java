package sanlab.icecream.fundamentum.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EPaymentMethod {
    COD("Collect on Delivery"),
    ICECREAM_PAY("IceCream E-Pay"),
    UNKNOWN(""),
    ;

    @Getter
    private final String formalText;

    EPaymentMethod(String formalText) {
        this.formalText = formalText;
    }

    private static final Map<String, EPaymentMethod> MAP_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(EPaymentMethod::name, Function.identity()));

    public static String toName(EPaymentMethod method) {
        return Optional.ofNullable(method).map(Enum::name).orElse(UNKNOWN.name());
    }

    public static String toFormalText(EPaymentMethod method) {
        return Optional.ofNullable(method).map(EPaymentMethod::getFormalText).orElse(UNKNOWN.getFormalText());
    }

    public static String toFormalText(String name) {
        var method = fromName(name);
        return toFormalText(method);
    }

    public static EPaymentMethod fromName(String name) {
        return MAP_BY_NAME.getOrDefault(name, UNKNOWN);
    }
}
