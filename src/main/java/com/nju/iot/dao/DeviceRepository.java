package com.nju.iot.dao;

import com.nju.iot.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: xiang
 * @date: 2021/4/13
 * @description:
 */
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
