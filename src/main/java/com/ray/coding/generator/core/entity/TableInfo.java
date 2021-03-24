package com.ray.coding.generator.core.entity;

import lombok.Data;

/**
 * @author ray
 * @version V1.0.0
 * @description 表信息
 * @since 2021/2/3 11:54
 */
@Data
public class TableInfo
{
    /**
     * 表名
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableDescribe;

    /**
     * 数据行
     */
    private Long dataRows;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建时间
     */
    private String updateTime;
}
