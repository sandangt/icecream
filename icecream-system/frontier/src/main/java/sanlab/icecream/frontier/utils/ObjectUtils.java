package sanlab.icecream.frontier.utils;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

public class ObjectUtils {

    private ObjectUtils() {}

    public static void copyNotNull(Object source, Object target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Source and target must not be null");
        }
        if (!source.getClass().equals(target.getClass())) {
            throw new IllegalArgumentException("Source and target must be of the same type");
        }

        Class<?> sourceClazz = source.getClass();
        Field[] fields = FieldUtils.getAllFields(sourceClazz);

        for (Field field : fields) {
            try {
                Object value = FieldUtils.readField(field, source, true);
                if (value != null) {
                    FieldUtils.writeField(field, target, value, true);
                }
            } catch (IllegalAccessException ex) {
                throw new RuntimeException("Failed to copy properties", ex);
            }
        }
    }

}
