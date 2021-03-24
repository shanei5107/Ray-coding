package com.ray.coding.generator.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ray
 * @version V1.0.0
 * @description 数据源相关信息
 * @since 2021/2/3 11:48
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DbConfig
{

    private String driverClassName;

    private String userName;

    private String password;

    private String url;
}
