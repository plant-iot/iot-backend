package com.nju.iot.service.data;

import com.nju.iot.payloads.DataRecord;
import com.nju.iot.payloads.DeviceInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/21
 * @description:
 */
public interface DataService {
    List<DataRecord> getData(long userId);

    List<DataRecord> getData(long userId, String dataType, String start, String end);
}
