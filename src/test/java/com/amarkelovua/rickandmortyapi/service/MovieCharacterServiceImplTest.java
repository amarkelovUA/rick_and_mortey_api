package com.amarkelovua.rickandmortyapi.service;

import com.amarkelovua.rickandmortyapi.model.Gender;
import com.amarkelovua.rickandmortyapi.model.MovieCharacter;
import com.amarkelovua.rickandmortyapi.model.Status;
import com.amarkelovua.rickandmortyapi.repository.MovieCharacterRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovieCharacterServiceImplTest {
    private static List<MovieCharacter> movieCharacters;

    @InjectMocks
    private MovieCharacterServiceImpl movieCharacterService;

    @BeforeAll
    public static void fillList() {
        movieCharacters = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MovieCharacter movieCharacter = new MovieCharacter();
            movieCharacter.setId((long) i);
            movieCharacter.setExternalId((long) i);
            movieCharacter.setName(String.valueOf(i));
            movieCharacter.setGender(Gender.values()[i % Gender.values().length]);
            movieCharacter.setStatus(Status.values()[i % Status.values().length]);
            movieCharacters.add(movieCharacter);
        }
    }

    @Mock
    private MovieCharacterRepository repository;

    @Test
    void getRandomMovieCharacter_withOneElement_ok() {
        Mockito.when(repository.count()).thenReturn(1L);
        Mockito.when(repository.getById(0L)).thenReturn(movieCharacters.get(0));
        MovieCharacter actual = movieCharacterService.getRandomMovieCharacter();
        Assertions.assertEquals(movieCharacters.get(0).getExternalId(), actual.getExternalId());
        Assertions.assertEquals(movieCharacters.get(0).getGender(), actual.getGender());
        Assertions.assertEquals(movieCharacters.get(0).getStatus(), actual.getStatus());
        Assertions.assertEquals(movieCharacters.get(0).getName(), actual.getName());
    }
}
