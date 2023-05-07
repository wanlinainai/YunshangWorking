package com.atguigu.auth.service.impl;


import com.atguigu.auth.mapper.SysMenuMapper;
import com.atguigu.auth.service.SysMenuService;
import com.atguigu.auth.service.SysRoleMenuService;
import com.atguigu.auth.utils.MenuHelper;
import com.atguigu.common.config.exception.LengfengException;
import com.atguigu.model.system.SysMenu;
import com.atguigu.model.system.SysRoleMenu;
import com.atguigu.vo.AssginMenuVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-05-07
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenu> findNodes() {
        //查询到所有的数据
        List<SysMenu> sysMenusList = baseMapper.selectList(null);
        //构造树形结构
        List<SysMenu> resultMenu = MenuHelper.buildTree(sysMenusList);

        return resultMenu;
    }

    @Override
    public boolean removeMenuById(Long id) {
        //判断一下当前的菜单是否有下一层菜单？
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getParentId, id);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {//有下一层菜单
            throw new LengfengException(201, "菜单不可删除");
        }
        int result = baseMapper.deleteById(id);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<SysMenu> findMenuByRoleId(Long roleId) {
        //1 查询所有的菜单（status=1菜单可用）
        LambdaQueryWrapper<SysMenu> wrapperSysMenu = new LambdaQueryWrapper<>();
        wrapperSysMenu.eq(SysMenu::getStatus, 1);
        List<SysMenu> allSysMenuList = baseMapper.selectList(wrapperSysMenu);
        //2 角色id查询，查询角色菜单关系表里的角色id对应所有的菜单id
        LambdaQueryWrapper<SysRoleMenu> wrapperSysRoleMenu = new LambdaQueryWrapper<>();
        wrapperSysRoleMenu.eq(SysRoleMenu::getRoleId, roleId);
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuService.list(wrapperSysRoleMenu);

        //3 根据获取的菜单id，获取菜单对象
        List<Long> menuIdList = sysRoleMenuList.stream().map(c -> c.getMenuId()).collect(Collectors.toList());

        //3.1 菜单的id和所有菜单集合里的id进行比较，如果相同则封装，否则不封装。
        allSysMenuList.stream().forEach(item -> {
            if (menuIdList.contains(item.getId())) {
                item.setSelect(true);
            } else {
                item.setSelect(false);
            }
        });

        //Local variable 'sysMenuList' is redundant
        //4 返回规定格式菜单列表
        return MenuHelper.buildTree(allSysMenuList);
    }

    /**
     * 为角色分配菜单
     *
     * @param assginMenuVo
     */
    @Override
    public boolean doAssign(AssginMenuVo assginMenuVo) {
        Integer count = 0;
        //1 根据角色id去删除菜单角色表里分配数据
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId, assginMenuVo.getRoleId());
        sysRoleMenuService.remove(wrapper);
        //2 从参数里面获取角色新分配菜单id列表
        List<Long> menuIdList = assginMenuVo.getMenuIdList();
        for (Long menuId : menuIdList) {
            if (StringUtils.isEmpty(menuId)){
                continue;
            }
            SysRoleMenu rolePermission = new SysRoleMenu();
            rolePermission.setRoleId(assginMenuVo.getRoleId());
            rolePermission.setMenuId(menuId);
            //插入
            boolean save = sysRoleMenuService.save(rolePermission);
            if (save) {
                count++;
            }
        }
        if (count > 0) {
            return true;
        }else{
            return false;
        }
    }
}
