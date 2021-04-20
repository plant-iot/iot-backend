package com.nju.iot.payloads;

import com.nju.iot.entity.ThingModelService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xuan
 * @date: 2021/4/17 23:52
 * @description:
 */
@ApiModel
public class ThingModelRequest {
    @ApiModelProperty(value = "物模型id")
    int modelId;
    @ApiModelProperty(value = "物模型名称")
    String modelName;
    @ApiModelProperty(value = "传感器/执行器")
    String type;
    @ApiModelProperty(value = "具体服务")
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
