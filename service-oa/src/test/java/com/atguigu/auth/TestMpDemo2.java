package com.atguigu.auth;

import com.atguigu.auth.service.SysRoleService;
import com.atguigu.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 16:36
 */
@SpringBootTest
public class TestMpDemo2 {
    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void getAll() {
        List<SysRole> list = sysRoleService.list();
        System.out.println(list);
    }

}
