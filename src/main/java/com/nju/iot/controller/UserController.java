package com.nju.iot.controller;

import com.nju.iot.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiang
 * @date: 2021/4/14
 * @description:
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户登录、登出页面")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    public String login(long userId, String password) {
        return userService.login(userId, password).getS();
    }

    @GetMapping("/logout")
    @ApiOperation("用户登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id")
    })
    public void logout(long userId) {
        userService.logout(userId);
    }
}
