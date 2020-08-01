package me.breakblog.util;

/**
 * @Author: tw.huang
 * @DateTime: 2020/5/25 23:49
 * @Description: TODO
 */
public enum ResultEnum {

    SUCCESS("1", "成功"),
    FAILURE("0", "失败");

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
