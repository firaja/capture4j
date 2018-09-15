package org.vorpal.catching;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;


@Aspect
public class Interceptor
{

    @Around("execution(* *(..)) && @annotation(Catchers)")
    public Object interceptCatchers(final ProceedingJoinPoint joinPoint) throws Throwable
    {
        Method method = getMethod(joinPoint);
        Class<?> returnType = method.getReturnType();
        Catchers annotation = method.getAnnotation(Catchers.class);
        Capture[] catchers = annotation.value();

        try
        {
            return joinPoint.proceed();
        }
        catch (Throwable theException)
        {
            for (final Capture catcher : catchers)
            {
                List<Class> classesException = Arrays.asList(catcher.what());
                if (catchable(classesException, theException))
                {
                    Class<? extends Handler> handlerClass = catcher.with();
                    Class<?> handlerType = handlerClass.getMethod(Handler.HANDLE_METHOD).getReturnType();
                    if (isParent(returnType, handlerType))
                    {
                        return handleIt(handlerClass);
                    }
                }

            }
            throw theException;
        }

    }

    @Around("execution(* *(..)) && @annotation(Capture)")
    public Object interceptCapture(final ProceedingJoinPoint joinPoint) throws Throwable
    {
        Method method = getMethod(joinPoint);
        Capture annotation = method.getAnnotation(Capture.class);

        try
        {
            return joinPoint.proceed();
        }
        catch (Throwable theException)
        {

            List<Class> classesException = Arrays.asList(annotation.what());
            if (catchable(classesException, theException))
            {
                return handleIt(annotation.with());
            }

            throw theException;
        }

    }

    private boolean catchable(final List<Class> classesException, final Throwable theException)
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

    @Around("execution(* *(..)) && @annotation(org.vorpal.catching.EasyCapture)")
    public Object interceptSimpleCatchers(final ProceedingJoinPoint joinPoint) throws Throwable
    {
        Method method = getMethod(joinPoint);
        EasyCapture easyCatch = method.getAnnotation(EasyCapture.class);
        String value = easyCatch.returns();
        Class<?> returnType = method.getReturnType();

        try
        {
            return joinPoint.proceed();
        }
        catch (Throwable theException)
        {
            return SimpleHandler.handle(value, returnType);
        }

    }

    private Method getMethod(ProceedingJoinPoint joinPoint)
    {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

    private Object handleIt(Class<? extends Handler> handlerClass)
    {
        Handler handler;
        try
        {
            handler = handlerClass.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            throw new CatchException(
                    String.format("Could not instantiate with %s because: %s", handlerClass.getCanonicalName(), e.getMessage()));
        }
        return handler.handle();
    }

    private static boolean isParent(Class<?> parent, Class<?> child)
    {
        return parent.isAssignableFrom(child);
    }

}
