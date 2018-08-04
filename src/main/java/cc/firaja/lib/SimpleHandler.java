package cc.firaja.lib;

import java.util.HashMap;
import java.util.Map;


public class SimpleHandler
{

    private static final Map<Class, AbstractHandler> dispatcher = new HashMap<>();

    static
    {
        // Byte
        AbstractHandler<Byte> byteHandler = new AbstractHandler<Byte>()
        {
            @Override
            public Byte handle(String value)
            {
                return Byte.valueOf(value);
            }
        };
        dispatcher.put(Byte.class, byteHandler);
        dispatcher.put(Byte.TYPE, byteHandler);

        // Integer
        AbstractHandler<Integer> integerAbstractHandler = new AbstractHandler<Integer>()
        {
            @Override
            public Integer handle(String value)
            {
                return Integer.valueOf(value);
            }
        };
        dispatcher.put(Integer.class, integerAbstractHandler);
        dispatcher.put(Integer.TYPE, integerAbstractHandler);

        // Float
        AbstractHandler<Float> floatHandler = new AbstractHandler<Float>()
        {
            @Override
            public Float handle(String value)
            {
                return Float.valueOf(value);
            }
        };
        dispatcher.put(Float.class, floatHandler);
        dispatcher.put(Float.TYPE, floatHandler);

        // Double
        AbstractHandler<Double> doubleHandler = new AbstractHandler<Double>()
        {
            @Override
            public Double handle(String value)
            {
                return Double.valueOf(value);
            }
        };
        dispatcher.put(Double.class, doubleHandler);
        dispatcher.put(Double.TYPE, doubleHandler);

        // Character
        AbstractHandler<Character> characterHandler = new AbstractHandler<Character>()
        {
            @Override
            public Character handle(String value)
            {
                return value.charAt(0);
            }
        };
        dispatcher.put(Character.class, characterHandler);
        dispatcher.put(Character.TYPE, characterHandler);

        // String
        AbstractHandler<String> stringHandler = new AbstractHandler<String>()
        {
            @Override
            public String handle(String value)
            {
                return value;
            }
        };
        dispatcher.put(String.class, stringHandler);

        // Boolean
        AbstractHandler<Boolean> booleanHandler = new AbstractHandler<Boolean>()
        {
            @Override
            public Boolean handle(String value)
            {
                return Boolean.valueOf(value);
            }
        };
        dispatcher.put(Boolean.class, booleanHandler);
        dispatcher.put(Boolean.TYPE, booleanHandler);
    }

    public <T> T handle(String value, Class<T> clazz)
    {
        AbstractHandler<T> handler = dispatcher.get(clazz);
        if (handler == null)
        {
            throw new CatchException("SimpleHandler cannot be applied to class " + clazz.getCanonicalName());
        }
        return handler.handle(value);
    }

    private abstract static class AbstractHandler<T>
    {
        public abstract T handle(String value);
    }

}
