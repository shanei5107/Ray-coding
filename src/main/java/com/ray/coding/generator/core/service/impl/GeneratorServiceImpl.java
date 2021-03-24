package com.ray.coding.generator.core.service.impl;

import com.baomidou.mybatisplus.annotation.DbType;
import com.ray.coding.core.pagination.LayPage;
import com.ray.coding.core.pagination.Pagination;
import com.ray.coding.core.pagination.PaginationFactory;
import com.ray.coding.generator.conf.DbConfig;
import com.ray.coding.generator.core.entity.FieldInfo;
import com.ray.coding.generator.core.entity.TableInfo;
import com.ray.coding.generator.core.service.GeneratorService;
import com.ray.coding.generator.toolkit.MysqlDataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ray
 * @version V1.0.0
 * @description 代码生成 service 实现类
 * @since 2021/2/3 11:58
 */
@Service
public class GeneratorServiceImpl implements GeneratorService
{
    @Autowired
    private MysqlDataBaseUtil dataBaseUtil;

    @Autowired
    private DbConfig dataSource;

    @Override
    public LayPage getTables(String tableName, String tableDescribe)
    {
        // 判断是否支持当前数据库
        if (!dataSource.getDriverClassName().contains(DbType.MYSQL.getDb())
                && !dataSource.getDriverClassName().contains(DbType.DM.getDb()))
        {
            return LayPage.fail("抱歉，暂不支持该数据库");
        }
        String schemaName = dataBaseUtil.getDbName(dataSource);
        Pagination page = PaginationFactory.getPagination();
        Pagination<TableInfo> data = dataBaseUtil.getTables(page, schemaName, tableName, tableDescribe);
        return LayPage.buildPagination(data);
    }

    @Override
    public LayPage getFields(String tableName)
    {
        String schemaName = dataBaseUtil.getDbName(dataSource);
        List<FieldInfo> list = dataBaseUtil.getFields(schemaName, tableName);
        return LayPage.buildPagination(list);
    }
}
