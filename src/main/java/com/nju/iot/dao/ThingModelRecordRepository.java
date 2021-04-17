package com.nju.iot.dao;

import com.nju.iot.entity.ThingModelRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThingModelRecordRepository extends JpaRepository<ThingModelRecord, Integer> {
}
