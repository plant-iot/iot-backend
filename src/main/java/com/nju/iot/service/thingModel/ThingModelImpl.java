package com.nju.iot.service.thingModel;

import com.nju.iot.dao.*;
import com.nju.iot.entity.*;
import com.nju.iot.payloads.ThingModelInfo;
import com.nju.iot.payloads.ThingModelRequest;
import com.nju.iot.service.connect.PushCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserThingModelRelationRepository userThingModelRelationRepository;

    private static final Logger logger = LoggerFactory.getLogger(ThingModelImpl.class);

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
            for(String name: request.getServices()) {
                ThingModelService service = thingModelServiceRepository.findById(name).get();
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

    @Override
    public ThingModelInfo persistThingModel(Long userId, ThingModelRequest request) {
        if(!userRepository.existsById(userId)) {
            return new ThingModelInfo();
        }
        ThingModel thingModel = persistThingModel(request);
        User user = userRepository.findById(userId).get();
        UserThingModelRelation relation = new UserThingModelRelation(user, thingModel);
        userThingModelRelationRepository.save(relation);
        return new ThingModelInfo(thingModel);
    }

    @Override
    public List<ThingModelInfo> getThingModel(Long userId) {
        List<ThingModelInfo> list = new LinkedList<>();

        if(userRepository.existsById(userId)) {
            User user = userRepository.findById(userId).get();
            list.add(getThingModelInfo(thingModelRepository.getOne(1)));
            list.add(getThingModelInfo(thingModelRepository.getOne(2)));
            List<UserThingModelRelation> relationList = userThingModelRelationRepository.findDistinctByUser(user);
            for(UserThingModelRelation relation : relationList) {
                list.add(getThingModelInfo(relation.getModel()));
            }
        }

        return list;
    }

    private ThingModelInfo getThingModelInfo(ThingModel thingModel) {
        ThingModelInfo thingModelInfo = new ThingModelInfo(thingModel);

        List<ThingModelRecord> recordList = thingModelRecordRepository.findDistinctByModel(thingModel);
        for(ThingModelRecord thingModelRecord : recordList) {
            thingModelInfo.addService(thingModelRecord.getService().getServiceName().getS());
        }

        return thingModelInfo;
    }
}
