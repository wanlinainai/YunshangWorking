package com.atguigu.auth.service;

import com.atguigu.model.system.SysMenu;
import com.atguigu.vo.AssginMenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-05-07
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();

    boolean removeMenuById(Long id);

    List<SysMenu> findMenuByRoleId(Long roleId);

    boolean doAssign(AssginMenuVo assginMenuVo);

}
