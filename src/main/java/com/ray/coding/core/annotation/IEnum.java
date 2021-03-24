package com.ray.coding.core.annotation;

/**
 * @author ray
 * @version V1.0.0
 * @description 枚举
 * @since 2021/2/3 11:32
 */
public interface IEnum<T>
{
    /**
     * 值
     *
     * @return 值
     */
    T value();

    /**
     * 描述
     *
     * @return 描述
     */
    String label();
}
