package com.nju.iot.entity;

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

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    public ThingModel() {
    }

    public ThingModel(DeviceType deviceType, ThingModelService service) {
        this.deviceType = deviceType;
    }
}
