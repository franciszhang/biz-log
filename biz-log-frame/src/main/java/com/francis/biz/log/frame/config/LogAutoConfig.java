package com.francis.biz.log.frame.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author francis
 * @version 2023-08-21
 */
@Configuration
@ComponentScan(basePackages = {"com.francis.biz.log.frame"})
@Conditional(LogEnableConditional.class)
public class LogAutoConfig {
}
