package com.atguigu.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "分配菜单")
@Data
public class AssginRoleVo {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    //因为一个用户id可以对应一个角色id ，也可以对应多个角色id 。
    @ApiModelProperty(value = "角色id列表")
    private List<Long> roleIdList;

}