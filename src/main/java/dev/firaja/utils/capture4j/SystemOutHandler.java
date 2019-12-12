package dev.firaja.utils.capture4j;

public class SystemOutHandler implements Handler<Object>
{

    @Override
    public Object handle(Throwable theException)
    {
        System.out.println(getMessage(theException));
        return null;
    }

    static String getMessage(Throwable theException)
    {
        return "Exception" + theException.getClass().getName() + " is thrown because " + theException.getMessage()
                + "\nReturn null.";
    }
}
