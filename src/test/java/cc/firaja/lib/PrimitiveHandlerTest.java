package cc.firaja.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        String result = throwWhatYouWant(new StringIndexOutOfBoundsException(), String.class);

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

    @Catcher(handler = PrimitiveHandler.ZERO.class, exceptions = ArithmeticException.class)
    @Catcher(handler = PrimitiveHandler.NULL_CHAR.class, exceptions = CharacterCodingException.class)
    @Catcher(handler = PrimitiveHandler.NULL.class, exceptions = NullPointerException.class)
    @Catcher(handler = PrimitiveHandler.FALSE.class, exceptions = IllegalArgumentException.class)
    @Catcher(handler = PrimitiveHandler.TRUE.class, exceptions = ArithmeticException.class)
    @Catcher(handler = PrimitiveHandler.TRUE.class, exceptions = IndexOutOfBoundsException.class)
    @Catcher(handler = PrimitiveHandler.EMPTY.class, exceptions = StringIndexOutOfBoundsException.class)
    private <T> T throwWhatYouWant(Throwable t, Class<T> returnType) throws Throwable
    {
        throw t;
    }

}
