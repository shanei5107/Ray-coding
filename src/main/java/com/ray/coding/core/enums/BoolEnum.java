package com.ray.coding.core.enums;

import com.ray.coding.core.annotation.IEnum;
import lombok.Getter;

/**
 * @author ray
 * @description 布尔枚举
 * @since 2021/2/3 11:46
 */
public enum BoolEnum implements IEnum<String>
{
    FALSE("F", 0, "否"),
    TRUE("T", 1, "是");

    @Getter
    private final String value;

    @Getter
    private final Integer key;

    @Getter
    private final String label;

    BoolEnum(String value, Integer key, String label)
    {
        this.value = value;
        this.key = key;
        this.label = label;
    }

    @Override
    public String value()
    {
        return this.value;
    }

    @Override
    public String label()
    {
        return this.label;
    }

    public Integer key()
    {
        return this.key;
    }

}
