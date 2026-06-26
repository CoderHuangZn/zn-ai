package com.zn.ai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zn.ai.mapper")
public class AiCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCoreApplication.class, args);
    }

}
