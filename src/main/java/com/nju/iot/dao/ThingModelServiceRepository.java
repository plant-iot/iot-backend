package com.nju.iot.dao;

import com.nju.iot.entity.ThingModelService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThingModelServiceRepository extends JpaRepository<ThingModelService, String> {
}
