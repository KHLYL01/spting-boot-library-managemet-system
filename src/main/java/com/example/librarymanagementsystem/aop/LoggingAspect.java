package com.example.librarymanagementsystem.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void serviceMethods() {}

    @Pointcut("execution(* com.example.librarymanagementsystem.feature.book.service.FindAllBookService.execute(..))")
    public void bookOperations() {}

    @Pointcut("execution(* com.example.librarymanagementsystem.feature.patron.service.*.execute(..))")
    public void patronOperations() {}

    @Around("serviceMethods() && bookOperations() && patronOperations()")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        // Log method entry
        logger.info("Executing {}.{} with arguments: {}", className, methodName, joinPoint.getArgs());

        long startTime = System.currentTimeMillis();
        try {
            // Proceed with the method execution
            Object result = joinPoint.proceed();

            // Log method exit and execution time
            long executionTime = System.currentTimeMillis() - startTime;
            logger.info("Completed {}.{} in {} ms with result: {}", className, methodName, executionTime, result);

            return result;
        } catch (Exception ex) {
            // Log exceptions
            logger.error("Exception in {}.{}: {}", className, methodName, ex.getMessage(), ex);
            throw ex; // Re-throw the exception
        }
    }

}
