package com.amarkelovua.rickandmortyapi.controller;

import com.amarkelovua.rickandmortyapi.service.MovieCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external_upload")
public class UploadController {
    private final MovieCharacterService characterService;

    public UploadController(MovieCharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public String loadAll() {
        characterService.syncExternalCharacters();
        return "Done!";
    }
}
