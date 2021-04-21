package com.nju.iot.service.group;

import com.nju.iot.payloads.GroupInfo;

import java.util.List;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/18
 * @description:
 */
public interface GroupService {
    boolean addGroup(Long userId, String name, Long[] deviceIdList);

    // 查看设备组信息
    List<GroupInfo> getGroupInfoList(long userId);

    // 查看设备组信息
    GroupInfo getGroupInfo(long userId, String name);
}
