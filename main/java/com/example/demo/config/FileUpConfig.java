package com.example.demo.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileUpConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory multipartConfigFactory =new MultipartConfigFactory();

        multipartConfigFactory.setMaxFileSize("10240KB");
        multipartConfigFactory.setMaxRequestSize("10240KB");
        return multipartConfigFactory.createMultipartConfig();
    }
}
