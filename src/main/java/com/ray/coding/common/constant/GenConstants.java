package com.ray.coding.common.constant;

import com.baomidou.mybatisplus.core.toolkit.StringPool;

import java.util.Arrays;
import java.util.List;

/**
 * @author ray
 * @version V1.0.0
 * @description 生成代码常量
 * @since 2021/2/3 13:58
 */
public interface GenConstants extends StringPool
{

    /**
     * 文件后缀
     */
    String DOT_HTML = ".html";
    String DOT_JS = ".js";
    String DOT_SQL = ".sql";

    /**
     * 生成模板  1.0.0只有MyBatis-Plus
     */
    List<String> TEMPLATE = Arrays.asList("MyBatis-Plus");

}
