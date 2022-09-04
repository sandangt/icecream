package icecream.product.constant;

public enum EStatus {
    NEW("NEW"),
    HOT("HOT"),
    AVAILABLE("AVAILABLE"),
    ARCHIVED("ARCHIVED");

    private final String statusVal;
    EStatus(final String status) {
        this.statusVal = status;
    }

    @Override
    public String toString() {
        return this.statusVal;
    }
}
