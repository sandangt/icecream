package sanlab.icecream.sharedlib.enumeration;

public enum ERole {
    NORMIE("icecream-client-normie"),
    VIP("icecream-client-vip");
    private final String value;
    ERole(String value) {
        this.value = value;
    }
    public String value() {
        return this.value;
    }
}
