package com.nju.iot.dao;

import com.nju.iot.entity.DeviceGroup;
import com.nju.iot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/18
 * @description:
 */
public interface DeviceGroupRepository extends JpaRepository<DeviceGroup, Long> {

    boolean existsByUserAndGroupName(User user, String name);

    DeviceGroup findDistinctByUserAndGroupName(User user, String name);

    List<DeviceGroup> findDistinctByUserId(Long userId);
}
