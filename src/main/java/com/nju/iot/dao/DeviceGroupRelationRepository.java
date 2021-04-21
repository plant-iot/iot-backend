package com.nju.iot.dao;

import com.nju.iot.entity.Device;
import com.nju.iot.entity.DeviceGroup;
import com.nju.iot.entity.DeviceGroupRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/18
 * @description:
 */
public interface DeviceGroupRelationRepository extends JpaRepository<DeviceGroupRelation, Long> {
    List<DeviceGroupRelation> findDistinctByDeviceGroup(DeviceGroup group);
}
