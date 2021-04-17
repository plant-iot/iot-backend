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
    private int modelId;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name = "thing_model")
    private ThingModel model;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name = "service_name")
    private ThingModelService service;

    public ThingModelRecord() {
    }

    public ThingModelRecord(int modelId, ThingModel model, ThingModelService service) {
        this.modelId = modelId;
        this.model = model;
        this.service = service;
    }
}
