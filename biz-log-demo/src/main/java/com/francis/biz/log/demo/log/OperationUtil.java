package com.francis.biz.log.demo.log;

import com.francis.biz.log.frame.LogRecordContext;

import java.util.Objects;

/**
 * @author francis
 * @version 2023-08-21
 */
public class OperationUtil {

    public static boolean isSuccess() {
        String s = Objects.requireNonNull(LogRecordContext.getResult()).toString();
        return s.contains("ok") || s.contains("success");
    }
}
