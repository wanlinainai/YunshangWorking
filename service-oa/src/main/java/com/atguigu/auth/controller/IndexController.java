package com.atguigu.auth.controller;

import com.atguigu.common.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/5 11:39
 */
@Api(tags = "后台登录管理器")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    //login
    @PostMapping("/login")
    public Result login() {
        //{"code":20000,"data":{"token":"admin-token"}}
        Map<String, Object> map = new HashMap<>();
        map.put("token","admin");
        return Result.ok(map);
    }
    //info
    @GetMapping("/info")
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", "admin");
        map.put("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        return Result.ok(map);
    }

    //logout
    @PostMapping("/logout")
    public Result logout() {
        return Result.ok();
    }
}
