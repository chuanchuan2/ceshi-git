package com.cloud.consumer.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectCut {

    //定义切点
    @Pointcut("execution(public * com.cloud.consumer.aspectj.AserviceImpl.*(..))")
    public void pointCutHere(){}

    @Around("pointCutHere()")
    public Object doAroud(ProceedingJoinPoint pointCut) throws Throwable {
        System.out.println("环绕通知前");
        Object o=pointCut.proceed();
        System.out.println("环绕通知后");
        return o;
    }

}
