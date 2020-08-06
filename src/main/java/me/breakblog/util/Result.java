package me.breakblog.util;

import lombok.Data;

/**
 * @Author: tw.huang
 * @DateTime: 2020/5/25 23:33
 * @Description: TODO
 */
@Data
public class Result {

    private Integer code;

    private String msg;

    private Object data;

    public static Result success() {
        Result result = new Result();
        result.data = null;
        result.msg = ResultEnum.SUCCESS.getMsg();
        result.code = ResultEnum.SUCCESS.getCode();
        return result;
    }

    public static Result success(String msg) {
        Result result = new Result();
        result.data = null;
        result.msg = msg;
        result.code = ResultEnum.SUCCESS.getCode();
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.data = data;
        result.msg = ResultEnum.SUCCESS.getMsg();
        result.code = ResultEnum.SUCCESS.getCode();
        return result;
    }

    public static Result success(Object data, String msg) {
        Result result = new Result();
        result.data = data;
        result.msg = msg;
        result.code = ResultEnum.SUCCESS.getCode();
        return result;
    }

    public static Result failure() {
        Result result = new Result();
        result.data = null;
        result.msg = ResultEnum.FAILURE.getMsg();
        result.code = ResultEnum.FAILURE.getCode();
        return result;
    }

    public static Result failure(String msg) {
        Result result = new Result();
        result.data = null;
        result.msg = msg;
        result.code = ResultEnum.FAILURE.getCode();
        return result;
    }

}
