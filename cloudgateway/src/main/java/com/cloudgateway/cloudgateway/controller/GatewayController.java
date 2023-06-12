package com.cloudgateway.cloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/orderServiceFallback")
    public String fallbackStatus(){
        return "Order service is down";
    }

    @GetMapping("/productServiceFallback")
    public String fallbackProductStatus(){
        return "Product service is down";
    }
}
