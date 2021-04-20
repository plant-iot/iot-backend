package com.nju.iot.service.group;

import com.nju.iot.dao.DeviceGroupRelationRepository;
import com.nju.iot.dao.DeviceGroupRepository;
import com.nju.iot.dao.DeviceRepository;
import com.nju.iot.dao.UserRepository;
import com.nju.iot.entity.Device;
import com.nju.iot.entity.DeviceGroup;
import com.nju.iot.entity.DeviceGroupRelation;
import com.nju.iot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/18
 * @description:
 */
@Service
public class GroupImpl implements GroupService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeviceGroupRepository deviceGroupRepository;
    @Autowired
    private DeviceGroupRelationRepository deviceGroupRelationRepository;

    @Override
    public boolean addGroup(Long userId, String name, Long[] deviceIdList) {
        if(!userRepository.existsById(userId)) {
            return false;
        }
        User user = userRepository.findById(userId).get();

        if(deviceGroupRepository.existsByUserAndGroupName(user, name)) {
            return false;
        }

        DeviceGroup group = new DeviceGroup(user, name);
        deviceGroupRepository.save(group);

        if(deviceIdList != null) {
            for(Long deviceId : deviceIdList) {
                if(deviceRepository.existsById(deviceId)) {
                    Device device = deviceRepository.findById(deviceId).get();
                    DeviceGroupRelation relation = new DeviceGroupRelation(device, group);
                    deviceGroupRelationRepository.save(relation);
                }
            }
        }

        return true;
    }
}
