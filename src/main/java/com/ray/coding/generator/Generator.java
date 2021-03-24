package com.ray.coding.generator;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.ray.coding.common.utils.CompressFileUtil;
import com.ray.coding.common.utils.FileUtil;
import com.ray.coding.generator.base.AbstractCodingGenerator;
import com.ray.coding.generator.conf.GeneratorConfig;
import com.ray.coding.generator.core.model.GeneratorInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;

/**
 * @author ray
 * @version V1.0.0
 * @description 代码生成器
 * @since 2021/3/15 17:46
 */
@Slf4j
public class Generator extends AbstractCodingGenerator
{
    /**
     * 构建 Generator
     *
     * @param generatorInfo 生成信息
     * @return Generator
     */
    public static Generator builder(GeneratorInfo generatorInfo)
    {
        Generator generator = new Generator();
        generator.init(generatorInfo);
        return generator;
    }

    @Override
    public String execute() throws Exception
    {
        // 设置生成临时路径
        String tmpDir = System.getProperty("java.io.tmpDir") + "rayCoding." + IdWorker.getIdStr();
        generatorInfo.setTempPath(tmpDir);
        // 配置MybatisPlus，并执行生成
        AutoGenerator autoGenerator = this.setMybatisPlusConfig(generatorInfo);
        autoGenerator.execute();
        log.info("====> 代码生成完毕！正在努力压缩文件中...");
        // 生成完成进行压缩处理
        if (FileUtil.validateFileDir(tmpDir))
        {
            FileOutputStream fos = new FileOutputStream(tmpDir + "auto-code.zip");
            CompressFileUtil.toZip(tmpDir, generatorInfo.getModuleName(), fos, true);
        }
        return tmpDir;
    }

    /**
     * mybatis plus 配置
     *
     * @param generatorInfo 生成信息
     * @return {@link AutoGenerator}
     */
    private AutoGenerator setMybatisPlusConfig(GeneratorInfo generatorInfo)
    {
        AutoGenerator generator = new AutoGenerator();
        // mybatis plus 配置
        GeneratorConfig config = new GeneratorConfig().initConfig(generatorInfo);
        generator.setGlobalConfig(config.initGlobalConfig());
        generator.setDataSource(config.initDataSourceConfig());
        generator.setStrategy(config.initStrategyConfig());
        generator.setPackageInfo(config.initPackageConfig());
        generator.setTemplate(config.initTemplateConfig());
        generator.setTemplateEngine(config.initTemplateEngine());
        return generator;
    }
}
