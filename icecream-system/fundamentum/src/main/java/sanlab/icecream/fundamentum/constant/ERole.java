package sanlab.icecream.fundamentum.constant;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ERole {
    NORMIE("ROLE_normie", "Normie"),
    WATCHER("ROLE_watcher", "Watcher"),
    ANON("ROLE_ANONYMOUS", "Anonymous"),
    ;

    private static final Map<String, ERole> MAP_BY_RAW = new HashMap<>();

    static {
        for (var role : ERole.values()) {
            MAP_BY_RAW.put(role.getRaw(), role);
        }
    }

    private final String raw;
    private final String name;

    ERole(String raw, String name) {
        this.raw = raw;
        this.name = name;
    }

    public static ERole parseToEnum(String type) {
        try {
            return ERole.valueOf(type);
        } catch (IllegalArgumentException ignored) {
            return ANON;
        }
    }

    public static ERole fromRaw(String raw) {
        return MAP_BY_RAW.getOrDefault(raw, ANON);
    }

}
