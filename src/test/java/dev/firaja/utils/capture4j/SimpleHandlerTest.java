package dev.firaja.utils.capture4j;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class SimpleHandlerTest
{

    @Test
    public void testBoolean() throws Throwable
    {
        boolean result = returnBoolean();
        assertTrue(result);
    }

    @EasyCapture(returns = "true")
    private Boolean returnBoolean() throws Throwable
    {
        throw new Exception();
    }

    @Test(expected = CatchException.class)
    public void testVoid() throws Throwable
    {
        noReturn();
    }

    @EasyCapture(returns = "1b.")
    private void noReturn() throws Throwable
    {
        throw new Exception();
    }

}
