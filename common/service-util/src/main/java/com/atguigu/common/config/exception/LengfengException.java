package com.atguigu.common.config.exception;

import com.atguigu.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 21:42
 */
@Data
//@AllArgsConstructor
public class LengfengException extends RuntimeException{
    private Integer code;

    private String msg;

    public LengfengException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    //接受枚举类型对象
    public LengfengException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }

    public String toString() {
        return "LengfengException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
