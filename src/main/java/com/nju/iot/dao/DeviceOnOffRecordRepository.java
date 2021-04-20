package com.nju.iot.dao;

import com.nju.iot.entity.DeviceOnOffRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: xiang
 * @date: 2021/4/17
 * @description:
 */
public interface DeviceOnOffRecordRepository extends JpaRepository<DeviceOnOffRecord, String> {
}
