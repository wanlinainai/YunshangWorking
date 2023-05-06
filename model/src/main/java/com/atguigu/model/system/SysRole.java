package com.atguigu.model.system;

import com.atguigu.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 14:49
 */
@Data
@TableName("sys_role")
@ApiModel("角色")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //角色名称
    @TableField("role_name")
    private String roleName;

    //角色编码
    @TableField("role_code")
    private String roleCode;

    //描述
    @TableField("description")
    private String description;

}
