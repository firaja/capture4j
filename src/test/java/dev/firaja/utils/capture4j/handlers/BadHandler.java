package dev.firaja.utils.capture4j.handlers;

import dev.firaja.utils.capture4j.GenericTest;
import dev.firaja.utils.capture4j.Handler;


public class BadHandler implements Handler<GenericTest.Risky>
{

    private BadHandler()
    {

    }

    @Override
    public GenericTest.Risky handle(Throwable theException)
    {
        return null;
    }
}
