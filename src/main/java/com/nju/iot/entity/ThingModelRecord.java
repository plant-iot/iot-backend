package com.nju.iot.entity;

import javax.persistence.*;

/**
 * @author: xuan
 * @date: 2021/4/17 22:52
 * @description:
 */
@Entity
public class ThingModelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name = "thing_model")
    private ThingModel model;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name = "service_name")
    private ThingModelService service;

    public ThingModelRecord() {
    }

    public ThingModelRecord(ThingModel model, ThingModelService service) {
        this.model = model;
        this.service = service;
    }

    public ThingModel getModel() {
        return model;
    }

    public void setModel(ThingModel model) {
        this.model = model;
    }

    public ThingModelService getService() {
        return service;
    }

    public void setService(ThingModelService service) {
        this.service = service;
    }
}
