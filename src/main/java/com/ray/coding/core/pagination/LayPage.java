package com.ray.coding.core.pagination;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ray.coding.core.enums.ErrorEnum;
import lombok.Data;

import java.util.List;

/**
 * @author ray
 * @version V1.0.0
 * @description 基于LayUI数据表格封装的分页模型
 * @since 2021/2/3 15:54
 */
@Data
public class LayPage
{
    private Integer code;

    private String msg;

    private Long count;

    private List data;

    /**
     * 构建LayUI数据表格分页模型
     *
     * @param page {@link Pagination}
     * @return 分页对象
     */
    public static LayPage buildPagination(Pagination page)
    {
        LayPage result = new LayPage();
        result.setCode(ErrorEnum.SUCCESS.code());
        result.setMsg(ErrorEnum.SUCCESS.label());
        result.setCount(page.getRowTotal());
        result.setData(page.getRows());
        return result;
    }

    /**
     * 构建LayUI数据表格分页模型
     *
     * @param page {@link IPage}
     * @return 分页对象
     */
    public static LayPage buildPagination(IPage page)
    {
        LayPage result = new LayPage();
        result.setCode(ErrorEnum.SUCCESS.code());
        result.setMsg(ErrorEnum.SUCCESS.label());
        result.setCount(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }

    /**
     * 构建LayUI数据表格分页模型
     *
     * @param data list集合
     * @return 分页对象
     */
    public static LayPage buildPagination(List data)
    {
        LayPage result = new LayPage();
        result.setCode(ErrorEnum.SUCCESS.code());
        result.setMsg(ErrorEnum.SUCCESS.label());
        result.setData(data);
        return result;
    }

    /**
     * 请求失败的结果
     *
     * @param msg 提示消息
     * @return 分页对象
     */
    public static LayPage fail(String msg)
    {
        LayPage result = new LayPage();
        result.setCode(ErrorEnum.FAIL.code());
        result.setMsg(msg);
        return result;
    }
}
