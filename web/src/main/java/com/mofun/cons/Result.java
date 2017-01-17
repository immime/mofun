package com.mofun.cons;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by runmain on 2016/12/26.
 */
public class Result implements Serializable {
    private boolean success;
    private String msg;
    private List data = new ArrayList<>();

    private Result() {
    }

    public static Result ok(List data) {
        Result result = new Result();
        result.success = true;
        result.data = data;
        return result;
    }

    public static Result error(String errorMsg) {
        Result result = new Result();
        result.success = false;
        result.msg = errorMsg;
        return result;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
