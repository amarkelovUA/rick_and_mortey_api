package com.amarkelovua.rickandmortyapi.controller;

import com.amarkelovua.rickandmortyapi.service.MovieCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final MovieCharacterService characterService;

    public DemoController(MovieCharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public String getAll() {
        characterService.syncExternalCharacters();
        return "Done!";
    }
}
