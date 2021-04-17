package com.nju.iot.service.thingModel;

import com.nju.iot.entity.ThingModel;
import com.nju.iot.entity.ThingModelService;
import com.nju.iot.payloads.ThingModelRequest;

import java.util.List;

public interface ThingModelInterface {

    ThingModel analyseThingModel(ThingModelRequest request);

    ThingModel persistThingModel(ThingModelRequest request);
}
