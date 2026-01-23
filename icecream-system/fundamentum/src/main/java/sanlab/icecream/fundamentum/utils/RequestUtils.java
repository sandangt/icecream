package sanlab.icecream.fundamentum.utils;

public final class RequestUtils {

    private RequestUtils() {}

    public static long calculateTotalPage(long total, long limit) {
        return Math.ceilDiv(total, limit);
    }

}
