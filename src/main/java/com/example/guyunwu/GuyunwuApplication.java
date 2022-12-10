package com.example.guyunwu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GuyunwuApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuyunwuApplication.class, args);
    }

}
