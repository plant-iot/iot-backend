package com.nju.iot.payloads;

import com.nju.iot.entity.DeviceType;
import com.nju.iot.entity.ThingModel;
import com.nju.iot.entity.ThingModelRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: xiang
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
    @ApiModelProperty(value = "service列表")
    private List<String> serviceList = new LinkedList<>();

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

    public List<String> getServiceList() {
        return serviceList;
    }

    public void addService(String service) {
        this.serviceList.add(service);
    }
}
