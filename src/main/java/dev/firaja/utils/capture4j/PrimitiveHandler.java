package dev.firaja.utils.capture4j;

public class PrimitiveHandler
{

    static abstract class AbstractPrimitiveHandler<T> implements Handler<T>
    {
        @Override
        public T handle(Throwable theException)
        {
            return handle();
        }

        abstract T handle();
    }

    public static class TRUE extends AbstractPrimitiveHandler<Boolean>
    {

        @Override
        public Boolean handle()
        {
            return true;
        }
    }

    public static class FALSE extends AbstractPrimitiveHandler<Boolean>
    {

        @Override
        public Boolean handle()
        {
            return false;
        }
    }

    public static class ZERO extends AbstractPrimitiveHandler<Number>
    {

        @Override
        public Integer handle()
        {
            return 0;
        }
    }

    public static class EMPTY extends AbstractPrimitiveHandler<String>
    {

        @Override
        public String handle()
        {
            return "";
        }
    }

    public static class NULL extends AbstractPrimitiveHandler
    {

        @Override
        public Object handle()
        {
            return null;
        }
    }

    public static class NULL_CHAR extends AbstractPrimitiveHandler<Character>
    {

        @Override
        public Character handle()
        {
            return '\u0000';
        }
    }

}
