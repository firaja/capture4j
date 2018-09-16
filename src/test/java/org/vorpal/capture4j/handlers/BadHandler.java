package org.vorpal.capture4j.handlers;

import org.vorpal.capture4j.GenericTest;
import org.vorpal.capture4j.Handler;


public class BadHandler implements Handler<GenericTest.Risky>
{

    private BadHandler()
    {

    }

    @Override
    public GenericTest.Risky handle()
    {
        return null;
    }
}
