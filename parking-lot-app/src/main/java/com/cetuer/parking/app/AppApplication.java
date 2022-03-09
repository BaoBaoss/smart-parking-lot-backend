package com.cetuer.parking.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * app端启动类
 *
 * @author Cetuer
 * @date 2022/3/6 12:08
 */
@MapperScan("com.cetuer.**.mapper")
@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
