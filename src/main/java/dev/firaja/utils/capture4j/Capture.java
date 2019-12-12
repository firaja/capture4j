package dev.firaja.utils.capture4j;

import java.lang.annotation.*;


@Repeatable(Catchers.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Capture
{
    Class<? extends Handler> with();

    Class<? extends Throwable>[] what() default {Throwable.class};

}
