package com.nju.iot.entity;

import javax.persistence.*;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/20
 * @description:
 */
@Entity
public class UserThingModelRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name = "thing_model")
    private ThingModel model;

    public UserThingModelRelation(User user, ThingModel model) {
        this.user = user;
        this.model = model;
    }

    public UserThingModelRelation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ThingModel getModel() {
        return model;
    }

    public void setModel(ThingModel model) {
        this.model = model;
    }
}
