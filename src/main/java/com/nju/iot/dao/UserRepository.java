package com.nju.iot.dao;

import com.nju.iot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: xiang
 * @date: 2021/4/13
 * @description:
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
