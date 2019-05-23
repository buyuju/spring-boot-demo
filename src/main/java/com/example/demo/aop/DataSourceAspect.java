package com.example.demo.aop;

import com.example.demo.config.mybatis.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component("dataSourceAspect")
@EnableAspectJAutoProxy(exposeProxy=true)
public class DataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    public DataSourceAspect(){
        logger.debug("--> init TransactionInterceptor <--");
    }

    //@Before("execution(* com.yx.pineapple.service..*.*(..)) && @annotation(org.springframework.transaction.annotation.Transactional)")
    @Before("execution(* com.example.demo.service..*.*(..))")
    public void transactional(JoinPoint joinPoint) {
        MethodSignature ms = (MethodSignature)joinPoint.getSignature();
        if(ms == null){
            DataSourceContextHolder.read();
            return;
        }
        if(!ms.getMethod().isAnnotationPresent(Transactional.class)){
            DataSourceContextHolder.read();
            return;
        }
        String method = joinPoint.getSignature().getName();
        if (method != null && (method.startsWith("get") || method.startsWith("find") || method.startsWith("query"))){
            DataSourceContextHolder.read();
        } else {
            DataSourceContextHolder.write();
        }
    }
}
