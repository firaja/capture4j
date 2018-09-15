package org.vorpal.catching;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class SuperExceptionTest
{

    class ParentException extends Exception
    {

    }

    class ChildException extends ParentException
    {

    }

    @Test(expected = ParentException.class)
    public void testHierarchy() throws Exception
    {
        // GIVEN

        // WHEN
        int result = methodThatFails();

        // THEN

    }

    @Test
    public void testInverseHierarchy() throws Exception
    {
        // GIVEN

        // WHEN
        int result = anotherMethodThatFails();

        // THEN
        assertEquals(0, result);
    }

    @Capture(with = PrimitiveHandler.ZERO.class, what = ChildException.class)
    private int methodThatFails() throws ParentException
    {
        throw new ParentException();
    }

    @Capture(with = PrimitiveHandler.ZERO.class, what = ParentException.class)
    private int anotherMethodThatFails() throws ChildException
    {
        throw new ChildException();
    }

}
