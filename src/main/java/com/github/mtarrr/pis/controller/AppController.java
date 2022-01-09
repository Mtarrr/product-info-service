package com.github.mtarrr.pis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/health")
    public String health() {
        return "health";
    }




}
