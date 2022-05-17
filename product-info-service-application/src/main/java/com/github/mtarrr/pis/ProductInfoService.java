package com.github.mtarrr.pis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties
@Slf4j
public class ProductInfoService {
    public static void main(String[] args) {
        SpringApplication.run(ProductInfoService.class, args);
        log.info("ProductInfoService started");
    }
}
