package com.nju.iot.entity;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/19
 * @description:
 */
public enum ServiceName {
    SUNSHINE_DOWN("调低光照"),
    SUNSHINE_UP("调高光照"),
    WATER("浇水"),;

    private final String s;

    ServiceName(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public static ServiceName getInstance(String s) {
        for(ServiceName name : ServiceName.values()) {
            if(name.getS().equals(s)) {
                return name;
            }
        }
        return null;
    }
}
