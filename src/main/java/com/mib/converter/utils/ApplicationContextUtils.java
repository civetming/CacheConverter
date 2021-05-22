package com.mib.converter.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author ming
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getContext() {
        checkApplicationContext();
        return applicationContext;
    }


    public static <T> T getBean(String name) {
        checkApplicationContext();
        try {
            return (T) applicationContext.getBean(name);
        } catch (Exception e) {
            return null;
        }
    }


    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return (T) applicationContext.getBean(clazz);
    }

    private static void checkApplicationContext() {
        Assert.notNull(applicationContext, "ApplicationContext未注入");
    }
}
