package com.nju.iot.service.device;

import com.nju.iot.dao.DeviceRepository;
import com.nju.iot.dao.UserRepository;
import com.nju.iot.entity.Device;
import com.nju.iot.entity.DeviceType;
import com.nju.iot.entity.User;
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
}
