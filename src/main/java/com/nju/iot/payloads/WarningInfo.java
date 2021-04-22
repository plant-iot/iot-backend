package com.nju.iot.payloads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: d_xin
 * @date: 2021/4/21
 * @description:
 */
@ApiModel
public class WarningInfo {
    @ApiModelProperty(value = "温度告警")
    private String temp_warn;
    @ApiModelProperty(value = "湿度告警")
    private String humi_warn;
    @ApiModelProperty(value = "CO2含量告警")
    private String co2_warn;
    @ApiModelProperty(value = "光照强度告警")
    private String light_warn;

    public WarningInfo() {
        this.temp_warn = "暂无告警";
        this.humi_warn = "暂无告警";
        this.co2_warn = "暂无告警";
        this.light_warn = "暂无告警";
    }

    public String getTemp_warn() {
        return temp_warn;
    }

    public void setTemp_warn(String temp_warn) {
        this.temp_warn = temp_warn;
    }

    public String getHumi_warn() {
        return humi_warn;
    }

    public void setHumi_warn(String humi_warn) {
        this.humi_warn = humi_warn;
    }

    public String getCo2_warn() {
        return co2_warn;
    }

    public void setCo2_warn(String co2_warn) {
        this.co2_warn = co2_warn;
    }

    public String getLight_warn() {
        return light_warn;
    }

    public void setLight_warn(String light_warn) {
        this.light_warn = light_warn;
    }
}
