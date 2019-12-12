package dev.firaja.utils.capture4j;

import org.junit.Test;
import dev.firaja.utils.capture4j.handlers.BadHandler;
import dev.firaja.utils.capture4j.handlers.GoodHandler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.IllegalFormatWidthException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class GenericTest
{

    public static class Risky
    {
        private boolean b = false;

        public void setB()
        {
            this.b = true;
        }

        Risky done()
        {
            if (!b)
            {
                throw new IllegalStateException();
            }
            return this;
        }

        boolean isCorrect()
        {
            return b;
        }
    }

    @Test
    public void testRisky()
    {
        // GIVEN

        // WHEN
        Risky risky = buildRisky();

        // THEN
        assertTrue(risky.isCorrect());
    }

    @Test(expected = CatchException.class)
    public void testRiskyWithBadHandler()
    {
        // GIVEN

        // WHEN
        Risky risky = buildBadRisky();

        // THEN
    }

    @Capture(with = GoodHandler.class)
    public Risky buildRisky()
    {
        return new Risky().done();
    }

    @Capture(with = BadHandler.class)
    public Risky buildBadRisky()
    {
        return new Risky().done();
    }


    @Test
    public void testPrintOutHandler()
    {
        // GIVEN
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        IllegalFormatWidthException exc = new IllegalFormatWidthException(1);

        // WHEN
        List<Map<Character, boolean[][]>> uglyObject = testMethod(exc);

        //THEN
        assertNull(uglyObject);
        String expectedOutput = SystemOutHandler.getMessage(exc) + '\n';
        assertEquals(expectedOutput, outContent.toString());
    }

    @Capture(with=SystemOutHandler.class)
    public List<Map<Character, boolean[][]>> testMethod(IllegalFormatWidthException exc)
    {
        throw exc;
    }


}
