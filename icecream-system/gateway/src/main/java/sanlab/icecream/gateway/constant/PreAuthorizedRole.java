package sanlab.icecream.gateway.constant;

public class PreAuthorizedRole {
    public static final String NORMIE_AND_VIP = "hasAnyRole('icecream-client-normie', 'icecream-client-vip')";
    public static final String NORMIE = "hasRole('icecream-client-normie')";
    public static final String VIP = "hasRole('icecream-client-vip')";
}
