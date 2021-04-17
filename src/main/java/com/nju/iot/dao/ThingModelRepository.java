package com.nju.iot.dao;

import com.nju.iot.entity.ThingModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: xuan
 * @date: 2021/4/18 0:05
 * @description:
 */
public interface ThingModelRepository extends JpaRepository<ThingModel, Integer> {

}
