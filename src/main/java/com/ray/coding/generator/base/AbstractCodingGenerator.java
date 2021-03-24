package com.ray.coding.generator.base;

import com.ray.coding.generator.core.model.GeneratorInfo;

/**
 * @author ray
 * @version V1.0.0
 * @description 代码生成抽象类
 * @since 2021/2/3 14:20
 */
public abstract class AbstractCodingGenerator
{
    /**
     * 生成信息
     */
    protected GeneratorInfo generatorInfo;

    /**
     * 初始化配置
     */
    public void init(GeneratorInfo generatorInfo)
    {
        this.generatorInfo = generatorInfo;
    }

    /**
     * 执行生成
     *
     * @return 临时路径
     * @throws Exception
     */
    public abstract String execute() throws Exception;

}
