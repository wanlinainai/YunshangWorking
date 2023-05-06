package com.atguigu.vo;

import java.io.Serializable;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 20:33
 */
public class SysRoleQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
