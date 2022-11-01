package com.amarkelovua.rickandmortyapi.dto.mapper;

import com.amarkelovua.rickandmortyapi.dto.MovieCharacterResponseDto;
import com.amarkelovua.rickandmortyapi.dto.external.ApiCharacterDto;
import com.amarkelovua.rickandmortyapi.model.Gender;
import com.amarkelovua.rickandmortyapi.model.MovieCharacter;
import com.amarkelovua.rickandmortyapi.model.Status;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterMapperImpl implements ResponseDtoMapper<MovieCharacterResponseDto,
        MovieCharacter>, ExternalResponseDtoMapper<ApiCharacterDto, MovieCharacter> {
    @Override
    public MovieCharacter mapToModelExternalApi(ApiCharacterDto dto) {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setExternalId(dto.getId());
        movieCharacter.setName(dto.getName());
        movieCharacter.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        movieCharacter.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        return movieCharacter;
    }

    @Override
    public MovieCharacterResponseDto mapToDto(MovieCharacter model) {
        MovieCharacterResponseDto dto = new MovieCharacterResponseDto();
        dto.setId(model.getId());
        dto.setExternalId(model.getExternalId());
        dto.setName(model.getName());
        dto.setGender(model.getGender().getDescription());
        dto.setStatus(model.getStatus().getDescription());
        return dto;
    }
}
