package com.ray.coding.core.resp;

import com.ray.coding.core.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ray
 * @version V1.0.0
 * @description API 返回结果
 * @since 2021/2/3 11:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 成功
     *
     * @return {ApiResult}
     */
    public static ApiResult success()
    {
        return new ApiResult(true, ErrorEnum.OK.code(), ErrorEnum.OK.label(), "");
    }

    /**
     * 成功
     *
     * @return {ApiResult}
     */
    public static ApiResult success(String msg)
    {
        return new ApiResult(true, ErrorEnum.OK.code(), msg, "");
    }

    /**
     * 成功
     *
     * @return {ApiResult}
     */
    public static ApiResult success(Object data)
    {
        return new ApiResult(true, ErrorEnum.OK.code(), ErrorEnum.OK.label(), data);
    }

    /**
     * 成功
     *
     * @return {ApiResult}
     */
    public static ApiResult success(String msg, Object data)
    {
        return new ApiResult(true, ErrorEnum.OK.code(), msg, data);
    }

    /**
     * 错误
     *
     * @return ${ApiResult}
     */
    public static ApiResult error(String msg)
    {
        return new ApiResult(false, ErrorEnum.INTERNAL_SERVER_ERROR.code(), msg, "");
    }

    /**
     * 错误
     *
     * @return ${ApiResult}
     */
    public static ApiResult error(Integer code, String msg)
    {
        return new ApiResult(false, code, msg, "");
    }

    /**
     * 错误
     *
     * @return ${ApiResult}
     */
    public static ApiResult error(Integer code, String msg, Object data)
    {
        return new ApiResult(false, code, msg, data);
    }

    /**
     * 错误
     *
     * @return ${ApiResult}
     */
    public static ApiResult error(ErrorEnum errorEnum)
    {
        return new ApiResult(false, errorEnum.code, errorEnum.label, "");
    }

    /**
     * 自定义返回
     *
     * @return ${ApiResult}
     */
    public static ApiResult msg(Boolean success, Integer code, String msg, Object data)
    {
        return new ApiResult(success, code, msg, data);
    }

}
