package com.francis.biz.log.frame;

import com.francis.biz.log.frame.publisher.LogRecordPublisher;
import com.francis.biz.log.frame.publisher.LogRecordPublisherFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * @author francis
 * @version 2023-08-21
 */
@Aspect
@Component
public class LogRecordAspect {

    @Pointcut("@annotation(com.francis.biz.log.frame.LogRecord)")
    public void around() {
    }

    @Around("around()")
    public Object logPointcut(ProceedingJoinPoint pjp) throws Throwable {
        setArgsContext(pjp.getArgs());

        LogRecord annotation = getAnnotation(pjp);

        if (annotation.needPre()) {
            LogRecordPublisher logRecordPublisher = LogRecordPublisherFactory.get(annotation.bizScenario());
            if (logRecordPublisher != null) {
                LogRecordContext.put(LogRecordContext.PRE, logRecordPublisher.getPreData());
            }
        }

        Object proceed = pjp.proceed();

        LogRecordContext.put(LogRecordContext.RESULT, proceed);
        sendOperationLog(annotation);
        return proceed;
    }

    private LogRecord getAnnotation(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        String methodName = pjp.getSignature().getName();
        Class<?> targetClass = pjp.getTarget().getClass();
        Class<?>[] parameterTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        return objMethod.getDeclaredAnnotation(LogRecord.class);
    }

    private void setArgsContext(Object[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof InputStreamSource) {
                LogRecordContext.put(LogRecordContext.ARG + i, null);
            } else {
                LogRecordContext.put(LogRecordContext.ARG + i, args[i]);
            }
        }
    }

    private void sendOperationLog(LogRecord annotation) {
        String bizScenario = annotation.bizScenario();
        LogEntity logEntity = LogEntity.builder()
                .content(annotation.content())
                .position(annotation.position())
                .type(annotation.type())
                .build();
        LogRecordPublisher logRecordPublisher = LogRecordPublisherFactory.get(bizScenario);
        if (logRecordPublisher != null) {
            logRecordPublisher.process(logEntity);
        }
    }

}
