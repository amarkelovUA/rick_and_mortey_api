package com.amarkelovua.rickandmortyapi.controller;

import com.amarkelovua.rickandmortyapi.model.Gender;
import com.amarkelovua.rickandmortyapi.model.MovieCharacter;
import com.amarkelovua.rickandmortyapi.model.Status;
import com.amarkelovua.rickandmortyapi.service.MovieCharacterService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieCharacterControllerTest {
    @MockBean
    private MovieCharacterService movieCharacterService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void getRandomCharacter_ok() {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setId(1L);
        movieCharacter.setExternalId(1L);
        movieCharacter.setName("Rick");
        movieCharacter.setGender(Gender.MALE);
        movieCharacter.setStatus(Status.ALIVE);
        Mockito.when(movieCharacterService.getRandomMovieCharacter()).thenReturn(movieCharacter);

        RestAssuredMockMvc.when()
                .get("/characters/random")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("externalId", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("Rick"))
                .body("status", Matchers.equalTo("Alive"))
                .body("gender", Matchers.equalTo("Male"));
    }

    @Test
    public void getAllByName_ok() {
        List<MovieCharacter> movieCharacters = new ArrayList<>();
        int[] numbers = {1, 11, 21, 31, 41, 51, 61, 71, 81, 91};
        for (int i : numbers) {
            MovieCharacter movieCharacter = new MovieCharacter();
            movieCharacter.setId((long) i);
            movieCharacter.setExternalId((long) i);
            movieCharacter.setName(String.valueOf(i));
            movieCharacter.setGender(Gender.values()[i % Gender.values().length]);
            movieCharacter.setStatus(Status.values()[i % Status.values().length]);
            movieCharacters.add(movieCharacter);
        }
        Mockito.when(movieCharacterService.findAllByNameContains("1"))
                .thenReturn(movieCharacters);

        RestAssuredMockMvc.given()
                .queryParam("namePart", 1)
                .when()
                .get("/characters/by-name")
                .then()
                .body("size()", Matchers.equalTo(numbers.length))
                .body("[0].name", Matchers.equalTo(String.valueOf(numbers[0])))
                .body("[1].id", Matchers.equalTo(numbers[1]))
                .body("[2].externalId", Matchers.equalTo(numbers[2]))
                .body("[3].status", Matchers.equalTo(Status
                        .values()[numbers[3] % Status.values().length].getDescription()))
                .body("[4].gender", Matchers.equalTo(Gender
                        .values()[numbers[4] % Gender.values().length].getDescription()));
    }
}
