package com.atguigu.model.system;

import com.atguigu.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 15:07
 */
@Data
@ApiModel(description = "岗位")
@TableName("sys_post")
public class SysPost extends BaseEntity {
    //定义一个序列化版本号
    private static final long serialVersionUID =1L;

    @TableField("post_code")
    private String postCode;

    private String name;

    private String description;

    private int status;
}
