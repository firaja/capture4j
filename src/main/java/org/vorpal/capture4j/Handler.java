package org.vorpal.capture4j;

public interface Handler<T>
{

    String HANDLE_METHOD = "handle";

    T handle();

}
