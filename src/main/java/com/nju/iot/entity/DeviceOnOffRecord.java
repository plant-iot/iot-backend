package com.nju.iot.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/17
 * @description:
 */
@Entity
public class DeviceOnOffRecord {
    @Id
    private String id;
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name = "device_id")
    private Device device;
    @NotNull
    private LocalDateTime time;
    @Enumerated(EnumType.STRING)
    private OnOffAction action;

    public DeviceOnOffRecord() {
    }

    public DeviceOnOffRecord(Device device, OnOffAction action) {
        this.device = device;
        this.time = LocalDateTime.now();
        this.action = action;
        this.id = time + "-" + device.getDeviceId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public OnOffAction getAction() {
        return action;
    }

    public void setAction(OnOffAction action) {
        this.action = action;
    }
}
