package com.Projekt.quizzbackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class QuizzBackend {

    public static void main(String[] args) {
        SpringApplication.run(QuizzBackend.class, args);




        // Hier können Sie Ihren Code ausführen
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}

