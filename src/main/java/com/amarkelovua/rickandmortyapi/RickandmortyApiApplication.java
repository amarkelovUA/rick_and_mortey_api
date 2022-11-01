package com.amarkelovua.rickandmortyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RickandmortyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RickandmortyApiApplication.class, args);
    }

}
