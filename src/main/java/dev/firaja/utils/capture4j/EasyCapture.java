package org.vorpal.capture4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EasyCapture
{
    String returns();

    Class<? extends Throwable>[] what() default { Throwable.class };

}
