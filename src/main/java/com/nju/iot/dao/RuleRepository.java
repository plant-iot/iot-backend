package com.nju.iot.dao;

import com.nju.iot.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: d_xin
 * @date: 2021/4/20
 * @description:
 */
public interface RuleRepository extends JpaRepository<Rule, String> {
}
