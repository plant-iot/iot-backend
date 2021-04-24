package com.nju.iot.service.data;

import com.nju.iot.dao.DataRepository;
import com.nju.iot.dao.DeviceRepository;
import com.nju.iot.entity.Data;
import com.nju.iot.entity.Device;
import com.nju.iot.payloads.DataRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: xiang
 * @date: 2021/4/21
 * @description:
 */
@Service
public class DataImpl implements DataService {
    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<DataRecord> getData(long userId) {
        List<DataRecord> recordList = new LinkedList<>();
        List<Device> deviceList = deviceRepository.findDistinctByUserId(userId);
        for(Device device : deviceList) {
            List<Data> dataList = dataRepository.findDistinctByDevice(device);
            for(Data data : dataList) {
                recordList.add(new DataRecord(device.getDeviceId(), data));
            }
        }
        Collections.sort(recordList);
        return recordList;
    }

    @Override
    public List<DataRecord> getData(long userId, Long deviceId, String dataType, String start, String end) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        try {
            startTime = LocalDateTime.parse(start + " 00:00:00", df);
        }catch (Exception e) {
        }
        try {
            endTime = LocalDateTime.parse(end + " 23:59:59", df);
        }catch (Exception e) {
        }

        List<DataRecord> recordList = getData(userId);
        Iterator<DataRecord> iterator = recordList.iterator();
        while(iterator.hasNext()) {
            DataRecord record = iterator.next();
            if(dataType != null && !dataType.isEmpty() && !dataType.equals(record.getDataType())) {
                iterator.remove();
            }if(deviceId != null && deviceId > 0 && record.getDeviceId() != deviceId) {
                iterator.remove();
            }else {
                LocalDateTime recordTime = LocalDateTime.parse(record.getTime(), df);
                if(startTime != null && recordTime.isBefore(startTime)) {
                    iterator.remove();
                }else if(endTime != null && recordTime.isAfter(endTime)) {
                    iterator.remove();
                }
            }
        }
        return recordList;
    }
}
