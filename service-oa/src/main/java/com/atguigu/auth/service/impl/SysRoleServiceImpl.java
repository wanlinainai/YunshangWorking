package com.atguigu.auth.service.impl;

import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.auth.service.SysRoleService;
import com.atguigu.auth.service.SysUserRoleService;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysUserRole;
import com.atguigu.vo.AssginRoleVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 16:32
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleService sysUserRoleService;
    
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 1、查询所有角色和 当前用户所属角色
     *
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> findRoleDataByUserId(Long userId) {

        //查询所有的角色，返回list集合
//        List<SysRole> allRoleList = baseMapper.selectList(null);
        List<SysRole> allRoleList = sysRoleMapper.selectList(null);
        //根据用户id查询角色用户关系表，查询userid对应的所有的角色id
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, userId);
        List<SysUserRole> existUserRoleList = sysUserRoleService.list(wrapper);

        //从我们查询出来的用户id对应角色list集合，获取所有角色id
        List<Long> existRoleIdList = existUserRoleList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());

        //根据查询出的所有的角色id，找到对应的角色信息
        List<SysRole> assignRoleLists = new ArrayList<>();
        for (SysRole sysRole : allRoleList) {
            //比较
            if (existRoleIdList.contains(sysRole.getId())) {
                assignRoleLists.add(sysRole);
            }
        }
        //得到的两部分数据封装到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("assignRoleList", assignRoleLists);
        map.put("allRoleList", allRoleList);
        return map;
    }

    @Transactional
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //把用户之前的角色删除
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, assginRoleVo.getUserId());
        sysUserRoleService.remove(wrapper);

        //重新分配
        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        for (Long roleId : roleIdList) {
            if (StringUtils.isEmpty(roleId)) {
                continue;
            }
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
            //插入操作
            sysUserRoleService.save(sysUserRole);
        }
    }
}