package sanlab.icecream.fundamentum.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EPaymentStatus {
    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELLED("Canceled"),
    UNKNOWN(""),
    ;

    @Getter
    private final String formalText;

    EPaymentStatus(String formalText) {
        this.formalText = formalText;
    }

    private static final Map<String, EPaymentStatus> MAP_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(EPaymentStatus::name, Function.identity()));

    public static String toName(EPaymentStatus status) {
        return Optional.ofNullable(status).map(Enum::name).orElse(UNKNOWN.name());
    }

    public static String toFormalText(EPaymentStatus status) {
        return Optional.ofNullable(status).map(EPaymentStatus::getFormalText).orElse(UNKNOWN.getFormalText());
    }

    public static String toFormalText(String name) {
        var status = fromName(name);
        return toFormalText(status);
    }

    public static EPaymentStatus fromName(String name) {
        return MAP_BY_NAME.getOrDefault(name, UNKNOWN);
    }

}
