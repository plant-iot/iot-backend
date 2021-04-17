package com.nju.iot.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * @author: xuan
 * @date: 2021/4/17 20:43
 * @description: 物模型，用来定义设备的服务
 */
@Entity
public class ThingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int modelId;

    @NotNull
    private String modelName = "";

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    public ThingModel() {
    }


    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public ThingModel(DeviceType deviceType) {
        String str = deviceType.getS().equals("sensor") ? "传感器" : "执行器";
        this.modelName = "标准" + str + "模板";
        this.deviceType = deviceType;
    }

    public ThingModel(String modelName, DeviceType deviceType) {
        this.modelName = modelName;
        this.deviceType = deviceType;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
}
