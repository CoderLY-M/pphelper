package com.xiaoli.basicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xiaoli.basicservice","com.xiaoli.servicebase","com.xiaoli.commonutils"})
public class BasicServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasicServiceApplication.class,args);
    }
}
