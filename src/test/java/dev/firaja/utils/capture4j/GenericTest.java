package org.vorpal.capture4j;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.vorpal.capture4j.handlers.BadHandler;
import org.vorpal.capture4j.handlers.GoodHandler;


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

}
