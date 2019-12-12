package dev.firaja.utils.capture4j;

/**
 * This interface represents the handler of the
 * exception to be caught.
 * <p>
 * All the handlers implementing this interface
 * may use the original exception.
 *
 * @author David Bertoldi
 * @version 0.1.1
 * @since 0.1.0
 */
public interface Handler<T>
{

    // For internal usage only
    String HANDLE_METHOD = "handle";


    /**
     * Handles the specified exception via annotation, like
     * {@link Capture}, {@link EasyCapture}, etc...
     *
     * @param theException the original exception thrown by the annotated method.
     * @return a value castable to the original return value of
     * the annotated method.
     * @since 0.1.1
     */
    T handle(Throwable theException);

}
