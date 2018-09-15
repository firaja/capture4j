package org.vorpal.catching;

public class PrimitiveHandler
{

    public static class TRUE implements Handler<Boolean>
    {

        @Override
        public Boolean handle()
        {
            return true;
        }
    }

    public static class FALSE implements Handler<Boolean>
    {

        @Override
        public Boolean handle()
        {
            return false;
        }
    }

    public static class ZERO implements Handler<Number>
    {

        @Override
        public Integer handle()
        {
            return 0;
        }
    }

    public static class EMPTY implements Handler<String>
    {

        @Override
        public String handle()
        {
            return "";
        }
    }

    public static class NULL implements Handler
    {

        @Override
        public Object handle()
        {
            return null;
        }
    }

    public static class NULL_CHAR implements Handler<Character>
    {

        @Override
        public Character handle()
        {
            return '\u0000';
        }
    }

}
