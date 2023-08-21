package com.francis.biz.log.frame;

import java.util.HashMap;
import java.util.Map;

/**
 * @author francis
 * @version 2023-08-21
 */
public class LogRecordContext {
    public static final String PRE = "preData";
    public static final String ARG = "arg";
    public static final String ARG_0 = "arg0";
    public static final String ARG_1 = "arg1";
    public static final String ARG_2 = "arg2";
    public static final String ARG_3 = "arg3";
    public static final String ARG_4 = "arg4";
    public static final String ARG_5 = "arg5";
    public static final String ARG_6 = "arg6";
    public static final String RESULT = "result";
    public static final String USER = "userInfo";

    private static final InheritableThreadLocal<Map<String, Object>> MAP = new InheritableThreadLocal<>();

    public static void put(String name, Object value) {
        if (MAP.get() == null) {
            MAP.set(new HashMap<>());
        }
        MAP.get().put(name, value);
    }

    public static Object getResult() {
        Map<String, Object> variableMap = MAP.get();
        return variableMap == null ? null : variableMap.get(RESULT);
    }

    public static Object getPre() {
        Map<String, Object> variableMap = MAP.get();
        return variableMap == null ? null : variableMap.get(PRE);
    }

    public static UserInfo getUser() {
        Map<String, Object> variableMap = MAP.get();
        return variableMap == null ? null : (UserInfo) variableMap.get(USER);
    }

    public static Object get(String key) {
        Map<String, Object> variableMap = MAP.get();
        return variableMap == null ? null : variableMap.get(key);
    }

    static Map<String, Object> getVariable() {
        Map<String, Object> variableMap = MAP.get();
        return variableMap == null ? new HashMap<>() : variableMap;
    }

    public static void clear() {
        if (MAP.get() != null) {
            MAP.get().clear();
        }
    }

}
