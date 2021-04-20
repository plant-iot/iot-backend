package com.nju.iot.service.group;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/18
 * @description:
 */
public interface GroupService {
    boolean addGroup(Long userId, String name, Long[] deviceIdList);
}
