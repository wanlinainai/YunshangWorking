package com.atguigu.auth.controller;


import com.atguigu.auth.service.SysMenuService;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysMenu;
import com.atguigu.vo.AssginMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-05-07
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询所有菜单和角色分配的菜单
     * @param roleId
     * @return
     */
    @ApiOperation("查询所有菜单和角色分配的菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        List<SysMenu> list = sysMenuService.findMenuByRoleId(roleId);
        return Result.ok(list);
    }

    @ApiOperation("角色分配菜单")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        boolean is_success = sysMenuService.doAssign(assginMenuVo);
        if (is_success) {
            return Result.ok();
        }else{
            return Result.fail().message("添加失败");
        }

    }

    /**
     * 菜单的列表接口
     */
    @ApiOperation("菜单列表")
    @GetMapping("/findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }

    /**
     * 新增菜单
     */
    @ApiOperation("新增菜单")
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu) {
        boolean is_success = sysMenuService.save(sysMenu);
        if (is_success) {
            return Result.ok().message("新增成功");
        }else{
            return Result.fail().message("新增失败");
        }
    }
    /**
     * 修改菜单
     */
    @ApiOperation("修改菜单")
    @PutMapping("/update")
    public Result update(@RequestBody SysMenu sysMenu) {
        boolean is_success = sysMenuService.updateById(sysMenu);
        if (is_success) {
            return Result.ok().message("修改成功");
        }else {
            return Result.fail().message("修改失败");
        }
    }

    /**
     * 删除菜单
     */
    @ApiOperation("删除菜单")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean is_success = sysMenuService.removeMenuById(id);
        if (is_success) {
            return Result.ok().message("删除成功");
        }else{
            return Result.fail().message("删除失败");
        }
    }

}

