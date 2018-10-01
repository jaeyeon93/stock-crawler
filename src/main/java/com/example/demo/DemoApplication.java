package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@EntityScan
public class DemoApplication {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(DemoApplication.class, args);
    }
}
