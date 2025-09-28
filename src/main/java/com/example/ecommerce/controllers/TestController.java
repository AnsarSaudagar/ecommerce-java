package com.example.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("")
    public Object test(){

        Map<String, Object> data = new HashMap<>();
        data.put("test", 1);
        return data;
    }
}
