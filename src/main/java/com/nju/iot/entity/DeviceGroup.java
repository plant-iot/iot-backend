package com.nju.iot.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/18
 * @description:
 */
@Entity
public class DeviceGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String groupName;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public DeviceGroup(User user, String groupName) {
        this.groupName = groupName;
        this.user = user;
    }

    public DeviceGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
