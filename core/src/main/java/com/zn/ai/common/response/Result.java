package com.zn.ai.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {
    /**
     * 状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 私有构造方法
     *
     * @param code    状态码
     * @param data    数据
     * @param message 消息
     */
    private Result(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.success = (code == ResultCode.SUCCESS.getCode());
    }

    /**
     * 私有构造方法（使用ResultCode枚举）
     *
     * @param resultCode 结果码枚举
     * @param data       数据
     * @param message    消息
     */
    private Result(ResultCode resultCode, T data, String message) {
        this(resultCode.getCode(), data, message != null ? message : resultCode.getMessage());
    }

    /**
     * 成功响应（带数据）
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return Result
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data, ResultCode.SUCCESS.getMessage());
    }

    /**
     * 成功响应（带数据和自定义消息）
     *
     * @param data    响应数据
     * @param message 自定义消息
     * @param <T>     数据类型
     * @return Result
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(ResultCode.SUCCESS, data, message);
    }

    /**
     * 成功响应（不带数据）
     *
     * @param <T> 数据类型
     * @return Result
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 成功响应（仅消息）
     *
     * @param message 响应消息
     * @param <T>     数据类型
     * @return Result
     */
    public static <T> Result<T> success(String message) {
        return new Result<>(ResultCode.SUCCESS, null, message);
    }

    /**
     * 失败响应（默认失败码）
     *
     * @param message 失败消息
     * @param <T>     数据类型
     * @return Result
     */
    public static <T> Result<T> fail(String message) {
        return new Result<>(ResultCode.FAIL, null, message);
    }

    /**
     * 失败响应（指定失败码）
     *
     * @param resultCode 失败码枚举
     * @param message    失败消息
     * @param <T>        数据类型
     * @return Result
     */
    public static <T> Result<T> fail(ResultCode resultCode, String message) {
        return new Result<>(resultCode, null, message);
    }

    /**
     * 失败响应（指定失败码和自定义消息）
     *
     * @param resultCode 失败码枚举
     * @param <T>        数据类型
     * @return Result
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result<>(resultCode, null, resultCode.getMessage());
    }

    /**
     * 失败响应（带数据）
     *
     * @param resultCode 失败码枚举
     * @param data       数据
     * @param message    消息
     * @param <T>        数据类型
     * @return Result
     */
    public static <T> Result<T> fail(ResultCode resultCode, T data, String message) {
        return new Result<>(resultCode, data, message);
    }

    /**
     * 参数错误响应
     *
     * @param message 错误消息
     * @param <T>     数据类型
     * @return Result
     */
    public static <T> Result<T> paramError(String message) {
        return fail(ResultCode.PARAM_ERROR, message);
    }

    /**
     * 未授权响应
     *
     * @param message 错误消息
     * @param <T>     数据类型
     * @return Result
     */
    public static <T> Result<T> unauthorized(String message) {
        return fail(ResultCode.UNAUTHORIZED, message);
    }

    /**
     * 禁止访问响应
     *
     * @param message 错误消息
     * @param <T>     数据类型
     * @return Result
     */
    public static <T> Result<T> forbidden(String message) {
        return fail(ResultCode.FORBIDDEN, message);
    }

    /**
     * 资源不存在响应
     *
     * @param message 错误消息
     * @param <T>     数据类型
     * @return Result
     */
    public static <T> Result<T> notFound(String message) {
        return fail(ResultCode.NOT_FOUND, message);
    }

    /**
     * 服务器内部错误响应
     *
     * @param message 错误消息
     * @param <T>     数据类型
     * @return Result
     */
    public static <T> Result<T> internalServerError(String message) {
        return fail(ResultCode.INTERNAL_SERVER_ERROR, message);
    }
}