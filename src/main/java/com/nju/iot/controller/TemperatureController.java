package com.nju.iot.controller;

import com.nju.iot.entity.Data;
import com.nju.iot.service.rule_engine.re_temperature.RE_TemperatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: d_xin
 * @date: 2021/4/18
 * @description:
 */
@RestController
@RequestMapping("/re_temperature")
@Api(tags = "温度规则引擎")
public class TemperatureController {
    @Autowired
    private RE_TemperatureService re_TemperatureService;

    @GetMapping("/check_temp_and_warn")
    @ApiOperation("检测温度阈值和告警")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "温度数据")
    })
    public void checkTempThresholdAndWarn(Data data) {
        boolean is_overThre = re_TemperatureService.checkTempThreshold(data.getValue());
        if(is_overThre){
            int signal = 1;
            re_TemperatureService.sendTempWarningSignal(signal);
        }
    }
}
