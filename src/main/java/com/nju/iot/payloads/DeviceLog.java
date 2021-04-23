package com.nju.iot.payloads;

import com.nju.iot.entity.Device;
import com.nju.iot.entity.DeviceOnOffRecord;
import com.nju.iot.entity.OnOffAction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/21
 * @description:
 */
@ApiModel
public class DeviceLog {
    @ApiModelProperty(value = "设备id")
    private long id;
    @ApiModelProperty(value = "日志列表")
    private List<DeviceLogItem> itemList = new LinkedList<>();

    public DeviceLog() {
    }

    public DeviceLog(Device device, List<DeviceOnOffRecord> recordList) {
        this.id = device.getDeviceId();
        itemList = new LinkedList<>();
        itemList.add(new DeviceLogItem(device.getRegisterTime(), OnOffAction.ON));
        for(DeviceOnOffRecord record : recordList) {
            itemList.add(new DeviceLogItem(record));
        }
        Collections.sort(itemList);
    }

    public void addRecordItem(DeviceOnOffRecord record) {
        itemList.add(new DeviceLogItem(record));
        Collections.sort(itemList);
    }

    public long getId() {
        return id;
    }

    public List<DeviceLogItem> getItemList() {
        return itemList;
    }
}
