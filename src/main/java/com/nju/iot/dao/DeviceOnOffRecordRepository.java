package com.nju.iot.dao;

import com.nju.iot.entity.Device;
import com.nju.iot.entity.DeviceOnOffRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: xiang
 * @date: 2021/4/17
 * @description:
 */
public interface DeviceOnOffRecordRepository extends JpaRepository<DeviceOnOffRecord, String> {
    List<DeviceOnOffRecord> findDistinctByDevice(Device device);
}
