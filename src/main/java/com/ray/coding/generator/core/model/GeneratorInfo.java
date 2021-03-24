package com.ray.coding.generator.core.model;

import com.ray.coding.generator.conf.DbConfig;
import com.ray.coding.generator.core.entity.FieldInfo;
import lombok.Data;

import java.util.List;

/**
 * @author ray
 * @version V1.0.0
 * @description 代码生成相关信息
 * @since 2021/2/3 11:52
 */
@Data
public class GeneratorInfo
{
    /**
     * 数据源
     */
    private DbConfig dataSource;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表名称
     */
    private String[] tableNames;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 作者
     */
    private String author;

    /**
     * 生成模板
     */
    private String template;

    /**
     * 生成路径
     */
    private String tempPath;

    /**
     * 扩展模板
     */
    private List<ExtTemplates> extendTemplate;

    /**
     * 是否生成页面
     */
    private Boolean createPage = false;

    /**
     * 是否生成SQL
     */
    private Boolean createSql = true;

    /**
     * 是否使用Swagger
     */
    private Boolean createSwagger = true;

    /**
     * 字段信息
     */
    private List<FieldInfo> fields;

    /**
     * 基类
     */
    private String superEntityClass;

    /**
     * 通用公用字段
     */
    private String superEntityColumns;
}
