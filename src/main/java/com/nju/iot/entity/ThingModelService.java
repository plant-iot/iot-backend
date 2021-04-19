package com.nju.iot.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    ServiceName serviceName;

    @NotNull
    String description = "";

    @NotNull
    String unit = "";

    Double quantity = 0.0;

    public ThingModelService() {
    }

    public ThingModelService(int type, ServiceName name, String description, String unit, double quantity) {
        this.type = type;
        this.serviceName = name;
        this.description = name.getS();
        this.unit = unit;
        this.quantity = quantity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }

    public void setServiceName(ServiceName serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
