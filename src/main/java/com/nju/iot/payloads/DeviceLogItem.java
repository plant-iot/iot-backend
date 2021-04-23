package com.nju.iot.payloads;

import com.nju.iot.entity.DeviceOnOffRecord;
import com.nju.iot.entity.OnOffAction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/21
 * @description:
 */
@ApiModel
public class DeviceLogItem implements Comparable<DeviceLogItem> {
    @ApiModelProperty(value = "时间")
    private String time;
    @ApiModelProperty(value = "操作")
    private String action;

    public DeviceLogItem() {
    }

    public DeviceLogItem(LocalDateTime time, OnOffAction action) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.time = df.format(time);
        this.action = action.getS();
    }

    public DeviceLogItem(DeviceOnOffRecord record) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        time = df.format(record.getTime());
        action = record.getAction().getS();
    }

    public String getAction() {
        return action;
    }

    public String getTime() {
        return time;
    }

    @Override
    public int compareTo(DeviceLogItem o) {
        return time.compareTo(o.getTime());
    }
}
