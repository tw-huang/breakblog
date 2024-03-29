package top.twhuang.util;

/**
 * @Author: tw.huang
 * @DateTime: 2020/5/25 23:49
 * @Description: 返回值枚举类
 */
public enum ResultEnum {
    SUCCESS(1, "success"),
    FAILURE(0, "failure"),
    UNAUTHORIZED(401, "unAuthorized"),
    UNKNOWN_ERROR(501, "unknownError");

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
