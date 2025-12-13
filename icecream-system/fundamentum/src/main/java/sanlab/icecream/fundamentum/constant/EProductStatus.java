package sanlab.icecream.fundamentum.constant;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum EProductStatus {
    UNAVAILABLE(1, "Unavailable"),
    AVAILABLE(2, "Available"),
    INVALID(3, "Invalid"),
    ARCHIVED(4, "Archived"),
    UNKNOWN(-1, "Unknown");
    ;

    private final int code;
    private final String formalName;

    EProductStatus(int code, String formalName) {
        this.code = code;
        this.formalName = formalName;
    }

    private static final Map<String, EProductStatus> MAP_BY_NAME = Arrays.stream(values())
        .collect(Collectors.toMap(Enum::name,Function.identity()));

    public static EProductStatus fromCode(int code) {
        var codes = values();
        return (code<0 || code>=codes.length) ? UNKNOWN : codes[code];
    }

    public static EProductStatus fromName(String name) {
        return MAP_BY_NAME.getOrDefault(StringUtils.upperCase(name), UNKNOWN);
    }

}
