package com.amarkelovua.rickandmortyapi.controller;

import com.amarkelovua.rickandmortyapi.dto.MovieCharacterResponseDto;
import com.amarkelovua.rickandmortyapi.dto.mapper.ResponseDtoMapper;
import com.amarkelovua.rickandmortyapi.model.MovieCharacter;
import com.amarkelovua.rickandmortyapi.service.MovieCharacterService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;
    private final ResponseDtoMapper<MovieCharacterResponseDto,
            MovieCharacter> movieCharacterResponseDtoMapper;

    public MovieCharacterController(MovieCharacterService movieCharacterService,
                                    ResponseDtoMapper<MovieCharacterResponseDto,
                                            MovieCharacter> movieCharacterResponseDtoMapper) {
        this.movieCharacterService = movieCharacterService;
        this.movieCharacterResponseDtoMapper = movieCharacterResponseDtoMapper;
    }

    @GetMapping("/random")
    MovieCharacterResponseDto getRandomCharacter() {
        return movieCharacterResponseDtoMapper
                .mapToDto(movieCharacterService.getRandomMovieCharacter());
    }

    @GetMapping("/by-name")
    List<MovieCharacterResponseDto> getAllByName(@RequestParam("name") String namePart) {
        return movieCharacterService.findAllByNameContains(namePart).stream()
                .map(movieCharacterResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
