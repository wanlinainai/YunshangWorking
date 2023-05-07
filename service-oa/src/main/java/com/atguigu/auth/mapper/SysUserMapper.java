package com.atguigu.auth.mapper;

import com.atguigu.model.system.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.MapperConfig;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-05-06
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}