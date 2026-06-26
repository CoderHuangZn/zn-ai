package com.zn.ai.tool;

import com.zn.ai.common.response.Result;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class MathTool {

    /*
        @Tool Spring AI通用的工具注解，专注于将方法定义为AI可调用的函数，可应用于多种场景
     */
    @Tool(name = "addNumber", description = "两个数字相加,返回数字相加的和")
    public Result<BigDecimal> add(BigDecimal a, BigDecimal b) {
        return Result.success(a.add(b));
    }

    @Tool(name = "subtractNumber", description = "两个数字相减,返回数字相加的差")
    public Result<BigDecimal> sub(BigDecimal a, BigDecimal b) {
        return Result.success(a.subtract(b));
    }

    @Tool(name = "multiplyNumber", description = "两个数字相乘,返回数字相乘的积")
    public Result<BigDecimal> mul(BigDecimal a, BigDecimal b) {
        return Result.success(a.multiply(b));
    }

    @Tool(name = "divideNumber", description = "两个数字相除，返回数字相除的商")
    public Result<BigDecimal> div(BigDecimal a, BigDecimal b) {
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return Result.success(BigDecimal.ZERO);
        }
        return Result.success(a.divide(b, 2, RoundingMode.HALF_UP));
    }

}
