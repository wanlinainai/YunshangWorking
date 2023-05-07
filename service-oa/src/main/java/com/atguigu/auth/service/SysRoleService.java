package com.atguigu.auth.service;

import com.atguigu.model.system.SysRole;
import com.atguigu.vo.AssginRoleVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 16:31
 */
public interface SysRoleService extends IService<SysRole> {
    //1、查询所有角色和 当前用户所属角色
    Map<String, Object> findRoleDataByUserId(Long userId);

    //2、保存分配角色：删除之前的分配橘色和保存现在的分配角色
    void doAssign(AssginRoleVo assginRoleVo);
}
