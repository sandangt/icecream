package sanlab.icecream.fundamentum.constant;

public class PreAuthorizedAuthExp {
    private PreAuthorizedAuthExp() {}
    public static final String NORMIE_AND_WATCHER = "hasAnyRole('normie', 'watcher')";
    public static final String NORMIE = "hasRole('normie')";
    public static final String WATCHER = "hasRole('watcher')";
    public static final String ANON = "isAnonymous()";
}
