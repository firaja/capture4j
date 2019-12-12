package dev.firaja.utils.capture4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for internal usage only.
 *
 * @author David Bertoldi
 * @version 0.1.0
 * @since 0.1.0
 */
class Capture4jUtils
{
    private static final Map<Class<?>, Class<?>> PRIMITIVES_TO_WRAPPERS = new HashMap<>();

    static
    {
        PRIMITIVES_TO_WRAPPERS.put(boolean.class, Boolean.class);
        PRIMITIVES_TO_WRAPPERS.put(byte.class, Byte.class);
        PRIMITIVES_TO_WRAPPERS.put(char.class, Character.class);
        PRIMITIVES_TO_WRAPPERS.put(double.class, Double.class);
        PRIMITIVES_TO_WRAPPERS.put(float.class, Float.class);
        PRIMITIVES_TO_WRAPPERS.put(int.class, Integer.class);
        PRIMITIVES_TO_WRAPPERS.put(long.class, Long.class);
        PRIMITIVES_TO_WRAPPERS.put(short.class, Short.class);
        PRIMITIVES_TO_WRAPPERS.put(void.class, Void.class);

    }

    /**
     * Transforms a primitive into its wrapped type.
     * If the input is an object, then the object itself is returned.
     *
     * @param clazz any class
     * @return the wrapped counterpart if the input is primitive
     * @since 0.1.0
     */
    @SuppressWarnings("unchecked")
    protected static <T> Class<T> wrap(Class<T> clazz)
    {
        return clazz.isPrimitive() ? (Class<T>) PRIMITIVES_TO_WRAPPERS.get(clazz) : clazz;
    }


}
