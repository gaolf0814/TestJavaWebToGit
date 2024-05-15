package com.example.demotest1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("hello,中国人");
        return("hello,中国人");
    }
}
