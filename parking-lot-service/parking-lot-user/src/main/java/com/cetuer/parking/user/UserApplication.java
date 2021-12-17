package com.cetuer.parking.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户启动类
 *
 * @author Cetuer
 * @date 2021/12/17 10:16
 */
@SpringBootApplication
@MapperScan("com.cetuer.**.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
