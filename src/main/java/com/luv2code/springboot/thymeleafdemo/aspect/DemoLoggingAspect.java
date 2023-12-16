package com.luv2code.springboot.thymeleafdemo.aspect;

import jakarta.persistence.JoinColumn;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.JobKOctets;
import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){
    }

    @Pointcut("forControllerPackage()||forServicePackage()||forDaoPackage()")
    private void forAppFlow(){}


    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("====>> in @Before: calling method: " + method);

        Object[] args = joinPoint.getArgs();

        for(Object tempArg:args){
            myLogger.info("====>" + tempArg);
        }
    }

    @AfterReturning(
            pointcut ="forAppFlow()",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("====>> in @After: calling method: " + method);

        myLogger.info("====>> result: " + result);

    }
















}
