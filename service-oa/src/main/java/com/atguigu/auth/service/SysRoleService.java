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
    Map<String, Object> findRoleDataByUserId(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
