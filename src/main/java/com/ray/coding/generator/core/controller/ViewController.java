package com.ray.coding.generator.core.controller;

import com.ray.coding.common.constant.SystemConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ray
 * @version V1.0.0
 * @description 视图控制器
 * @since 2021/2/3 14:48
 */
@Controller
public class ViewController
{
    public static final String PREFIX = SystemConstants.VIEW_PREFIX + "gen";

    @Value("${app.generator.superEntityClass:null}")
    private String superEntityClass;

    @Value("${app.generator.superEntityColumns:null}")
    private String superEntityColumns;

    @Value("${app.generator.author:autoCoding}")
    private String author;


    @GetMapping({"/", "/gen"})
    public String index(Model model)
    {
        model.addAttribute("superEntityClass", superEntityClass);
        model.addAttribute("superEntityColumns", superEntityColumns);
        model.addAttribute("author", author);
        return PREFIX + "/gen";
    }

    @GetMapping("/gen/info")
    public String info()
    {
        return PREFIX + "/gen_info";
    }

    @GetMapping("/gen/add")
    public String add()
    {
        return PREFIX + "/gen_add";
    }
}
