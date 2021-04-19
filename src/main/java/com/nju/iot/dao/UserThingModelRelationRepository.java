package com.nju.iot.dao;

import com.nju.iot.entity.User;
import com.nju.iot.entity.UserThingModelRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/20
 * @description:
 */
public interface UserThingModelRelationRepository extends JpaRepository<UserThingModelRelation, Long> {
    List<UserThingModelRelation> findDistinctByUser(User user);
}
