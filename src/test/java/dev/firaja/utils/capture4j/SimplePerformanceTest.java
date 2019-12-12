package dev.firaja.utils.capture4j;

import org.junit.Test;


public class SimplePerformanceTest
{

    private static final int WARMUP = 1000;

    private static final int ROUNDS = WARMUP + 100000;

    @Test
    public void perform()
    {

        long now = 0;


        for (int i = 0; i < ROUNDS; i++)
        {
            if (i == WARMUP)
            {
                now = System.currentTimeMillis();
            }
            callWithAnnotation();

        }
        System.out.println("annotation: " + (System.currentTimeMillis() - now));


        for (int i = 0; i < ROUNDS; i++)
        {
            if (i == WARMUP)
            {
                now = System.currentTimeMillis();
            }
            callWithTryCatch();

        }
        System.out.println("try catch: " + (System.currentTimeMillis() - now));



    }

    private int functionThatFails()
    {
        int x = 3;
        int y = 0;
        return x / y;
    }

    @Capture(with = PrimitiveHandler.ZERO.class)
    private int callWithAnnotation()
    {
        return functionThatFails();
    }

    private int callWithTryCatch()
    {
        try
        {
            return functionThatFails();
        }
        catch (Exception e)
        {
            return 0;
        }
    }

}
