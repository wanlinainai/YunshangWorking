package com.atguigu.auth.controller;

import com.atguigu.auth.service.SysRoleService;
import com.atguigu.common.config.exception.LengfengException;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.vo.AssginRoleVo;
import com.atguigu.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 16:42
 */
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
//  localhost:8800/admin/system/sysRole/findAll
public class SysRoleController {
    //service
    @Autowired
    private SysRoleService sysRoleService;

    //查询所有的角色
    @ApiOperation("查询所有的角色 ")
    @GetMapping("/findAll")
    public Result findAll() {
        List<SysRole> list = sysRoleService.list();
        try {
            int a = 10 / 0;
        } catch (Exception e) {
            throw new LengfengException(20001, "我是冷锋，一名资深的狙击手需要2.5秒，我2.3秒。");
        }
        return Result.ok(list);
    }

    //条件分页查询
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@ApiParam("分页数") @PathVariable("page") Long page,
                                @ApiParam("页大小") @PathVariable("limit") Long limit,
                                SysRoleQueryVo sysRoleQueryVo) {
        Page<SysRole> pageParam = new Page<>(page, limit);
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        //如果是不等于空
        if (!StringUtils.isEmpty(roleName)) {
            wrapper.like(SysRole::getRoleName, roleName);
        }
        wrapper.orderByDesc(SysRole::getCreateTime);
        //调用service方法使用
        IPage<SysRole> pageModel = sysRoleService.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }

    //添加角色
    @ApiOperation("添加角色")
    @PostMapping("/save")
    public Result save(@RequestBody SysRole role) {
        boolean is_success = sysRoleService.save(role);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //修改角色-根据id查询
    @ApiOperation("根据id查询")
    @GetMapping("get/{id}")
    public Result get(@ApiParam("角色id") @PathVariable("id") Long id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    //修改角色-最终修改
    @ApiOperation("修改角色")
    @PutMapping("/update")
    public Result update(@RequestBody SysRole role) {
        boolean is_success = sysRoleService.updateById(role);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //删除
    @ApiOperation("删除角色")
    @DeleteMapping("/remove/{id}")
    public Result remove(@ApiParam("角色id") @PathVariable("id") Long id) {
        boolean is_success = sysRoleService.removeById(id);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //批量删除
    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@ApiParam("角色数组") @RequestBody List<Long> idList) {
        boolean is_success = sysRoleService.removeByIds(idList);
        if (is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //https://github.com/wanlinainai/YunshangWorking.git
    //1、进入分配页面：获取已经分配的角色和全部角色，进行页面展示

    //查询所有角色和 当前用户所属角色
    @ApiOperation("获取角色")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable("userId") Long userId) {
        Map<String, Object> roleMap = sysRoleService.findRoleDataByUserId(userId);
        return Result.ok(roleMap);
    }

    //2、保存分配角色：删除之前的分配橘色和保存现在的分配角色
    @ApiOperation("为用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok().message("分配角色成功");
    }


}