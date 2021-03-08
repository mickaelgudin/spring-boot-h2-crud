package com.spring.crud.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .info(new Info().title("CRUD API")
                .version(appVersion)
                 .contact(new Contact().name("Mickaelgudin")
                         .url("https://github.com/mickaelgudin/spring-boot-h2-crud"))
                .description("Back projet")
                .termsOfService("http://swagger.io/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}