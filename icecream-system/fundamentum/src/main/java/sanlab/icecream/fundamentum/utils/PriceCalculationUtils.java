package sanlab.icecream.fundamentum.utils;

public final class PriceCalculationUtils {

    private PriceCalculationUtils() {}

    public static Double discountedPrice(Double originalPrice, Double discount) {
        return originalPrice * (1 - discount);
    }

}
