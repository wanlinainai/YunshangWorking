package com.atguigu.common.result;


import lombok.Data;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 16:55
 */
@Data
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    /**
     * 私有化
     */
    private Result() {
    }

    //返回数据
    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(T data, Integer code, String message) {
        Result<T> result = build(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(data);
        result.setMessage(resultCodeEnum.getMessage());
        result.setCode(resultCodeEnum.getCode());
        return result;
    }


    //返回成功的方法
    public static<T> Result<T> ok() {
        return Result.ok(null);
    }

    public static<T> Result<T> ok(T data) {
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    //失败
    public static<T> Result<T> fail() {
        return fail(null);
    }

    public static <T> Result<T> fail(T data) {
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    public Result<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

}
