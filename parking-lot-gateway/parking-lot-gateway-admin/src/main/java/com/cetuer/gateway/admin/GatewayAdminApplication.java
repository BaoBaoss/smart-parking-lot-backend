package com.cetuer.gateway.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 后台网关启动类
 *
 * @author Cetuer
 * @date 2021/12/5 16:13
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GatewayAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAdminApplication.class, args);
    }
}
