package cc.firaja.lib;

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
    public Object interceptCatchers(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Method method = getMethod(joinPoint);
        Catchers annotation = method.getAnnotation(Catchers.class);
        Catcher[] catchers = annotation.value();

        try
        {
            return joinPoint.proceed();
        }
        catch (Throwable theException)
        {
            for (Catcher catcher : catchers)
            {
                List<Class> classesException = Arrays.asList(catcher.exceptions());
                if (classesException.contains(theException.getClass()))
                {
                    return handleIt(catcher.handler());
                }

            }
            throw theException;
        }

    }

    @Around("execution(* *(..)) && @annotation(SimpleCatcher)")
    public Object interceptSimpleCatchers(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Method method = getMethod(joinPoint);
        SimpleCatcher simpleCatcher = method.getAnnotation(SimpleCatcher.class);
        String value = simpleCatcher.value();
        Class<?> returnType = method.getReturnType();

        try
        {
            return joinPoint.proceed();
        }
        catch (Throwable theException)
        {
            return new SimpleHandler().handle(value, returnType);
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
                    String.format("Could not instantiate handler %s because: %s", handlerClass.getCanonicalName(),
                            e.getMessage()));
        }
        return handler.handle();
    }

}
