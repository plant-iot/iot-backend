package com.nju.iot.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse extends Response {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ErrorResponse(int code, String msg){
        this.setCode(code);
        this.setMsg(msg);
    }

    public ErrorResponse(String msg){
        this.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.setMsg(msg);
    }
}
