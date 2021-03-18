package me.breakblog.util;

/**
 * @Author: tw.huang
 * @DateTime: 2020/5/25 23:49
 * @Description: TODO
 */
public enum ResultEnum {

    SUCCESS(1, "success"),
    FAILURE(0, "failure");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
