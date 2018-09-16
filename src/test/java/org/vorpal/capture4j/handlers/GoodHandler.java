package org.vorpal.capture4j.handlers;

import org.junit.Ignore;
import org.vorpal.capture4j.GenericTest;
import org.vorpal.capture4j.Handler;


@Ignore
public class GoodHandler implements Handler<GenericTest.Risky>
{
    @Override
    public GenericTest.Risky handle()
    {
        GenericTest.Risky risky = new GenericTest.Risky();
        risky.setB();
        return risky;
    }
}
