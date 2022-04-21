package com.github.mtarrr.pis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties
public class ProductInfoService {
    public static void main(String[] args) {
        SpringApplication.run(ProductInfoService.class, args);
    }
}