package org.vorpal.capture4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.charset.CharacterCodingException;

import org.junit.Test;


public class PrimitiveHandlerTest
{
    @Test
    public void testInteger() throws Throwable
    {
        // GIVEN

        // WHEN
        int result = throwWhatYouWant(new ArithmeticException(), Integer.class);

        // THEN
        assertEquals(0, result);
    }

    @Test
    public void testString() throws Throwable
    {
        // GIVEN

        // WHEN
        String result = throwWhatYouWant(new IOException(), String.class);

        // THEN
        assertEquals("", result);
    }

    @Test
    public void testChar() throws Throwable
    {
        // GIVEN

        // WHEN
        char result = throwWhatYouWant(new CharacterCodingException(), Character.class);

        // THEN
        assertEquals('\u0000', result);
    }

    @Test
    public void testBoolean() throws Throwable
    {
        // GIVEN

        // WHEN
        boolean result = throwWhatYouWant(new IndexOutOfBoundsException(), Boolean.class);

        // THEN
        assertTrue(result);
    }

    @Test(expected = ClassCastException.class)
    public void testWrongBoolean() throws Throwable
    {
        // GIVEN

        // WHEN
        boolean result = throwWhatYouWant(new ArithmeticException(), Boolean.class);

        // THEN
        assertTrue(result);
    }

    @Test(expected = ClassNotFoundException.class)
    public void testUnmanaged() throws Throwable
    {
        // GIVEN

        // WHEN
        throwWhatYouWant(new ClassNotFoundException(), Boolean.class);
    }

    @Capture(with = PrimitiveHandler.ZERO.class, what = ArithmeticException.class)
    @Capture(with = PrimitiveHandler.NULL_CHAR.class, what = CharacterCodingException.class)
    @Capture(with = PrimitiveHandler.NULL.class, what = NullPointerException.class)
    @Capture(with = PrimitiveHandler.FALSE.class, what = IllegalArgumentException.class)
    @Capture(with = PrimitiveHandler.TRUE.class, what = ArithmeticException.class)
    @Capture(with = PrimitiveHandler.TRUE.class, what = IndexOutOfBoundsException.class)
    @Capture(with = PrimitiveHandler.EMPTY.class, what = IOException.class)
    private <T> T throwWhatYouWant(Throwable t, Class<T> returnType) throws Throwable
    {
        throw t;
    }

    @Test
    public void testOrder() throws Exception
    {
        // GIVEN

        // WHEN
        boolean result = getBoolean();

        // THEN
        assertFalse(result);
    }

    @Capture(with = PrimitiveHandler.FALSE.class)
    @Capture(with = PrimitiveHandler.TRUE.class)
    private boolean getBoolean() throws Exception
    {
        throw new Exception();
    }

}
