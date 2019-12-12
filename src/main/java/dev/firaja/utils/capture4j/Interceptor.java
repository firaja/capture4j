package dev.firaja.utils.capture4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


@Aspect
public class Interceptor
{

    @Around("execution(* *(..)) && @annotation(dev.firaja.utils.capture4j.Catchers)")
    public Object interceptCatchers(final ProceedingJoinPoint joinPoint) throws Throwable
    {
        try
        {
            return joinPoint.proceed();
        } catch (Throwable theException)
        {
            Method method = getMethod(joinPoint);
            Class<?> returnType = method.getReturnType();
            Catchers annotation = method.getAnnotation(Catchers.class);
            Capture[] catchers = annotation.value();

            for (final Capture catcher : catchers)
            {
                List<Class<?>> classesException = Arrays.asList(catcher.what());
                if (catchable(classesException, theException))
                {
                    Class<? extends Handler> handlerClass = catcher.with();
                    Class<?> handlerType = handlerClass.getMethod(Handler.HANDLE_METHOD).getReturnType();
                    if (isParent(returnType, handlerType))
                    {
                        return handleIt(handlerClass, theException);
                    }
                }

            }
            throw theException;
        }

    }

    @Around("execution(* *(..)) && @annotation(dev.firaja.utils.capture4j.Capture)")
    public Object interceptCapture(final ProceedingJoinPoint joinPoint) throws Throwable
    {
        try
        {
            return joinPoint.proceed();
        } catch (Throwable theException)
        {
            Method method = getMethod(joinPoint);
            Capture annotation = method.getAnnotation(Capture.class);
            List<Class<?>> classesException = Arrays.asList(annotation.what());
            if (catchable(classesException, theException))
            {
                return handleIt(annotation.with(), theException);
            }

            throw theException;
        }

    }

    private static boolean catchable(final List<Class<?>> classesException, final Throwable theException)
    {
        for (final Class<?> classExcpetion : classesException)
        {
            if (isParent(classExcpetion, theException.getClass()))
            {
                return true;
            }
        }
        return false;
    }

    @Around("execution(* *(..)) && @annotation(dev.firaja.utils.capture4j.EasyCapture)")
    public Object interceptSimpleCatchers(final ProceedingJoinPoint joinPoint) throws Throwable
    {
        try
        {
            return joinPoint.proceed();
        } catch (Throwable theException)
        {
            Method method = getMethod(joinPoint);
            EasyCapture easyCatch = method.getAnnotation(EasyCapture.class);
            String value = easyCatch.returns();
            Class<?> returnType = method.getReturnType();
            return SimpleHandler.handle(value, returnType);
        }

    }

    private static Method getMethod(ProceedingJoinPoint joinPoint)
    {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

    private static Object handleIt(Class<? extends Handler> handlerClass, Throwable theException)
    {
        Handler<?> handler;
        try
        {
            handler = handlerClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e)
        {
            throw new CatchException(
                    String.format("Could not instantiate with %s because: %s", handlerClass.getCanonicalName(), e.getMessage()));
        }
        return handler.handle(theException);
    }

    private static boolean isParent(Class<?> parent, Class<?> child)
    {
        return Capture4jUtils.wrap(parent).isAssignableFrom(Capture4jUtils.wrap(child));
    }


}
