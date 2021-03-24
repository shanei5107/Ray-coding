package com.ray.coding.generator.conf;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.ray.coding.common.constant.GenConstants;
import com.ray.coding.generator.core.model.GeneratorInfo;
import com.ray.coding.generator.engine.CodingTemplateEngine;
import org.springframework.stereotype.Component;

/**
 * @author ray
 * @version V1.0.0
 * @description Mybatis-Plus代码生成配置类
 * @since 2021/2/3 11:49
 */
@Component
public class GeneratorConfig
{

    /**
     * 生成信息
     */
    private GeneratorInfo generatorInfo;

    public GeneratorConfig initConfig(GeneratorInfo generatorInfo)
    {
        this.generatorInfo = generatorInfo;
        return this;
    }

    /**
     * 全局配置
     */
    public GlobalConfig initGlobalConfig()
    {
        GlobalConfig globalConfig = new GlobalConfig();
        // 开启 ActiveRecord 模式
        globalConfig.setActiveRecord(true);
        // 作者
        globalConfig.setAuthor(generatorInfo.getAuthor());
        // 生成路径
        globalConfig.setOutputDir(generatorInfo.getTempPath());
        // 文件覆盖
        globalConfig.setFileOverride(true);
        // 主键策略
        globalConfig.setIdType(IdType.INPUT);
        // 是否开启二级缓存
        globalConfig.setEnableCache(false);
        // 设置生成的service接口的名字的首字母是否为I
        globalConfig.setServiceName("%sService");
        // ResultMap
        globalConfig.setBaseResultMap(true);
        // 字段集合
        globalConfig.setBaseColumnList(true);
        // 时间类型采用Date
        globalConfig.setDateType(DateType.ONLY_DATE);
        // 是否打开文件夹
        globalConfig.setOpen(false);
        // 开启swagger
        globalConfig.setSwagger2(true);

        return globalConfig;
    }

    /**
     * 数据源配置
     */
    public DataSourceConfig initDataSourceConfig()
    {
        // 获取数据源配置
        DbConfig db = generatorInfo.getDataSource();
        String jdbcUrl = db.getUrl();

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName(db.getDriverClassName());
        dataSourceConfig.setUrl(jdbcUrl);
        dataSourceConfig.setUsername(db.getUserName());
        dataSourceConfig.setPassword(db.getPassword());

        // 数据库类型
        if (jdbcUrl.contains(DbType.ORACLE.getDb()))
        {
            dataSourceConfig.setDbType(DbType.ORACLE);
        }
        else if (jdbcUrl.contains(DbType.DM.getDb()))
        {
            dataSourceConfig.setDbType(DbType.DM);
        }
        else if (jdbcUrl.contains(DbType.MYSQL.getDb()))
        {
            dataSourceConfig.setDbType(DbType.MYSQL);
        }
        else
        {
            dataSourceConfig.setDbType(DbType.OTHER);
        }

        return dataSourceConfig;
    }

    /**
     * 策略配置
     */
    public StrategyConfig initStrategyConfig()
    {

        StrategyConfig strategyConfig = new StrategyConfig();
        // 驼峰转换
        strategyConfig.setControllerMappingHyphenStyle(true);
        // 数据库表映射到实体的命名策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // Lombok
        strategyConfig.setEntityLombokModel(true);
        // 是否生成实体时，生成字段注解
        strategyConfig.setEntityTableFieldAnnotationEnable(true);
        // 表前缀
        strategyConfig.setTablePrefix(generatorInfo.getTablePrefix());
        // 表名
        strategyConfig.setInclude(generatorInfo.getTableNames());
        // 基类继承
        if (StrUtil.isNotEmpty(generatorInfo.getSuperEntityClass()))
        {
            strategyConfig.setSuperEntityClass(generatorInfo.getSuperEntityClass());
        }
        // 公共字段
        if (StrUtil.isNotEmpty(generatorInfo.getSuperEntityColumns()))
        {
            String[] columns = StrUtil.split(generatorInfo.getSuperEntityColumns(), ",");
            if (ArrayUtil.isNotEmpty(columns))
            {
                strategyConfig.setSuperEntityColumns(columns);
            }

        }

        return strategyConfig;
    }

    /**
     * 包配置
     */
    public PackageConfig initPackageConfig()
    {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(generatorInfo.getPackageName() + StringPool.DOT + generatorInfo.getModuleName());
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setController("controller");
        packageConfig.setEntity("entity");
        packageConfig.setXml("mapper.xml");
        return packageConfig;
    }

    /**
     * 模板配置
     */
    public TemplateConfig initTemplateConfig()
    {
        TemplateConfig templateConfig = new TemplateConfig();
        String template = generatorInfo.getTemplate();
        String prefix = "/templates/ftl/";
        if (GenConstants.TEMPLATE.contains(template))
        {
            templateConfig.setController(prefix + template + "/controller.java");
            templateConfig.setEntity(prefix + template + "/entity.java");
            templateConfig.setService(prefix + template + "/service.java");
            templateConfig.setServiceImpl(prefix + template + "/serviceImpl.java");
            templateConfig.setMapper(prefix + template + "/mapper.java");
            templateConfig.setXml(prefix + template + "/mapper.xml");
        }
        return templateConfig;
    }

    /**
     * 模板引擎
     */
    public AbstractTemplateEngine initTemplateEngine()
    {
        CodingTemplateEngine templateEngine = new CodingTemplateEngine();
        templateEngine.setGeneratorInfo(generatorInfo);
        return templateEngine;
    }

}
