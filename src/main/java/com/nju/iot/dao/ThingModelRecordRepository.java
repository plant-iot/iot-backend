package com.nju.iot.dao;

import com.nju.iot.entity.ThingModel;
import com.nju.iot.entity.ThingModelRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThingModelRecordRepository extends JpaRepository<ThingModelRecord, Integer> {
    boolean existsByModel(ThingModel model);

    List<ThingModelRecord> findDistinctByModel(ThingModel model);
}
