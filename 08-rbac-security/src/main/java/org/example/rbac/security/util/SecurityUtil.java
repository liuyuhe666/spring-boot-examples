package org.example.rbac.security.util;

import cn.hutool.core.util.ObjectUtil;
import org.example.rbac.security.common.Constants;
import org.example.rbac.security.vo.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
    /**
     * 获取当前登录用户用户名
     *
     * @return 当前登录用户用户名
     */
    public static String getCurrentUsername() {
        UserPrincipal currentUser = getCurrentUser();
        return ObjectUtil.isNull(currentUser) ? Constants.ANONYMOUS_NAME : currentUser.getUsername();
    }

    /**
     * 获取当前登录用户信息
     *
     * @return 当前登录用户信息，匿名登录时，为null
     */
    public static UserPrincipal getCurrentUser() {
        Object userInfo = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userInfo instanceof UserDetails) {
            return (UserPrincipal) userInfo;
        }
        return null;
    }
}
