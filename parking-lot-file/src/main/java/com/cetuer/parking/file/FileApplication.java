package com.cetuer.parking.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 文件系统启动类
 *
 * @author Cetuer
 * @date 2022/2/12 15:21
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}
