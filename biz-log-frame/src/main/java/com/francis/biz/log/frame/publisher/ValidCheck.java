package com.francis.biz.log.frame.publisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * @author francis
 * @version 2023-08-21
 */
@Component
public class ValidCheck {
    @Value("${biz.log.valid-check:null}")
    private String checkValidConfig;
    private static Method method = null;

    @PostConstruct
    public void postConstruct() {
        if (!StringUtils.hasText(checkValidConfig)) {
            return;
        }
        String[] split = checkValidConfig.split("#");
        if (split.length != 2) {
            return;
        }
        try {
            Class<?> aClass = Class.forName(split[0]);
            method = aClass.getMethod(split[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isValid() {
        if (method == null) {
            return true;
        }
        try {
            return (boolean) method.invoke(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
