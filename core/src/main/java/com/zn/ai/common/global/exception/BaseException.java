package com.zn.ai.common.global.exception;

import lombok.Getter;

/**
 * 自定义基础异常抽象类,系统其他自定义异常类继承该类
 *
 * @author Hzn
 */
abstract class BaseException extends RuntimeException {

    /**
     * 异常码
     */
    @Getter
    protected final String code;

    /**
     * 异常信息
     */
    @Getter
    protected final String message;

    /**
     * 全参构造方法
     *
     * @param code    异常码
     * @param message 异常信息
     */
    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }

}