package com.cetuer.parking.menu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 菜单模块启动类
 *
 * @author Cetuer
 * @date 2021/11/30 22:43
 */
@MapperScan("com.cetuer.**.mapper")
@SpringBootApplication
public class MenuApplication {
    public static void main(String[] args) {
        SpringApplication.run(MenuApplication.class, args);
    }
}
