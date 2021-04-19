package com.nju.iot.payloads;

import com.nju.iot.entity.DeviceType;
import com.nju.iot.entity.ThingModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/20
 * @description:
 */
@ApiModel
public class ThingModelInfo {
    @ApiModelProperty(value = "物模型id")
    private int modelId;
    @ApiModelProperty(value = "物模型名称")
    private String modelName = "";
    @ApiModelProperty(value = "设备类型")
    private String deviceType;

    public ThingModelInfo() {
    }

    public ThingModelInfo(ThingModel model) {
        this.modelId = model.getModelId();
        this.modelName = model.getModelName();
        this.deviceType = model.getDeviceType().getS();
    }

    public int getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public String getDeviceType() {
        return deviceType;
    }
}
