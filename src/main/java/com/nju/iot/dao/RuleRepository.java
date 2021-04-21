package com.nju.iot.dao;

import com.nju.iot.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

/**
 * @author: d_xin
 * @date: 2021/4/20
 * @description:
 */
public interface RuleRepository extends JpaRepository<Rule, Long> {
    ArrayList<Rule> findDistinctByUserId(long user_id);
}
