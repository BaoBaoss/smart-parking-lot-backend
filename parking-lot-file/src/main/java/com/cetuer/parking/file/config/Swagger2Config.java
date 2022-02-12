package com.cetuer.parking.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Swagger API配置
 *
 * @author Cetuer
 * @date 2021/12/8 10:39
 */
@Configuration
@EnableSwagger2WebMvc
public class Swagger2Config {

    @Bean(value = "fileApi")
    @Order(value = 1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cetuer.parking.file.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo groupApiInfo(){
        return new ApiInfoBuilder()
                .title("文件模块")
                .description("文件模块相关接口")
                //这里配置的是服务网站，暂时写了个人博客
                .termsOfServiceUrl("www.cetuer.com")
                .contact(new Contact("Cetuer", "www.cetuer.com", "1928160048@qq.com"))
                .version("1.0")
                .build();
    }
}