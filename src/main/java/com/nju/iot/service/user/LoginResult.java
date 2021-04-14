package com.nju.iot.service.user;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/14
 * @description:
 */
public enum LoginResult {
    SUCCESS("成功"),
    WRONG_PASSWORD("密码不正确"),
    USER_NOT_REGISTERED("用户不存在");

    private final String s;

    LoginResult(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
