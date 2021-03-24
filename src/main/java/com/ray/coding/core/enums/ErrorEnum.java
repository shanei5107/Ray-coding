package com.ray.coding.core.enums;

import lombok.Getter;

/**
 * @author ray
 * @description API 返回状态枚举
 * @since 2021/2/3 11:30
 */
public enum ErrorEnum
{
    OK(200, "请求处理成功"),
    SUCCESS(0, "请求处理成功"),
    FAIL(-1, "请求处理失败"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

    @Getter
    public Integer code;

    @Getter
    public String label;

    ErrorEnum(Integer code, String label)
    {
        this.code = code;
        this.label = label;
    }


    public Integer code()
    {
        return this.code;
    }

    public String label()
    {
        return this.label;
    }
}
