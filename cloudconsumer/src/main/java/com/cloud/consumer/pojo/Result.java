package com.cloud.consumer.pojo;

import java.io.Serializable;

public class Result implements Serializable {
    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode(int i) {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
