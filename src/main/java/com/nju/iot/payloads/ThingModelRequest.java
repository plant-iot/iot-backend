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
    @ApiModelProperty(value = "用户id")
    long userId;
    @ApiModelProperty(value = "物模型id")
    int modelId;
    @ApiModelProperty(value = "物模型名称")
    String modelName;
    @ApiModelProperty(value = "传感器/执行器")
    String type;
    @ApiModelProperty(value = "具体服务")
    List<String> services;
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public String getType() {
        return type;
    }

    public List<String> getServices() {
        return services;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
