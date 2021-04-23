package com.nju.iot.payloads;

import com.nju.iot.entity.Device;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: xiang
 * @date: 2021/4/19
 * @description:
 */
@ApiModel
public class DeviceInfo {
    @ApiModelProperty(value = "设备id")
    private long id;
    @ApiModelProperty(value = "设备名称")
    private String name;
    @ApiModelProperty(value = "是否离线")
    private String onOff;
    @ApiModelProperty(value = "当前状态")
    private String state;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "设备类型")
    private String type;
    @ApiModelProperty(value = "用户id")
    private long userId;

    public DeviceInfo() {
    }

    public DeviceInfo(Device device) {
        this.id = device.getDeviceId();
        this.name = device.getDeviceName();
        if(device.getOnline()) {
            this.onOff = "在线";
        }else {
            this.onOff = "离线";
        }
        this.state = device.getState().getS();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createTime = df.format(device.getRegisterTime());
        this.type = device.getType().getS();
        this.userId = device.getUser().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnOff() {
        return onOff;
    }

    public void setOnOff(String onOff) {
        this.onOff = onOff;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
