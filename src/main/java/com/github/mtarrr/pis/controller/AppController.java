package com.github.mtarrr.pis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AppController{

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

}
