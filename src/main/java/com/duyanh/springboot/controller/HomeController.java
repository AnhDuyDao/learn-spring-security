package com.duyanh.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// RestController = Controller + ResponseBody
@RestController
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "Hello Backend Java";
    }
}
