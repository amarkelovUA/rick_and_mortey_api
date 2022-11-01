package com.amarkelovua.rickandmortyapi.service;

import com.amarkelovua.rickandmortyapi.model.MovieCharacter;
import java.util.List;

public interface MovieCharacterService {
    void syncExternalCharacters();

    MovieCharacter getRandomMovieCharacter();

    List<MovieCharacter> findAllByNameContains(String namePart);
}
