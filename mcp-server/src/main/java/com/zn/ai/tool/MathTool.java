package com.zn.ai.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class MathTool {

    @Tool(name = "addNumber", description = "两个数字相加,返回数字相加的和")
    public BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    @Tool(name = "subtractNumber", description = "两个数字相减,返回数字相加的差")
    public BigDecimal sub(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    @Tool(name = "multiplyNumber", description = "两个数字相乘,返回数字相乘的积")
    public BigDecimal mul(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    @Tool(name = "divideNumber", description = "两个数字相除，返回数字相除的商")
    public BigDecimal div(BigDecimal a, BigDecimal b) {
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return a.divide(b, 2, RoundingMode.HALF_UP);
    }

}
