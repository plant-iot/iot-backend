package com.nju.iot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import com.sun.istack.NotNull;

/**
 * @author: xuan
 * @date: 2021/4/17 21:45
 * @description:
 */
@Entity
public class ThingModelService {

    int type;

    @Id
    @NotNull
    String serviceName;

    @NotNull
    String description = "";

    @NotNull
    String unit = "";

    Double quantity = 0.0;

    public ThingModelService() {
    }

    public ThingModelService(int type, String name, String description, String unit, double quantity) {
        this.type = type;
        this.serviceName = name;
        this.description = description;
        this.unit = unit;
        this.quantity = quantity;
    }
}
