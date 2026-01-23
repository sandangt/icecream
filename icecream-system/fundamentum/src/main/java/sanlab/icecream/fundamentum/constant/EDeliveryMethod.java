package sanlab.icecream.fundamentum.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EDeliveryMethod {
    STANDARD("Standard"),
    IMMEDIATELY("Immediately"),
    UNKNOWN(""),
    ;

    @Getter
    private final String formalText;

    EDeliveryMethod(String formalText) {
        this.formalText = formalText;
    }

    private static final Map<String, EDeliveryMethod> MAP_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(EDeliveryMethod::name, Function.identity()));

    public static String toName(EDeliveryMethod method) {
        return Optional.ofNullable(method).map(Enum::name).orElse(UNKNOWN.name());
    }

    public static String toFormalText(EDeliveryMethod method) {
        return Optional.ofNullable(method).map(EDeliveryMethod::getFormalText).orElse(UNKNOWN.getFormalText());
    }

    public static String toFormalText(String name) {
        var method = fromName(name);
        return toFormalText(method);
    }

    public static EDeliveryMethod fromName(String name) {
        return MAP_BY_NAME.getOrDefault(name, UNKNOWN);
    }

}
