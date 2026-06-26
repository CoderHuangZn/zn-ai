package com.zn.ai.common.global;

import com.zn.ai.common.global.exception.BizException;
import com.zn.ai.common.global.exception.SysException;
import com.zn.ai.common.response.Result;
import com.zn.ai.common.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<Void> handleNoHandlerFound(NoHandlerFoundException e) {
        return Result.notFound(ResultCode.NOT_FOUND.getMessage());
    }

    /**
     * BizException 业务异常处理
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> bizExceptionHandler(BizException e) {
        // 业务异常使用WARN级别
        log.warn("BizException with error code:{}, error message:{}", e.getCode(), e.getMessage(), e);
        return Result.internalServerError(e.getMessage());
    }

    /**
     * SysException 系统异常处理
     */
    @ExceptionHandler(SysException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> sysExceptionHandler(SysException e) {
        // 系统相关异常使用error打印
        log.error("SysException with error code:{}, error message:{}", e.getCode(), e.getMessage(), e);
        return Result.fail(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    /**
     * 参数校验校验错误异常捕获
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> validExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        // 提取错误提示信息
        String errorMessage = allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
        log.warn("MethodArgumentNotValidException error message:{}", errorMessage);
        return Result.paramError(errorMessage);
    }

    /**
     * 未捕获到自定义的异常,兜底捕获
     *
     * @param e 异常信息
     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Result<?> exceptionHandler(Exception e) {
//        log.error("全局异常捕获", e);
//        return Result.fail(ResultCode.FAIL, ResultCode.FAIL.getMessage());
//    }

}

