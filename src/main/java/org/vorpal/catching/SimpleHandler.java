package org.vorpal.catching;

import java.util.HashMap;
import java.util.Map;


class SimpleHandler
{

    private static final Map<Class, AbstractHandler> dispatcher = new HashMap<>();

    static
    {
        // Byte
        AbstractHandler<Byte> byteHandler = Byte::valueOf;
        dispatcher.put(Byte.class, byteHandler);
        dispatcher.put(Byte.TYPE, byteHandler);

        // Integer
        AbstractHandler<Integer> integerAbstractHandler = Integer::valueOf;
        dispatcher.put(Integer.class, integerAbstractHandler);
        dispatcher.put(Integer.TYPE, integerAbstractHandler);

        // Float
        AbstractHandler<Float> floatHandler = Float::valueOf;
        dispatcher.put(Float.class, floatHandler);
        dispatcher.put(Float.TYPE, floatHandler);

        // Double
        AbstractHandler<Double> doubleHandler = Double::valueOf;
        dispatcher.put(Double.class, doubleHandler);
        dispatcher.put(Double.TYPE, doubleHandler);

        // Character
        AbstractHandler<Character> characterHandler = value -> value.charAt(0);
        dispatcher.put(Character.class, characterHandler);
        dispatcher.put(Character.TYPE, characterHandler);

        // String
        AbstractHandler<String> stringHandler = value -> value;
        dispatcher.put(String.class, stringHandler);

        // Boolean
        AbstractHandler<Boolean> booleanHandler = Boolean::valueOf;
        dispatcher.put(Boolean.class, booleanHandler);
        dispatcher.put(Boolean.TYPE, booleanHandler);
    }

    static <T> T handle(String value, Class<T> clazz)
    {
        AbstractHandler<T> handler = dispatcher.get(clazz);
        if (handler == null)
        {
            throw new CatchException("SimpleHandler cannot be applied to class " + clazz.getCanonicalName());
        }
        return handler.handle(value);
    }

    private SimpleHandler()
    {
    }

    private interface AbstractHandler<T>
    {
        T handle(String value);
    }

}
