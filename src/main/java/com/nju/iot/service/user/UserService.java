package com.nju.iot.service.user;

/**
 * @author: xiang
 * @date: 2021/4/14
 * @description:
 */
public interface UserService {
    LoginResult login(Long userId, String password);

    void logout(Long userId);
}
