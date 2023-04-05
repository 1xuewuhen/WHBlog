package com.xwh.whblogcommon.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xwh.whblogcommon.enums.AppHttpCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(name = "R", title = "统一返回值")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> {


    @Schema(title = "状态码")
    private Integer code;
    @Schema(title = "消息")
    private String msg;
    @Schema(title = "返回值")
    private T data;

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <V> R<V> ok(V data) {
        R<V> r = new R<>(AppHttpCodeEnum.SUCCESS.getCode(), AppHttpCodeEnum.SUCCESS.getMsg());
        r.data = data;
        return r;
    }

    public static <V> R<V> ok(Integer code, V data) {
        R<V> r = new R<>(code, AppHttpCodeEnum.SUCCESS.getMsg());
        r.data = data;
        return r;
    }

    public static <V> R<V> ok(Integer code, String msg, V data) {
        R<V> r = new R<>(code, msg);
        r.data = data;
        return r;
    }

    public static <V> R<V> error(V data) {
        R<V> r = new R<>(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),AppHttpCodeEnum.SYSTEM_ERROR.getMsg());
        r.data = data;
        return r;
    }

    public static <V> R<V> error(Integer code, V data) {
        R<V> r = new R<>(code,AppHttpCodeEnum.SYSTEM_ERROR.getMsg());
        r.data = data;
        return r;
    }

    public static <V> R<V> error(Integer code, String msg, V data) {
        R<V> r = new R<>(code, msg);
        r.data = data;
        return r;
    }

   /* public static R ok() {
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
    }*/
}
