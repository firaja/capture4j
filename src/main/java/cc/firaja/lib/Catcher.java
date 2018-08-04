package cc.firaja.lib;

import java.lang.annotation.Repeatable;

@Repeatable(Catchers.class)
public @interface Catcher
{
    Class<? extends Handler> handler();

    Class<? extends Exception>[] exceptions() default { Exception.class };

}
