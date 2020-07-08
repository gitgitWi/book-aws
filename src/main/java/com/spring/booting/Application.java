package com.spring.booting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Main Class of Spring Boot

@EnableJpaAuditing // activate JPA Auditing (BaseTimeEntity)
// Annotation for auto-configuration of Spring Boot
// Must be on the "Top" of Project
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
//        run with internal WAS ; don't have to install Tomcat Server
        SpringApplication.run(Application.class, args);
    }
}
