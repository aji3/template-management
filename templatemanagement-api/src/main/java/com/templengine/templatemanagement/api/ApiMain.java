package com.templengine.templatemanagement.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@Controller
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.templengine.templatemanagement")
@ComponentScan(basePackages = "org.xlbean.xlapi")
@MapperScan("com.templengine.templatemanagement.dao")
public class ApiMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApiMain.class, args);
    }

}
