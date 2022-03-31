package com.xiaoli.clientservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author xiaoli
 * @Date 2022/3/26 20:15
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.xiaoli.clientservice","com.xiaoli.servicebase","com.xiaoli.commonutils"})
public class ClientServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientServerApplication.class,args);
    }
}
