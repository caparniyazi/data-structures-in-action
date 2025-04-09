package com.caparniyazi.ds;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
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
@RequiredArgsConstructor
public class DataStructuresInActionApplication implements CommandLineRunner {
    // Data fields
    @Component
    @ConfigurationProperties(prefix = "myapp")
    @Getter
    @Setter
    public static class MyAppProperties {
        private String appName;
        private String appVersion;
        private String appDescription;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(DataStructuresInActionApplication.class);
    private final MyAppProperties props;

    public static void main(String[] args) {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(DataStructuresInActionApplication.class);
        springApplicationBuilder.profiles("dev");
        /*
         ApplicationStartedEvent: This is sent at the start.
         ApplicationEnvironmentPreparedEvent: This is sent when the environment is known.
         ApplicationPreparedEvent: This is sent after the bean definitions.
         ApplicationReadyEvent: This is sent when the application is ready.
         ApplicationFailedEvent: This is sent in case of exception during the startup.
         */
        springApplicationBuilder.listeners(event -> LOGGER.info("#### > {}", event.getClass().getCanonicalName()));
        springApplicationBuilder.run(args);
    }

    @Bean
    String info() {
        return "Hello World!";
    }

    /*
     Spring Boot order if you want to override application config properties:
    • Command-line arguments
    • SPRING_APPLICATION_JSON
    • JNDI (java:comp/env)
    • System.getProperties()
    • OS Environment variables
    • RandomValuePropertySource (random.*)
    • Profile-specific (application-{profile}.jar) outside the package jar.
    • Profile-specific (application-{profile}.jar) inside the package jar.
    • Application properties (application-dev.properties) outside the package jar.
    • Application properties (application-dev.properties) inside the package jar.
    • @PropertySource
    • SpringApplication.setDefaultProperties
     */

    // The way to access the property in the application.
    @Value("${server.port}")
    String serverPort;

    @GetMapping
    public String greetings() {
        return "<h3><font face='verdana'>Thanks for evaluating Data Structures in Action REST API</font></h3>" +
                "<hr><h4><p/>@Author: Niyazi.Çapar" +
                "<p/>@Email: capar.niyazi.tr@gmail.com</h4>";
    }

    @Override
    public void run(String... args) {
        LOGGER.info("## > CommandLineRunner implementation..");
        LOGGER.info("Accessing the info bean: {}", info());
        LOGGER.info("## > My Server Port is: {}", serverPort);
        LOGGER.info("## > My App description is: {}", props.getAppDescription());

        for (String arg : args) {
            LOGGER.info(arg);
        }
    }
}
