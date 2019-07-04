package com.example.demo.aop;

import com.example.demo.exception.ClientException;
import com.example.demo.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.rmi.ServerException;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LogsAop {

    @Pointcut("execution(* com.example.demo.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) {
        long beginTime = System.currentTimeMillis();
        //获取到请求的属性
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取到请求对象
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String className = signature.getDeclaringTypeName() + "." + method.getName() + "()";

        Object result = null;

        Object[] args = pjp.getArgs();

        log.info("Request start, ip{} , url: {}, method: {}, params: {}", request.getRemoteAddr(), request.getRequestURL(), className, args);
        try {
            result = pjp.proceed(args);
        } catch (ClientException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            long costMs = System.currentTimeMillis() - beginTime;
            log.info("Request end, ip{}, url: {}, method: {}, result: {}, taking {}ms", request.getRemoteAddr(), request.getRequestURL(), className, handleResult(result), costMs);
        }

        return result;
    }

    private Object handleResult(Object ori) {
        if (ori == null) {
            return null;
        }

        String json = ObjectMapperUtil.toJsonString(ori);
        if(json == null) {
            return ori;
        }

        @SuppressWarnings("unchecked")
        Map<Object, Object> map = (Map<Object, Object>) ObjectMapperUtil.getJsonToMap(json, Object.class, Object.class);
        Object tmp;
        String key = "data";
        if (map.containsKey(key)) {
            tmp = map.get(key);
            if (tmp != null) {
                map.put(key, "***");
            }
        }
        return ObjectMapperUtil.toJson(map);
    }
}