package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot", "com.example.demo"})
@EnableJpaAuditing
@EnableScheduling
@EnableAuthorizationServer // Oauth권한 서버
@EnableResourceServer // API서버인증
@EntityScan
public class DemoApplication {
//    private static final String PROPERTIES = "spring.config.loaction=classpath:/google.yml";

    public static void main(String[] args) {
        ApiContextInitializer.init();
//        new SpringApplicationBuilder(DemoApplication.class).properties(PROPERTIES).run(args);
        SpringApplication.run(DemoApplication.class, args);
    }
}
