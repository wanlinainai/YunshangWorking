package com.atguigu.auth.controller;


import com.atguigu.auth.service.SysUserService;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysUser;
import com.atguigu.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-05-06
 */
@RestController
@RequestMapping("/admin/system/sysUser")
@Api(tags = "用户管理接口")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户的条件分页查询
     */
    @ApiOperation("用户条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable("page") Long page,
                        @PathVariable("limit") Long limit,
                        SysUserQueryVo sysUserQueryVo) {
        //创建page 对象
        Page<SysUser> pageParam = new Page<>(page, limit);
        //封装条件
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        String createTimeBegin = sysUserQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysUserQueryVo.getCreateTimeEnd();
        String username = sysUserQueryVo.getKeyword();
        //判断条件不等于空
        if (!StringUtils.isEmpty(username)) {
            wrapper.like(SysUser::getUsername,username)
                    .or()
                    .like(SysUser::getName,username)
                    .or()
                    .like(SysUser::getPhone,username);
        }
        if (!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge(SysUser::getCreateTime, createTimeBegin);
        }
        if (!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.le(SysUser::getCreateTime, createTimeEnd);
        }

        IPage<SysUser> pageModel = sysUserService.page(pageParam, wrapper);
        //调用mp的方法实现分页查询
        return Result.ok(pageModel);
    }

    /**
     * 获取用户
     */
    @ApiOperation("获取用户")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") Long id) {
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    /**
     * 添加用户
     */
    @ApiOperation("添加用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysUser sysUser) {
        boolean is_success = sysUserService.save(sysUser);
        if (is_success) {
            return Result.ok().message("添加成功");
        }else {
            return Result.fail().message("添加失败");
        }
    }

    /**
     * 更新用户
     */
    @ApiOperation("更新用户")
    @PutMapping("/update")
    public Result update(@RequestBody SysUser sysUser) {
        boolean is_success = sysUserService.updateById(sysUser);
        if (is_success) {
            return Result.ok().message("修改成功");
        }else{
            return Result.ok().message("修改失败");
        }
    }
    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable("id") Long id) {
        boolean is_success = sysUserService.removeById(id);
        if (is_success) {
            return Result.ok().message("删除成功");
        }else {
            return Result.fail().message("删除失败");
        }
    }

    @ApiOperation("更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@ApiParam("更新状态的用户id") @PathVariable("id") Long id,
                               @ApiParam("更新的状态(0:停用;1:可用)")@PathVariable("status") Integer status) {
        sysUserService.updateStatus(id, status);
        return Result.ok();
    }
}