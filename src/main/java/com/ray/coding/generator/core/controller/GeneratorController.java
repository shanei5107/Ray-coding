package com.ray.coding.generator.core.controller;

import cn.hutool.json.JSON;
import com.ray.coding.common.constant.GenConstants;
import com.ray.coding.common.utils.FileUtil;
import com.ray.coding.core.pagination.LayPage;
import com.ray.coding.generator.Generator;
import com.ray.coding.generator.conf.DbConfig;
import com.ray.coding.generator.core.model.ExtTemplates;
import com.ray.coding.generator.core.model.GeneratorInfo;
import com.ray.coding.generator.core.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ray
 * @version V1.0.0
 * @description 代码生成控制器
 * @since 2021/2/3 15:50
 */
@RestController
@RequestMapping("/gen")
public class GeneratorController
{
    @Autowired
    private GeneratorService generatorService;

    @Autowired
    private DbConfig dbConfig;

    @RequestMapping("list")
    public LayPage list(String tableName, String tableDescribe)
    {
        return generatorService.getTables(tableName, tableDescribe);
    }

    @RequestMapping("getFields")
    public LayPage getFields(String tableName)
    {
        return generatorService.getFields(tableName);
    }

    @RequestMapping("execute")
    public void execute(HttpServletResponse response, @RequestBody GeneratorInfo generatorInfo) throws Exception
    {
        // 设置数据源
        generatorInfo.setDataSource(dbConfig);
        // 构建生成器
        Generator generator = Generator.builder(generatorInfo);
        String path = generator.execute();
        // 下载并删除临时文件
        FileUtil.downloadFile(path + "auto-code.zip", "auto-code.zip", true, response);
        FileUtil.deleteFile(path);
    }

}
