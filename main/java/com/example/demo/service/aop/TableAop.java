package com.example.demo.service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component  
@Aspect
public class TableAop {

    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    public void point(){}

    @Before("point()")
    public void before(JoinPoint joinPoint){

        System.out.println("我是前置通知");
    }

    @AfterReturning(value = "execution(* com.example.demo.controller..*.*(..))",returning = "keys")
    public void after(JoinPoint joinPoint,Object keys){
        System.out.println("第一个后置通知："+keys);
    }

    @AfterReturning(value = "execution(* com.example.demo.controller..*.*(..))",returning = "keys",argNames = "keys")
    public void af(String keys){
        System.out.println("第二个后置通知："+keys);
    }


}
