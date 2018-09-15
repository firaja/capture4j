package org.vorpal.catching;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GenericTest
{

    static class Risky
    {
        private boolean b = false;

        void setB()
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

    @Capture(with = HandlerTest.class)
    public Risky buildRisky()
    {
        return new Risky().done();
    }

}
