package com.ray.coding.common.utils;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author ray
 * @version V1.0.0
 * @description 应用上下文工具类
 * @since 2021/3/15 15:58
 */
public class RequestContextUtil
{

    /**
     * 获取 Request
     */
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取 response
     */
    public static HttpServletResponse getResponse()
    {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取 session
     */
    public static HttpSession getSession()
    {
        return getRequest().getSession();
    }

    /**
     * 获取 ServletContext
     */
    public static ServletContext getServletContext()
    {
        return Objects.requireNonNull(ContextLoader.getCurrentWebApplicationContext()).getServletContext();
    }

    /**
     * 获取 RequestAttributes
     */
    private static ServletRequestAttributes getRequestAttributes()
    {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }


}
