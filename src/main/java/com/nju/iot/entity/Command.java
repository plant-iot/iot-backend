package com.nju.iot.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/13
 * @description:
 */
@Entity
public class Command {
    @Id
    private String id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name = "device_id")
    private Device device;

    @NotNull
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private CommandType type;

    @NotNull
    private String command;

    public Command() {
    }

    public Command(Device device, LocalDateTime time, CommandType type, String command) {
        this.id = time + "-" + device.getDeviceId();
        this.device = device;
        this.time = time;
        this.type = type;
        this.command = command;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
