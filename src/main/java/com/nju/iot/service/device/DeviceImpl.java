package com.nju.iot.service.device;

import com.nju.iot.dao.*;
import com.nju.iot.entity.*;
import com.nju.iot.payloads.DeviceInfo;
import com.nju.iot.payloads.ThingModelRequest;
import com.nju.iot.service.thingModel.ThingModelInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/17
 * @description:
 */
@Service
public class DeviceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThingModelInterface thingModelInterface;



    @Override
    public long addDevice(Long userId, DeviceType type) {
        if(!userRepository.existsById(userId)) {
            return -1;
        }

        User user = userRepository.findById(userId).get();
        Device device = new Device(user, type);
        deviceRepository.save(device);
        device.setTopic("test/" + device.getDeviceId());
        deviceRepository.save(device);
        return device.getDeviceId();
    }

    @Override
    public long addDevice(Long userId, DeviceType type, ThingModelRequest modelRequest) {

        if(!userRepository.existsById(userId)) {
            return -1;
        }

        User user = userRepository.findById(userId).get();
        ThingModel model = thingModelInterface.persistThingModel(modelRequest);
        Device device = new Device(user, type, model);
        deviceRepository.save(device);
        device.setTopic("test/" + device.getDeviceId());
        deviceRepository.save(device);
        return device.getDeviceId();
    }

    @Override
    public void deleteDevice(Long deviceId) {
        if(deviceRepository.existsById(deviceId)) {
            Device device = deviceRepository.findById(deviceId).get();
            device.setState(DeviceAction.DELETED);
            deviceRepository.save(device);
        }
    }

    @Override
    public void disableDevice(Long deviceId) {
        if(deviceRepository.existsById(deviceId)) {
            Device device = deviceRepository.findById(deviceId).get();
            device.setState(DeviceAction.DISABLED);
            deviceRepository.save(device);
        }
    }

    @Override
    public boolean enableDevice(Long deviceId) {
        if(deviceRepository.existsById(deviceId)) {
            Device device = deviceRepository.findById(deviceId).get();
            if(device.getState() == DeviceAction.DISABLED) {
                device.setState(DeviceAction.IN_USE);
                deviceRepository.save(device);
                return true;
            }
        }
        return false;
    }

    @Override
    public DeviceInfo getDeviceInfo(long deviceId) {
        if(!deviceRepository.existsById(deviceId)) {
            return new DeviceInfo();
        }
        Device device = deviceRepository.findById(deviceId).get();
        return new DeviceInfo(device);
    }
}
