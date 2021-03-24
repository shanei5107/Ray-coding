package com.ray.coding.conf;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.pagehelper.PageInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ray
 * @version V1.0.0
 * @description mybatis-plus
 * @since 2021/2/3 10:49
 */
@Configuration
public class MybatisPlusConfig
{

    @Value("${spring.pagehelper.helperDialect}")
    private String helperDialect;

    /**
     * pageHelper的分页插件
     */
    @Bean
    public PageInterceptor pageInterceptor()
    {
        return new PageInterceptor();
    }

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor()
    {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        if (StrUtil.equals(helperDialect, DbType.DM.getDb()))
        {
            // 达梦数据库
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.DM));
        }
        else if (StrUtil.equals(helperDialect, DbType.ORACLE.getDb()))
        {
            // oracle
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE));
        }
        else
        {
            // 默认：mysql
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        }

        return interceptor;
    }


}
