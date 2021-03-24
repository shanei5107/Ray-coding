package com.ray.coding.core.pagination;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ray.coding.common.utils.RequestContextUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ray
 * @version V1.0.0
 * @description 分页参数工厂类
 * @since 2021/2/3 15:57
 */
public class PaginationFactory
{

    /**
     * 获取layUI默认传递的分页参数
     */
    public static Pagination getPagination()
    {
        return PaginationFactory.getPagination("page", "limit");
    }

    /**
     * 自定义获取分页参数
     *
     * @param currPageName 当前页的参数名称
     * @param pageSizeName 每页记录数的参数名称
     * @return 分页
     */
    public static Pagination getPagination(String currPageName, String pageSizeName)
    {
        return new Pagination(getCurrPageParameter(currPageName), getPageSizeParameter(pageSizeName));
    }

    /**
     * 获取layUI默认传递的分页参数
     *
     * @return 分页
     */
    public static Page getPage()
    {
        return PaginationFactory.getPage("page", "limit");
    }

    /**
     * 获取当前页
     *
     * @param currPageName 当前页的参数名称
     * @param pageSizeName 每页记录数的参数名称
     * @return 分页
     */
    public static Page getPage(String currPageName, String pageSizeName)
    {
        return new Page(getCurrPageParameter(currPageName), getPageSizeParameter(pageSizeName));
    }

    /**
     * 获取当前页
     *
     * @param currPageName 当前页的参数名称
     * @return 页码
     */
    private static int getCurrPageParameter(String currPageName)
    {
        HttpServletRequest request = RequestContextUtil.getRequest();
        int currPage = 1;
        String pageParam = request.getParameter(currPageName);
        if (StrUtil.isNotEmpty(pageParam))
        {
            currPage = Integer.parseInt(pageParam);
        }
        return currPage;
    }

    /**
     * 获取分页下标
     *
     * @param page 分页对象
     * @return limit开始以及结束索引
     */
    public static Pagination getPaginationIndex(Pagination page)
    {
        // 开始下标
        int pageSize = page.getPageSize();
        int beginIndex = (page.getPageNum() - 1) * pageSize;
        // 结束下标
        int endIndex = beginIndex + pageSize;
        // 设置下标值
        page.setBeginIndex(beginIndex);
        page.setEndIndex(endIndex);
        return page;
    }

    /**
     * 获取每页记录数
     *
     * @param pageSizeName 每页记录数的参数名称
     * @return 值
     */
    private static int getPageSizeParameter(String pageSizeName)
    {
        HttpServletRequest request = RequestContextUtil.getRequest();
        int pageSize = 10;
        String limitParam = request.getParameter(pageSizeName);
        if (StrUtil.isNotEmpty(limitParam))
        {
            pageSize = Integer.parseInt(limitParam);
        }
        return pageSize;
    }

}
