package com.nju.iot.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author: xiang
 * @date: 2021/4/11
 * @description:
 */
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @NotNull
    private String deviceName = "";

    @NotNull
    private String topic = "";

    @NotNull
    private LocalDateTime registerTime;

    @Enumerated(EnumType.STRING)
    private DeviceAction state = DeviceAction.IN_USE;

    @NotNull
    private Boolean isOnline = true;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name = "thing_model")
    private ThingModel model;

    public Device() {
    }

    public Device(User user, DeviceType type) {
        this.user = user;
        this.type = type;
        this.registerTime = LocalDateTime.now();
        this.model = new ThingModel(type);
    }

    public Device(User user, DeviceType type, ThingModel model) {
        this.user = user;
        this.type = type;
        this.registerTime = LocalDateTime.now();
        this.model = model;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public ThingModel getModel() {
        return model;
    }

    public void setModel(ThingModel model) {
        this.model = model;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public DeviceAction getState() {
        return state;
    }

    public void setState(DeviceAction state) {
        this.state = state;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public boolean canSendCommand() {
        return type.isCanSendCommand();
    }
}
