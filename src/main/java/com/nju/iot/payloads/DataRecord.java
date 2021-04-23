package com.nju.iot.payloads;

import com.nju.iot.entity.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.format.DateTimeFormatter;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/21
 * @description:
 */
@ApiModel
public class DataRecord implements Comparable<DataRecord> {
    @ApiModelProperty(value = "设备id")
    private long deviceId;
    @ApiModelProperty(value = "时间")
    private String time;
    @ApiModelProperty(value = "数据类别")
    private String dataType;
    @ApiModelProperty(value = "数值")
    private double value;

    public DataRecord() {
    }

    public DataRecord(long deviceId, Data data) {
        this.deviceId = deviceId;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        time = df.format(data.getTime());
        this.dataType = data.getDataType().getS();
        this.value = data.getValue();
    }

    public long getDeviceId() {
        return deviceId;
    }

    public String getTime() {
        return time;
    }

    public String getDataType() {
        return dataType;
    }

    public double getValue() {
        return value;
    }

    @Override
    public int compareTo(DataRecord o) {
        if(!time.equals(o.getTime())) {
            return time.compareTo(o.getTime());
        }else if(!dataType.equals(o.getDataType())) {
            return dataType.compareTo(o.dataType);
        }
        return (int)(deviceId - o.getDeviceId());
    }
}
