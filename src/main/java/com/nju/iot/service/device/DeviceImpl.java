package com.nju.iot.service.device;

import com.nju.iot.dao.*;
import com.nju.iot.entity.*;
import com.nju.iot.payloads.DeviceInfo;
import com.nju.iot.payloads.DeviceLog;
import com.nju.iot.payloads.ThingModelRequest;
import com.nju.iot.service.thingModel.ThingModelInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

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
    @Autowired
    private DeviceOnOffRecordRepository deviceOnOffRecordRepository;
    @Autowired
    private ThingModelRepository thingModelRepository;

    @Override
    public long addDevice(Long userId, DeviceType type, String deviceName, int modelId) {
        if(!userRepository.existsById(userId) || !thingModelRepository.existsById(modelId)) {
            return -1L;
        }

        User user = userRepository.findById(userId).get();
        Device device = new Device(user, type);
        device.setDeviceName(deviceName);
        device.setModel(thingModelRepository.findById(modelId).get());
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

    @Override
    public List<DeviceInfo> getDeviceInfoList(long userId) {
        List<DeviceInfo> deviceInfoList = new LinkedList<>();

        List<Device> deviceList = deviceRepository.findDistinctByUserId(userId);
        for(Device device : deviceList) {
            deviceInfoList.add(new DeviceInfo(device));
        }

        return deviceInfoList;
    }

    @Override
    public DeviceLog getDeviceLog(long deviceId) {
        if(!deviceRepository.existsById(deviceId)) {
            return new DeviceLog();
        }

        Device device = deviceRepository.findById(deviceId).get();
        if(device.getState() == DeviceAction.DELETED) {
            return new DeviceLog();
        }

        List<DeviceOnOffRecord> onOffRecordList = deviceOnOffRecordRepository.findDistinctByDevice(device);
        System.out.println("on off record list: " + onOffRecordList.size());
        DeviceLog deviceLog = new DeviceLog(device, onOffRecordList);

        System.out.println("device log");
//        System.out.println("id: " + deviceLog.);

        return deviceLog;
    }

    @Override
    public List<DeviceLog> getDeviceLogList(long userId) {
        List<DeviceLog> deviceLogList = new LinkedList<>();
        List<Device> deviceList = deviceRepository.findDistinctByUserId(userId);
        for(Device device : deviceList) {
            deviceLogList.add(getDeviceLog(device.getDeviceId()));
        }
        return deviceLogList;
    }
    
    @Override
    public String dataAnalysis() {
        String command = "python ./analysis/analysis.py";
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream(),"gbk"));
            String line;
            String str = "";
            while ((line = in.readLine()) != null) {
                str += line;
            }
            in.close();
            p.waitFor();
            if (p.exitValue() != 0) {
                System.out.println("执行失败");
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
