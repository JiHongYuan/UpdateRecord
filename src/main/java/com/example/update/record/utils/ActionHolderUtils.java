package com.example.update.record.utils;

import java.util.UUID;

/**
 * @author jihongyuan
 * @date 2019/12/7 2:13
 */
public class ActionHolderUtils {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String get() {
        if (StringUtils.isEmpty(threadLocal.get())) {
            threadLocal.set(renderAction());
        }

        return threadLocal.get();
    }

    public static void set() {
        threadLocal.set(renderAction());
    }

    public static void remove() {
        threadLocal.remove();
    }

    /**
     * 生成操作唯一标识
     */
    private static String renderAction() {
        return UUID.randomUUID() + "-" + System.currentTimeMillis();
    }

}
