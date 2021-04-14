package com.nju.iot.response;

import lombok.Data;

@Data
public class ResourceResponse extends Response {

    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    public ResourceResponse(Object data){
        this.setCode(200);
        this.setData(data);
    }

}
