package com.ray.coding.generator.core.entity;

import lombok.Data;

/**
 * @author ray
 * @version V1.0.0
 * @description 字段信息
 * @since 2021/2/3 11:53
 */
@Data
public class FieldInfo
{
    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段描述
     */
    private String fieldComment;

    /**
     * 字段sql类型
     */
    private String dataType;

    /**
     * 字段类型长度
     */
    private String columnType;

    /**
     * 是否为页面快速查询的字段
     */
    private boolean queryField;

}
