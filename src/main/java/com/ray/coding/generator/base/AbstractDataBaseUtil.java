package com.ray.coding.generator.base;

import com.ray.coding.common.constant.GenConstants;
import com.ray.coding.core.pagination.Pagination;
import com.ray.coding.generator.conf.DbConfig;
import com.ray.coding.generator.core.entity.FieldInfo;
import com.ray.coding.generator.core.entity.TableInfo;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ray
 * @version V1.0.0
 * @description 数据库操作规范抽象类
 * @since 2021/2/3 14:02
 */
@Component
public abstract class AbstractDataBaseUtil
{

    /**
     * 建立连接
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

    /**
     * 获取数据库中的表
     *
     * @param page          分页参数
     * @param schemaName    数据库名
     * @param tableName     表名 - 模糊查询
     * @param tableDescribe 表描述 - 模糊查询
     * @return 分页
     */
    public abstract Pagination<TableInfo> getTables(Pagination page, String schemaName, String tableName, String tableDescribe);

    /**
     * 获取表字段列表
     *
     * @param schemaName 数据库名
     * @param tableName  表名
     * @return 字段列表
     */
    public abstract List<FieldInfo> getFields(String schemaName, String tableName);

    /**
     * 获取数据库名称
     *
     * @param datasource 数据源配置
     * @return 数据库名称
     */
    public String getDbName(DbConfig datasource)
    {
        String jdbcUrl = datasource.getUrl();
        int start = jdbcUrl.lastIndexOf(GenConstants.SLASH) + 1;
        int end = jdbcUrl.indexOf(GenConstants.QUESTION_MARK);
        return jdbcUrl.substring(start, end);
    }
}
