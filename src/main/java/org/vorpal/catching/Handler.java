package org.vorpal.catching;

public interface Handler<T>
{

    String HANDLE_METHOD = "handle";

    T handle();

}
