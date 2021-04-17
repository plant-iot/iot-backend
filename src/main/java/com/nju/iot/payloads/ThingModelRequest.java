package com.nju.iot.payloads;

import com.nju.iot.entity.ThingModelService;

import java.util.List;

/**
 * @author: xuan
 * @date: 2021/4/17 23:52
 * @description:
 */
public class ThingModelRequest {

    int modelId;

    String modelName;

    String type;

    List<ThingModelService> services;

    public int getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public String getType() {
        return type;
    }

    public List<ThingModelService> getServices() {
        return services;
    }
}
