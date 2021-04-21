package com.nju.iot.service.thingModel;

import com.nju.iot.entity.ThingModel;
import com.nju.iot.entity.ThingModelService;
import com.nju.iot.payloads.ThingModelInfo;
import com.nju.iot.payloads.ThingModelRequest;

import java.util.List;

public interface ThingModelInterface {

    ThingModel analyseThingModel(ThingModelRequest request);

    ThingModel persistThingModel(ThingModelRequest request);

    List<ThingModel> getThingModelList();

    // 创建物模型
    ThingModelInfo persistThingModel(Long userId, ThingModelRequest request);

    // 查看物模型
    List<ThingModelInfo> getThingModel(Long userId);

    List<String> getDeviceThingModel(Long deviceId);
}
