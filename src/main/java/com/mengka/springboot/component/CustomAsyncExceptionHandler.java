package com.mengka.springboot.component;

import lombok.extern.log4j.Log4j2;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import java.lang.reflect.Method;

@Log4j2
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(final Throwable throwable, final Method method, final Object... obj) {
        log.info("-----syncExHandler----- Exception message - " + throwable.getMessage());
        log.info("-----syncExHandler----- Method name - " + method.getName());
        for (final Object param : obj) {
            log.info("-----syncExHandler----- Param - " + param);
        }
    }

}
