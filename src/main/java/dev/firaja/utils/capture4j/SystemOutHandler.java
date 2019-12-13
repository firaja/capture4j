package dev.firaja.utils.capture4j;

import java.io.PrintStream;

/**
 * This class defines a simple Handler that prints a message
 * in the console output.
 *
 * @author David Bertoldi
 * @version 0.1.1
 * @since 0.1.1
 */
public class SystemOutHandler implements Handler<Object>
{

    /**
     * See {@link Handler#handle(Throwable)}
     */
    @Override
    public Object handle(Throwable theException)
    {
        getPrintStream().println(getMessage(theException));
        return null;
    }

    /**
     * Override this method in order to change the message.
     *
     * @param theException that has been thrown
     * @return the message to be printed
     * @since 0.1.1
     */
    protected String getMessage(Throwable theException)
    {
        return getDefaultMessage(theException);
    }

    /**
     * Override this method in order to change the stream.
     *
     * @return the {@link PrintStream} to use for the message.
     * @since 0.1.1
     */
    protected PrintStream getPrintStream()
    {
        return System.out;
    }

    protected static String getDefaultMessage(Throwable theException)
    {
        return "Exception" + theException.getClass().getName() + " is thrown because " + theException.getMessage()
                + "\nReturn null.";
    }

}
