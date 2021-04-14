package com.nju.iot.response;

import lombok.Data;

@Data
public class EmptyResponse extends ErrorResponse {

    public EmptyResponse(){
        super(0,"empty message");
    }
}
