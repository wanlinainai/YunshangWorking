package com.atguigu.auth;

import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 15:20
 */
@SpringBootTest
public class TestMpDemo1 {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Test
    public void getAll() {
        List<SysRole> lists = sysRoleMapper.selectList(null);
        Iterator<SysRole> iterator = lists.iterator();
        while (iterator.hasNext()) {
            SysRole next = iterator.next();
            System.out.println("数据内容:" + next);
        }
    }

    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleCode("role");
        sysRole.setRoleName("大张伟");
        sysRole.setDescription("我会唱阳光彩虹小白马");
        int rows = sysRoleMapper.insert(sysRole);
        System.out.println(rows);
        System.out.println(sysRole.getId());
        System.out.println(sysRole);
    }

    @Test
    public void update() {
        //根据id查询
        SysRole role = sysRoleMapper.selectById(10);
        //修改设置值
        role.setRoleName("美国警长布莱克");
        //修改
        int rows = sysRoleMapper.updateById(role);
        System.out.println(rows);
    }

    @Test
    public void deleteId() {
        int rows = sysRoleMapper.deleteById(10);
        System.out.println(rows);
    }

    //批量删除
    @Test
    public void testDeleteBatchIds() {
        int rows = sysRoleMapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(rows);
    }

    //条件查询
    @Test
    public void testQuery1() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name","普通管理员");
        List<SysRole> admin = sysRoleMapper.selectList(wrapper);
        for (SysRole sysRole : admin) {
            System.out.println(sysRole);
        }
    }

    @Test
    public void testQuery2() {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getRoleName,"普通管理员");
        List<SysRole> admin = sysRoleMapper.selectList(wrapper);
        for (SysRole sysRole : admin) {
            System.out.println(sysRole);
        }
    }
}
