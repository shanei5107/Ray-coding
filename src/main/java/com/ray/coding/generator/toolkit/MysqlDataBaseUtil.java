package com.ray.coding.generator.toolkit;

import com.ray.coding.core.pagination.Pagination;
import com.ray.coding.core.pagination.PaginationFactory;
import com.ray.coding.generator.base.AbstractDataBaseUtil;
import com.ray.coding.generator.conf.DbConfig;
import com.ray.coding.generator.core.entity.FieldInfo;
import com.ray.coding.generator.core.entity.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ray
 * @version V1.0.0
 * @description Mysql数据库操作工具类
 * @since 2021/3/15 16:55
 */
@Slf4j
@SuppressWarnings("unchecked")
@Component
public class MysqlDataBaseUtil extends AbstractDataBaseUtil
{
    @Autowired
    private DbConfig dbConfig;


    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName(dbConfig.getDriverClassName());
        return DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUserName(), dbConfig.getPassword());
    }

    /**
     * 分页获取表数据
     *
     * @param page          分页参数
     * @param schemaName    数据库名
     * @param tableName     表名 - 模糊查询
     * @param tableDescribe 表描述 - 模糊查询
     * @return {@link Pagination}
     */
    @Override
    public Pagination<TableInfo> getTables(Pagination page, String schemaName, String tableName, String tableDescribe)
    {
        // 获取分页下标
        Pagination pageInfo = PaginationFactory.getPaginationIndex(page);
        // 拼接sql条件
        StringBuilder sqlSb = new StringBuilder();
        sqlSb.append(" from information_schema.tables").append(" where table_schema = ?");
        if (tableName != null && tableName != "")
        {
            sqlSb.append(" and table_name LIKE concat('%','").append(tableName).append("','%')");
        }
        if (tableDescribe != null && tableDescribe != "")
        {
            sqlSb.append(" and table_comment LIKE concat('%','").append(tableDescribe).append("','%')");
        }
        String sql = sqlSb.toString();
        // 拼接执行sql
        String countSql = "select count(1) " + sql;
        String selectSql = "select table_name tableName, table_comment tableDescribe, table_rows dataRows, create_time createTime, update_time updateTime " + sql + " LIMIT ?,?";
        // 创建连接
        Connection connection = null;
        try
        {
            connection = getConnection();
            System.out.println("=============================  select tables begin  =================================================================");
            System.out.println("JDBC Connection Success in " + this.getClass().getName());
            // 查询记录总数
            PreparedStatement countPreparedStatement = connection.prepareStatement(countSql);
            countPreparedStatement.setString(1, schemaName);
            // 打印语句，方便排错
            System.out.println("====> print sql : " + countSql);
            System.out.println("====> parameters: " + schemaName);
            ResultSet result = countPreparedStatement.executeQuery();
            result.next();
            Long count = result.getLong(1);
            System.out.println("<====  Columns  : count(1)");
            System.out.println("<====     Rows  : " + count);
            // 获取分页数量
            List<TableInfo> list = new ArrayList<>();
            if (count > 0)
            {
                // 打印语句
                System.out.println("====> print sql : " + selectSql);
                System.out.println("====> parameters: " + schemaName + ", " + pageInfo.getBeginIndex() + ", " + pageInfo.getPageSize());
                System.out.println("<====  Columns  : tableName, tableDescribe, dataRows, createTime, updateTime");
                // 执行sql
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setString(1, schemaName);
                // 起始
                preparedStatement.setInt(2, pageInfo.getBeginIndex());
                // 每页限制数量
                preparedStatement.setInt(3, pageInfo.getPageSize());
                ResultSet resultSet = preparedStatement.executeQuery();
                // 封闭结果集
                while (resultSet.next())
                {
                    TableInfo tableInfo = new TableInfo();
                    String name = resultSet.getString("tableName");
                    String describe = resultSet.getString("tableDescribe");
                    Long dataRows = resultSet.getLong("dataRows");
                    String createTime = resultSet.getString("createTime");
                    String updateTime = resultSet.getString("updateTime");
                    tableInfo.setTableName(name);
                    tableInfo.setTableDescribe(describe);
                    tableInfo.setDataRows(dataRows);
                    tableInfo.setCreateTime(createTime);
                    tableInfo.setUpdateTime(updateTime);
                    list.add(tableInfo);
                    System.out.println("<====     Row   : " + name + ", " + describe + ", " + dataRows + ",  " + createTime + ", " + updateTime);
                }
                System.out.println("<====   Total   : " + count);
            }
            // 处理分页
            pageInfo.setRowTotal(count);
            pageInfo.setRows(list);
        }
        catch (ClassNotFoundException e)
        {
            log.error("未找到数据库连接驱动！", e);
        }
        catch (SQLException e)
        {
            log.error("执行sql出现错误！", e);
        }
        finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                    System.out.println("Close JDBC Connection Success");
                    System.out.println("=============================  select tables end  =================================================================\n");
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
        return pageInfo;
    }

    @Override
    public List<FieldInfo> getFields(String schemaName, String tableName)
    {
        String sql = " select column_name fieldName, column_comment fieldComment, data_type dataType, COLUMN_TYPE columnType from information_schema.columns"
                + " where table_schema = ?"
                + " and table_name = ?"
                + " order by ordinal_position";
        Connection connection = null;
        try
        {
            // 创建连接
            connection = getConnection();
            System.out.println("=============================  select fields begin  =================================================================");
            System.out.println("JDBC Connection Success in " + this.getClass().getName());
            // 获取PreparedStatement对象，设置参数
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, schemaName);
            preparedStatement.setString(2, tableName);
            // 打印sql
            System.out.println("====> print sql : " + sql);
            System.out.println("====> parameters: " + schemaName + ", " + tableName);
            System.out.println("<====  Columns  : fieldName, dataType, columnType, fieldComment");
            // 执行sql
            ResultSet resultSet = preparedStatement.executeQuery();
            // 获取结果集
            List<FieldInfo> result = new ArrayList<>();
            while (resultSet.next())
            {
                String fieldName = resultSet.getString("fieldName");
                String fieldComment = resultSet.getString("fieldComment");
                String dataType = resultSet.getString("dataType");
                String columnType = resultSet.getString("columnType");

                FieldInfo field = new FieldInfo();
                field.setFieldName(fieldName);
                field.setFieldComment(fieldComment);
                field.setDataType(dataType);
                field.setColumnType(columnType);
                result.add(field);

                System.out.println("<====     Row   : " + fieldName + ", " + dataType + ", " + columnType + ", " + fieldComment);
            }
            return result;
        }
        catch (ClassNotFoundException e)
        {
            log.error("未找到数据库连接驱动！", e);
        }
        catch (SQLException e)
        {
            log.error("执行sql出现错误！", e);
        }
        finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                    System.out.println("Close JDBC Connection Success");
                    System.out.println("=============================  select fields end  =================================================================\n");
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
        return null;
    }
}
