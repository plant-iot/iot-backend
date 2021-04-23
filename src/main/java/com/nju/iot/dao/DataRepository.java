package com.nju.iot.dao;

import com.nju.iot.entity.Data;
import com.nju.iot.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: xiang
 * @date: 2021/4/13
 * @description:
 */
public interface DataRepository extends JpaRepository<Data, String> {
    List<Data> findDistinctByDevice(Device device);
}
