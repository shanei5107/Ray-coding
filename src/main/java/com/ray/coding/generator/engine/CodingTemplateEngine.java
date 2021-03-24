package com.ray.coding.generator.engine;

import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.ray.coding.generator.core.entity.FieldInfo;
import com.ray.coding.generator.core.model.GeneratorInfo;
import com.ray.coding.generator.toolkit.TemplateUtil;

import java.util.List;
import java.util.Map;

/**
 * @author ray
 * @version V1.0.0
 * @description 重写 FreemarkerTemplateEngine
 * @since 2021/2/3 14:27
 */
public class CodingTemplateEngine extends FreemarkerTemplateEngine
{
    private GeneratorInfo generatorInfo;

    @Override
    public AbstractTemplateEngine batchOutput()
    {
        return super.batchOutput();
    }

    @Override
    public Map<String, Object> getObjectMap(TableInfo tableInfo)
    {
        Map<String, Object> objectMap = super.getObjectMap(tableInfo);
        // 首字母小写的实体类名称
        String lowerEntityName = TemplateUtil.lowerFirst(tableInfo.getEntityName());
        objectMap.put("lowerEntityName", lowerEntityName);
        // 首字母小写的service
        String lowerServiceName = TemplateUtil.lowerFirst(tableInfo.getServiceName());
        objectMap.put("lowerServiceName", lowerServiceName);
        // 首字母小写的mapper
        String lowerMapperName = TemplateUtil.lowerFirst(tableInfo.getMapperName());
        objectMap.put("lowerMapperName", lowerMapperName);
        // 主键名称、类型
        String keyPropertyName = "";
        String keyPropertyType = "";
        for (TableField field : tableInfo.getFields())
        {
            if (field.isKeyFlag())
            {
                keyPropertyName = field.getPropertyName();
                keyPropertyType = field.getPropertyType();
            }
        }
        objectMap.put("keyPropertyName", keyPropertyName);
        objectMap.put("keyPropertyType", keyPropertyType);
        return objectMap;
    }

    public void setGeneratorInfo(GeneratorInfo generatorInfo)
    {
        this.generatorInfo = generatorInfo;
    }
}
