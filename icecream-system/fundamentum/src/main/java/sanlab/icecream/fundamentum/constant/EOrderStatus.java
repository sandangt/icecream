package sanlab.icecream.fundamentum.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EOrderStatus {
    ACCEPTED("Accepted"),
    PROCESSING("Processing"),
    COMPLETED("Completed"),
    REFUND("Refund"),
    CANCELLED("Cancelled"),
    REJECTED("Rejected"),
    UNKNOWN(""),
    ;

    @Getter
    private final String formalText;

    EOrderStatus(String formalText) {
        this.formalText = formalText;
    }

    private static final Map<String, EOrderStatus> MAP_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(EOrderStatus::name, Function.identity()));

    private static final Set<EOrderStatus> ACTIVE_STATUSES = Set.of(ACCEPTED, PROCESSING);

    public static String toName(EOrderStatus status) {
        return Optional.ofNullable(status).map(Enum::name).orElse(UNKNOWN.name());
    }

    public static String toFormalText(EOrderStatus status) {
        return Optional.ofNullable(status).map(EOrderStatus::getFormalText).orElse(UNKNOWN.getFormalText());
    }

    public static String toFormalText(String name) {
        var status = fromName(name);
        return toFormalText(status);
    }

    public static EOrderStatus fromName(String name) {
        return MAP_BY_NAME.getOrDefault(name, UNKNOWN);
    }

    public static boolean isActiveStatus(String name) {
        return ACTIVE_STATUSES.contains(fromName(name));
    }

}
