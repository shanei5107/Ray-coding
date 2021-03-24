package com.ray.coding.conf.web;

import com.ray.coding.common.constant.SystemConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ray
 * @version V1.0.0
 * @description 全局注册自定义的序列化配置类
 * @since 2021/2/3 11:04
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer
{

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        // 自定义Long类型转换
        builder.serializerByType(Long.class, new CustomLongConverter());
        builder.serializerByType(Long.TYPE, new CustomLongConverter());
        // 时间类型格式转换，默认为long
        builder.indentOutput(true).dateFormat(new SimpleDateFormat(SystemConstants.DATE_FORMAT));
        converters.add(0, new MappingJackson2HttpMessageConverter(builder.build()));

    }
}
