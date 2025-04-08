package com.caparniyazi.ds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBootApplication annotation is the @ComponentScan, @Configuration, and @EnableAutoConfiguration
 * annotations.It provides the bootstrap for the Spring Boot application that is executed in the main method.
 * You need to pass the class that is executed.
 */
@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
public class DataStructuresInActionApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DataStructuresInActionApplication.class);
        // Add your features here.
        // By default, Spring Boot looks for the banner.txt file in the classpath.
        app.run(args);
    }

    @GetMapping
    public String greetings() {
        return "<h3><font face='verdana'>Thanks for evaluating Data Structures in Action REST API</font></h3>" +
                "<hr><h4><p/>@Author: Niyazi.Çapar" +
                "<p/>@Email: capar.niyazi.tr@gmail.com</h4>";
    }
}
