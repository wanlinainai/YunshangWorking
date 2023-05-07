package com.atguigu.auth.service.impl;

import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.auth.service.SysRoleService;
import com.atguigu.model.system.SysRole;
import com.atguigu.vo.AssginRoleVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 16:32
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService{

    @Override
    public Map<String, Object> findRoleDataByUserId(Long userId) {
        return null;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {

    }
}
