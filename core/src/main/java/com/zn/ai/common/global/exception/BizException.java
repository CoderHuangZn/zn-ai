package com.zn.ai.common.global.exception;

/**
 * 业务异常
 *
 * @author Hzn
 */
public class BizException extends BaseException {

    /**
     * 全参构造方法
     *
     * @param code    异常码
     * @param message 异常信息
     */
    public BizException(String code, String message) {
        super(code, message);
    }

}