package sanlab.icecream.fundamentum.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

public class LogUtils {

    private LogUtils() {}

    public static void logException(Logger log, Exception e, String format, Object... args) {
        var msgFormat = e.getClass() + " - " + format;
        log.error(msgFormat, args);
        if (log.isDebugEnabled()) {
            log.debug(StringUtils.EMPTY, e);
        }
    }

    public static void logException(Logger log, Exception e) {
        logException(log, e, e.getMessage());
    }

    public static void logThrowable(Logger log, Throwable t) {
        log.error(t.getMessage());
        if (log.isDebugEnabled()) {
            log.debug(StringUtils.EMPTY, t);
        }
    }

    public static void logInfo(Logger log, String format, Object... args) {
        log.info(format, args);
    }

    public static void logDebug(Logger log, String format, Object... args) {
        log.debug(format, args);
    }

    public static void logError(Logger log, String format, Object... args) {
        log.error(format, args);
    }

}
