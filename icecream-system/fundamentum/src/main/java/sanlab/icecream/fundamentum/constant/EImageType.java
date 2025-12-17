package sanlab.icecream.fundamentum.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EImageType {
    AVATAR,
    MEDIA,
    UNKNOWN,
    ;

    private static final Map<String, EImageType> MAP_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(EImageType::name, Function.identity()));

    public static String toName(EImageType status) {
        return Optional.ofNullable(status).map(Enum::name).orElse(UNKNOWN.name());
    }

    public static EImageType fromName(String name) {
        return MAP_BY_NAME.getOrDefault(name, UNKNOWN);
    }

}
