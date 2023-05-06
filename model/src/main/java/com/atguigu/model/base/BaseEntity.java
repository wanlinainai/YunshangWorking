package com.atguigu.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 14:55
 */
@Data
//需要持久化操作时候需要实现Serializable接口
public class BaseEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableLogic
    @TableField("is_deleted")
    private int isDeleted;

    @TableField(exist = false)
    private Map<String, Object> param = new HashMap<>();
}
