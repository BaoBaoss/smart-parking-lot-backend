package com.cetuer.parking.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 菜单模块启动类
 *
 * @author Cetuer
 * @date 2021/11/30 22:43
 */
@EnableFeignClients(basePackages = "com.cetuer")
@MapperScan("com.cetuer.**.mapper")
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
