package com.xwh.whblogcommon.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xwh.whblogcommon.enums.AppHttpCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;



@Schema(name = "R",title = "统一返回值")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class R extends HashMap<String, Object> {
    public static R ok() {
        R r = new R();
        r.put("code", AppHttpCodeEnum.SUCCESS.getCode());
        r.put("msg", AppHttpCodeEnum.SUCCESS.getMsg());
        return r;
    }

    public static R ok(Integer code) {
        R r = new R();
        r.put("code", code);
        r.put("msg", AppHttpCodeEnum.SUCCESS.getMsg());
        return r;
    }

    public static R ok(Integer code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R error() {
        R r = new R();
        r.put("code", AppHttpCodeEnum.SYSTEM_ERROR.getCode());
        r.put("msg", AppHttpCodeEnum.SYSTEM_ERROR.getMsg());
        return r;
    }

    public static R error(Integer code) {
        R r = new R();
        r.put("code", code);
        r.put("msg", AppHttpCodeEnum.SYSTEM_ERROR.getMsg());
        return r;
    }

    public static R error(Integer code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public R put(Object data) {
        this.put("data", data);
        return this;
    }

    public R setData(String sign, Object data) {
        this.put(sign, data);
        return this;
    }

    public <T> T getData(TypeReference<T> typeReference) {
        return JSON.parseObject(JSON.toJSONString(this.get("data")), typeReference);
    }

    public <T> T getData(String sign, TypeReference<T> typeReference) {
        return JSON.parseObject(JSON.toJSONString(this.get(sign)), typeReference);
    }
}
