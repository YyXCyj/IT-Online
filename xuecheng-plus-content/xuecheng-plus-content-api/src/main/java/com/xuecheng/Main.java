package com.xuecheng;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author yy
 * @version 1.0
 */
@EnableSwagger2Doc
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        SpringApplication.run(Main.class,args);
    }
}