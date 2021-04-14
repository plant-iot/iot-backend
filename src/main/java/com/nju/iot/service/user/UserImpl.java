package com.nju.iot.service.user;

import com.nju.iot.dao.UserRepository;
import com.nju.iot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xiang
 * @date: 2021/4/14
 * @description:
 */
@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResult login(Long userId, String password) {
        if(!userRepository.existsById(userId)) {
            return LoginResult.USER_NOT_REGISTERED;
        }
        User user = userRepository.findById(userId).get();
        if(!user.getPassword().equals(password)) {
            return LoginResult.WRONG_PASSWORD;
        }
        return LoginResult.SUCCESS;
    }

    @Override
    public void logout(Long userId) {
    }
}
