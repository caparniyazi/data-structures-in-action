package com.caparniyazi.ds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
public class DataStructuresInActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataStructuresInActionApplication.class, args);
    }

    @GetMapping
    public String greetings() {
        return "<h3><font face='verdana'>Thanks for evaluating Data Structures in Action REST API</font>" +
                "<p/>@Author: Niyazi.Çapar</h3>";
    }


}
