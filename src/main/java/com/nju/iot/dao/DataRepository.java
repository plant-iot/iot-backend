package com.nju.iot.dao;

import com.nju.iot.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/13
 * @description:
 */
public interface DataRepository extends JpaRepository<Data, String> {
}
