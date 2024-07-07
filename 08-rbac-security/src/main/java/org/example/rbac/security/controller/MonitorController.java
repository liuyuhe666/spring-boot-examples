package org.example.rbac.security.controller;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.rbac.security.common.ApiResponse;
import org.example.rbac.security.common.PageResult;
import org.example.rbac.security.common.Status;
import org.example.rbac.security.exception.SecurityException;
import org.example.rbac.security.payload.PageCondition;
import org.example.rbac.security.service.MonitorService;
import org.example.rbac.security.util.PageUtil;
import org.example.rbac.security.util.SecurityUtil;
import org.example.rbac.security.vo.OnlineUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/monitor")
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    /**
     * 在线用户列表
     *
     * @param pageCondition 分页参数
     */
    @GetMapping("/online/user")
    public ApiResponse onlineUser(PageCondition pageCondition) {
        PageUtil.checkPageCondition(pageCondition, PageCondition.class);
        PageResult<OnlineUser> pageResult = monitorService.onlineUser(pageCondition);
        return ApiResponse.ofSuccess(pageResult);
    }

    /**
     * 批量踢出在线用户
     *
     * @param names 用户名列表
     */
    @DeleteMapping("/online/user/kickout")
    public ApiResponse kickoutOnlineUser(@RequestBody List<String> names) {
        if (CollUtil.isEmpty(names)) {
            throw new SecurityException(Status.PARAM_NOT_NULL);
        }
        if (names.contains(SecurityUtil.getCurrentUsername())) {
            throw new SecurityException(Status.KICKOUT_SELF);
        }
        monitorService.kickout(names);
        return ApiResponse.ofSuccess();
    }
}
