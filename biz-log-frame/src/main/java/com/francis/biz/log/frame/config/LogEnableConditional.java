package com.francis.biz.log.frame.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * @author francis
 * @version 2023-08-21
 */
public class LogEnableConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("biz.log.enable");
        if (StringUtils.hasText(property)) {
            try {
                if (Boolean.parseBoolean(property)) {
                    return true;
                }
            } catch (Exception ignore) {
            }
        }
        return false;
    }
}
