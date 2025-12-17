package sanlab.icecream.fundamentum.constant;

public class ConsulRSocketRoute {

    private ConsulRSocketRoute() {}

    public static final String GET_PRODUCT_BY_ID = "consul.products.{id}.get";
    public static final String GET_CATEGORIES_BY_PRODUCT_ID = "consul.products.{id}.categories.get";
    public static final String GET_MEDIA_BY_PRODUCT_ID = "consul.products.{id}.media.get";
    public static final String GET_FEATURED_BANNER_BY_PRODUCT_ID = "consul.products.{id}.featured-banner.get";

    public static final String GET_PRODUCTS_BY_CATEGORY_ID = "consul.categories.{id}.products.get";
    public static final String GET_PRODUCTS_BY_IMAGE_ID = "consul.images.{id}.products.get";
}
