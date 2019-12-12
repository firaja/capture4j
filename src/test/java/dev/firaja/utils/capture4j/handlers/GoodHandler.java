package dev.firaja.utils.capture4j.handlers;

import dev.firaja.utils.capture4j.GenericTest;
import dev.firaja.utils.capture4j.Handler;
import org.junit.Ignore;


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
