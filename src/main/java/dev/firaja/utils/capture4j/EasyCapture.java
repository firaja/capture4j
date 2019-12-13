package dev.firaja.utils.capture4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation allows you to define the return value of a method to be associated with an array of {@link Throwable}s.
 * <p>
 * <pre>{@code @EasyCapture(returns=<string representation of the value>" what=<array of class literals of Throwables>)}</pre>
 * </p>
 * <p>
 * The `returns` attribute must contain a valid String representation of the value if casted to the returned type of the
 * annotated method. If not, a {@link CatchException} is thrown.
 * </p>
 * <p>
 * A String representation is considered valid if
 *  <ul>
 *      <li>in case of {@link byte} or {@link Byte} the method {@link Byte#valueOf(String)} does not throw a {@link NumberFormatException}</li>
 *      <li>in case of {@link int} or {@link Integer} the method {@link Integer#valueOf(String)} does not throw a {@link NumberFormatException}</li>
 *      <li>in case of {@link float} or {@link Float} the method {@link Float#valueOf(String)} does not throw a {@link NumberFormatException}</li>
 *      <li>in case of {@link double} or {@link Double} the method {@link Double#valueOf(String)} does not throw a {@link NumberFormatException}</li>
 *      <li>in case of {@link char} or {@link Character} the method {@link String#charAt(int 0)}} does not throw a {@link StringIndexOutOfBoundsException}</li>
 *  </ul>
 *  </p>
 *  <p>
 *  For {@link boolean} and {@link Boolean} the String is converted through {@link Boolean#valueOf(String)}.
 * <p>
 *  In case of {@link String} the actual representation is always considered valid.
 * <p>
 *  Other types are not supported.
 * </p>
 *
 * @author David Bertoldi
 * @version 0.1.0
 * @since 0.1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EasyCapture
{
    String returns();

    Class<? extends Throwable>[] what() default {Throwable.class};

}
