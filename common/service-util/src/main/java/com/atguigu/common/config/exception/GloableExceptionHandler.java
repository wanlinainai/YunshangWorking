package com.atguigu.common.config.exception;

import com.atguigu.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 21:31
 */
@ControllerAdvice
public class GloableExceptionHandler {

    //全局异常处理方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error() {
        return Result.fail().message("------执行了全局异常处理------");
    }

    //指定异常处理方法
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException exception) {
        return Result.fail().message("------执行了指定异常处理------");
    }

    //自定义异常
    @ExceptionHandler(LengfengException.class)
    @ResponseBody
    public Result error(LengfengException exception) {
        return Result.fail().message(exception.getMsg());
    }
}
