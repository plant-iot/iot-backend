package com.nju.iot.dao;

import com.nju.iot.entity.UserThingModelRelation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/20
 * @description:
 */
public interface UserThingModelRelationRepository extends JpaRepository<UserThingModelRelation, Long> {
}
