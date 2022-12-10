package com.example.guyunwu.utils;

/**
 * @author gu
 * @date 2020/12/15 16:00
 * @description SecurityUtil
 * @version 1.0
 * @since 1.0
 */
public class SecurityUtil {

    private static final ThreadLocal<Long> CURRENT_USER_ID = new ThreadLocal<>();

    public static Long getCurrentUserId() {
        return CURRENT_USER_ID.get();
    }

    public static void setCurrentUserId(Long id) {
        CURRENT_USER_ID.set(id);
    }

    public static void removeCurrentUser() {
        CURRENT_USER_ID.remove();
    }

    public static boolean isLogin() {
        return CURRENT_USER_ID.get() != null;
    }
}
