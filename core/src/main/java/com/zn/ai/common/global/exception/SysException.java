package com.zn.ai.common.global.exception;

/**
 * 系统异常
 *
 * @author Hzn
 */
public class SysException extends BaseException{

    /**
     * 全参构造方法
     *
     * @param code    异常码
     * @param message 异常信息
     */
    public SysException(String code, String message) {
        super(code, message);
    }

}