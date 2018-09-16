package org.vorpal.capture4j;

import org.junit.Ignore;


@Ignore
public class HandlerTest implements Handler<GenericTest.Risky>
{
    @Override
    public GenericTest.Risky handle()
    {
        GenericTest.Risky risky = new GenericTest.Risky();
        risky.setB();
        return risky;
    }
}
