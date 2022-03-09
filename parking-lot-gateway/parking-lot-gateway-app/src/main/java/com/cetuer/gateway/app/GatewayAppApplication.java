package com.cetuer.gateway.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * app端网关启动类
 *
 * @author Cetuer
 * @date 2022/3/6 11:37
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GatewayAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAppApplication.class, args);
    }

}
