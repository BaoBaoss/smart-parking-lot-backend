package com.cetuer.parking.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Auth启动类
 *
 * @author Cetuer
 * @date 2021/12/16 18:44
 */
@EnableFeignClients(basePackages = "com.cetuer")
@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
