package com.ray.coding.conf.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.ray.coding.common.constant.SystemConstants;

import java.io.IOException;

/**
 * @author ray
 * @version V1.0.0
 * @description 自定义的序列化类
 * @since 2021/2/3 11:06
 */
public class CustomLongConverter extends StdSerializer<Long>
{
    public CustomLongConverter()
    {
        super(Long.class);
    }

    @Override
    public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        // 超过12个数字用String格式返回，由于js的number只能表示15个数字
        if (value.toString().length() > SystemConstants.JS_NUMBER_MAX_LENGTH)
        {
            jsonGenerator.writeString(value.toString());
        }
        else
        {
            jsonGenerator.writeNumber(value);
        }
    }
}
