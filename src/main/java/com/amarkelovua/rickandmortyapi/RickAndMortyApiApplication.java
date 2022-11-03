package com.amarkelovua.rickandmortyapi;

import com.amarkelovua.rickandmortyapi.service.MovieCharacterService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RickAndMortyApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext
                = SpringApplication.run(RickAndMortyApiApplication.class, args);

        applicationContext.getBean(MovieCharacterService.class).syncExternalCharacters();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public CloseableHttpClient getClosableHttpClient() {
        return HttpClients.createDefault();
    }

}
