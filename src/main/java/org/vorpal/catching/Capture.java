package org.vorpal.catching;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Repeatable(Catchers.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Capture
{
    Class<? extends Handler> with();

    Class<? extends Throwable>[] what() default { Throwable.class };

}
