package com.ray.coding.generator.core.service;

import com.ray.coding.core.pagination.LayPage;

/**
 * @author ray
 * @version V1.0.0
 * @description 代码生成 service
 * @since 2021/2/3 11:58
 */
public interface GeneratorService
{
    /**
     * 获取数据库中的表
     *
     * @param tableName     表名
     * @param tableDescribe 表描述
     * @return 数据库中的表（分页）
     */
    LayPage getTables(String tableName, String tableDescribe);

    /**
     * 获取表所有字段
     *
     * @param tableName 表名
     * @return 表字段（全部）
     */
    LayPage getFields(String tableName);
}
