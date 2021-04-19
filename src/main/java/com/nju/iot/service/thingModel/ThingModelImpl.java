package com.nju.iot.service.thingModel;

import com.nju.iot.dao.ThingModelRecordRepository;
import com.nju.iot.dao.ThingModelRepository;
import com.nju.iot.dao.ThingModelServiceRepository;
import com.nju.iot.entity.DeviceType;
import com.nju.iot.entity.ThingModel;
import com.nju.iot.entity.ThingModelRecord;
import com.nju.iot.entity.ThingModelService;
import com.nju.iot.payloads.ThingModelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xuan
 * @date: 2021/4/17 23:59
 * @description:
 */
@Service
public class ThingModelImpl implements ThingModelInterface {

    @Autowired
    private ThingModelRepository thingModelRepository;

    @Autowired
    private ThingModelServiceRepository thingModelServiceRepository;

    @Autowired
    private ThingModelRecordRepository thingModelRecordRepository;

    @Override
    public ThingModel analyseThingModel(ThingModelRequest request) {
        if(thingModelRepository.existsById(request.getModelId())) {
            return thingModelRepository.findById(request.getModelId()).get();
        }else {
            DeviceType type = DeviceType.getType(request.getType());
            ThingModel thingModel = new ThingModel(request.getModelName(), type);
            return thingModel;
        }
    }

    @Override
    public ThingModel persistThingModel(ThingModelRequest request) {

        ThingModel model = analyseThingModel(request);

        if(!thingModelRepository.existsById(model.getModelId())) {
            thingModelRepository.save(model);
            for(ThingModelService service: request.getServices()) {
                thingModelServiceRepository.save(service);
                ThingModelRecord record = new ThingModelRecord(model, service);
                thingModelRecordRepository.save(record);
            }
        }

        return model;
    }

    @Override
    public List<ThingModel> getThingModelList() {
        return thingModelRepository.findAll();
    }
}
