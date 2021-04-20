package com.nju.iot.entity;

import javax.persistence.*;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/18
 * @description:
 */
@Entity
public class DeviceGroupRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private DeviceGroup deviceGroup;

    public DeviceGroupRelation() {
    }

    public DeviceGroupRelation(Device device, DeviceGroup deviceGroup) {
        this.device = device;
        this.deviceGroup = deviceGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public DeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    public void setDeviceGroup(DeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }
}
