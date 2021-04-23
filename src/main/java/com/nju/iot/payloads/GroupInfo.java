package com.nju.iot.payloads;

import com.nju.iot.entity.Device;
import com.nju.iot.entity.DeviceGroup;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: xiang
 * @date: 2021/4/21
 * @description:
 */
public class GroupInfo {
    @ApiModelProperty(value = "设备组id")
    private long groupId;
    @ApiModelProperty(value = "用户id")
    private long userId;
    @ApiModelProperty(value = "设备组名")
    private String name;
    @ApiModelProperty(value = "设备列表")
    private List<DeviceInfo> deviceInfoList = new LinkedList<>();

    public GroupInfo() {

    }

    public GroupInfo(DeviceGroup group, long userId) {
        this.groupId = group.getId();
        this.userId = userId;
        this.name = group.getGroupName();
        deviceInfoList = new LinkedList<>();
    }

    public void addDevice(Device device) {
        deviceInfoList.add(new DeviceInfo(device));
    }

    public long getGroupId() {
        return groupId;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<DeviceInfo> getDeviceInfoList() {
        return deviceInfoList;
    }
}
