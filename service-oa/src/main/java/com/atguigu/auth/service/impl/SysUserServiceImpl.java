package com.atguigu.auth.service.impl;

import com.atguigu.auth.mapper.SysUserMapper;
import com.atguigu.auth.service.SysUserService;
import com.atguigu.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-05-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    /**
     * 根据id修改状态
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(Long id, Integer status) {
        //先找到对象
        SysUser sysUser = baseMapper.selectById(id);
        //设置值
        sysUser.setStatus(status);
        //调用方法进行修改
        baseMapper.updateById(sysUser);
    }
}
