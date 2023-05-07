package com.atguigu.auth.utils;

import com.atguigu.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
    /**
     * 使用递归方式构建菜单
     * @param sysMenusList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenusList) {
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenusList) {
            //入口（入穴申请）
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(getChildren(sysMenu, sysMenusList));
            }
        }
        return trees;
    }

    public static SysMenu getChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历所有菜单的数据
        for (SysMenu it : sysMenuList) {
            if (sysMenu.getId().longValue() == it.getParentId().longValue()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(getChildren(it, sysMenuList));
            }
        }
        return sysMenu;
    }
}
