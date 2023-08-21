package com.francis.biz.log.frame;

import com.francis.biz.log.frame.publisher.LogRecordPublisher;

import java.lang.annotation.*;

/**
 * @author francis
 * @version 2023-08-21
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogRecord {

    /**
     * 日志业务场景
     */
    String bizScenario() default LogRecordPublisher.COMMON;

    /**
     * 是否需要修改之前数据
     */
    boolean needPre() default false;

    /**
     * 日志内容
     */
    String content() default "";

    /**
     * 操作位置
     */
    String position() default "";

    /**
     * 操作类型
     */
    String type() default "";

}
