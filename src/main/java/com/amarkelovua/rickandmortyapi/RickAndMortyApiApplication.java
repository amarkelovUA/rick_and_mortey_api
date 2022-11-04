package com.amarkelovua.rickandmortyapi;

import com.amarkelovua.rickandmortyapi.service.MovieCharacterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RickAndMortyApiApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext
                = SpringApplication.run(RickAndMortyApiApplication.class, args);

        applicationContext.getBean(MovieCharacterService.class).syncExternalCharacters();
    }
}
