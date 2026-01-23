package sanlab.icecream.fundamentum.constant;

public class TableName {

    private TableName() {}


    //region consul
    public static final String ADDRESS = "address";
    public static final String CART = "cart";
    public static final String CART_ITEM = "cart_item";
    public static final String CATEGORY = "category";
    public static final String CUSTOMER = "customer";
    public static final String FEEDBACK = "feedback";
    public static final String IMAGE = "image";
    public static final String ORDER = "order_tbl";
    public static final String ORDER_ITEM = "order_item";
    public static final String PRODUCT = "product";
    public static final String STOCK = "stock";
    public static final String ES_PRODUCT = "icecream.conflux.enriched.product";
    //endregion

    //region consul CDC queue name
    private static final String QUEUE_PREFIX = "icecream.consul.";
    public static final String QUEUE_PRODUCT = QUEUE_PREFIX + PRODUCT;
    public static final String QUEUE_CATEGORY = QUEUE_PREFIX + CATEGORY;
    public static final String QUEUE_IMAGE = QUEUE_PREFIX + IMAGE;
    public static final String QUEUE_PRODUCT_CATEGORY = QUEUE_PREFIX + "product_category";
    public static final String QUEUE_PRODUCT_IMAGE = QUEUE_PREFIX + "product_image";
    //endregion

    //region Memoir
    public static final String AUDIT_LOG = "audit_log";
    //endregion

    //region Echo
    public static final String BELL_NOTIFICATION_MESSAGE = "bell_notification_message";
    //endregion

}
