package sanlab.icecream.fundamentum.constant;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum EPreAuthorizeRole {
    NORMIE("ROLE_normie", "Normie", "normie"),
    WATCHER("ROLE_watcher", "Watcher", "watcher"),
    GARDENER("ROLE_gerdener", "Gardener", "gardener"),
    ANON("ROLE_ANONYMOUS", "Anonymous", "anon"),
    ;

    public static final String HAS_ROLE_NORMIE_AND_WATCHER = "hasAnyRole('normie','watcher')";
    public static final String HAS_ROLE_NORMIE = "hasRole('normie')";
    public static final String HAS_ROLE_WATCHER = "hasRole('watcher')";
    public static final String HAS_ROLE_GARDENER = "hasRole('gardener')";
    public static final String PERMIT_ALL = "permitAll()";

    private static final Map<String, EPreAuthorizeRole> MAP_BY_RAW = new HashMap<>();

    static {
        for (var role : EPreAuthorizeRole.values()) {
            MAP_BY_RAW.put(role.getRaw(), role);
        }
    }

    private final String raw;
    private final String formalName;
    private final String code;

    EPreAuthorizeRole(String raw, String formalName, String code) {
        this.raw = raw;
        this.formalName = formalName;
        this.code = code;
    }

    public static EPreAuthorizeRole parseToEnum(String type) {
        try {
            return EPreAuthorizeRole.valueOf(type);
        } catch (IllegalArgumentException ignored) {
            return ANON;
        }
    }

    public static EPreAuthorizeRole fromRaw(String raw) {
        return MAP_BY_RAW.getOrDefault(raw, ANON);
    }

}
