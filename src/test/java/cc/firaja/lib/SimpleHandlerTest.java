package cc.firaja.lib;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cc.firaja.lib.SimpleCatcher;


public class SimpleHandlerTest
{

    @Test
    public void testBoolean() throws Throwable
    {
        boolean result = returnBoolean();
        assertTrue(result);
    }

    @SimpleCatcher(value = "true")
    private Boolean returnBoolean() throws Throwable
    {
        throw new Exception();
    }

    @Test(expected = CatchException.class)
    public void testVoid() throws Throwable
    {
        noReturn();
    }

    @SimpleCatcher(value = "1b.")
    private void noReturn() throws Throwable
    {
        throw new Exception();
    }

}
