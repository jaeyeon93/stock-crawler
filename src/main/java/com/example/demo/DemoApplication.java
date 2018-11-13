package com.example.demo;

import com.example.demo.domain.Account;
import com.example.demo.domain.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot", "com.example.demo"})
@EnableJpaAuditing
@EnableScheduling
@EntityScan
public class DemoApplication {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner bootstrapTestAccount(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Account account = new Account();
            account.setPassword(passwordEncoder.encode("1234"));
            accountRepository.save(account);
        };
    }
}
