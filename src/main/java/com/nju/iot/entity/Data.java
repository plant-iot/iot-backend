package com.nju.iot.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/11
 * @description:
 */
@Entity
public class Data {
    @Id
    private String id;

    @NotNull
    private LocalDateTime time;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name = "device_id")
    private Device device;

    @Enumerated(EnumType.STRING)
    private DataType type;

    private Double value = 0.0;

    public Data() {
    }


    public Data(LocalDateTime time, Device device, DataType type, Double value) {
        this.time = time;
        this.device = device;
        this.id = time + "-" + device.getDeviceId() + "-" + type;
        this.type = type;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Device getDeviceInfo() {
        return device;
    }

    public void setDeviceInfo(Device device) {
        this.device = device;
    }

    public DataType getDataType() {
        return type;
    }

    public void setDataType(DataType dataType) {
        this.type = dataType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
