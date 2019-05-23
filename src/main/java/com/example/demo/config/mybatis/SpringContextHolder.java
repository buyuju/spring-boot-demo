package com.example.demo.config.mybatis;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringContextHolder.applicationContext == null) {
            SpringContextHolder.applicationContext = applicationContext;
        }
    }

    //获取applicationContext
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}

