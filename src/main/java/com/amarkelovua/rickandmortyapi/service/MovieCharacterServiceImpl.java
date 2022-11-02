package com.amarkelovua.rickandmortyapi.service;

import com.amarkelovua.rickandmortyapi.dto.external.ApiCharacterDto;
import com.amarkelovua.rickandmortyapi.dto.external.ApiResponseDto;
import com.amarkelovua.rickandmortyapi.dto.mapper.ExternalResponseDtoMapper;
import com.amarkelovua.rickandmortyapi.model.MovieCharacter;
import com.amarkelovua.rickandmortyapi.repository.MovieCharacterRepository;
import com.amarkelovua.rickandmortyapi.util.HttpClient;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MovieCharacterServiceImpl implements MovieCharacterService {
    private final HttpClient httpClient;
    private final MovieCharacterRepository movieCharacterRepository;
    private final ExternalResponseDtoMapper<ApiCharacterDto, MovieCharacter>
            externalResponseDtoMapper;

    public MovieCharacterServiceImpl(HttpClient httpClient,
                                     MovieCharacterRepository movieCharacterRepository,
                                     ExternalResponseDtoMapper<ApiCharacterDto,
                                             MovieCharacter> externalResponseDtoMapper) {
        this.httpClient = httpClient;
        this.movieCharacterRepository = movieCharacterRepository;
        this.externalResponseDtoMapper = externalResponseDtoMapper;
    }

    @Scheduled(cron = "0 0 10 * * 1")
    @Override
    public void syncExternalCharacters() {
        log.info("syncExternalCharacters method is invoked: " + LocalDateTime.now());
        ApiResponseDto apiResponseDto = httpClient
                .get("https://rickandmortyapi.com/api/character", ApiResponseDto.class);

        saveDtosToDb(apiResponseDto);
        while (apiResponseDto.getInfo().getNext() != null) {
            apiResponseDto = httpClient
                    .get(apiResponseDto.getInfo().getNext(), ApiResponseDto.class);
            saveDtosToDb(apiResponseDto);
        }
    }

    @Override
    public MovieCharacter getRandomMovieCharacter() {
        long count = movieCharacterRepository.count();
        return movieCharacterRepository.getById((long) (Math.random() * count));
    }

    @Override
    public List<MovieCharacter> findAllByNameContains(String namePart) {
        return movieCharacterRepository.findAllByNameContains(namePart);
    }

    private void saveDtosToDb(ApiResponseDto apiResponseDto) {
        Map<Long, ApiCharacterDto> externalDtos = Arrays.stream(apiResponseDto.getResults())
                .collect(Collectors.toMap(ApiCharacterDto::getId, Function.identity()));
        Set<Long> externalIds = externalDtos.keySet();

        List<MovieCharacter> existingCharacters = movieCharacterRepository
                .findAllByExternalIdIn(externalIds);
        Map<Long, MovieCharacter> existingCharactersWithIds = existingCharacters.stream()
                .collect(Collectors.toMap(MovieCharacter::getExternalId, Function.identity()));
        Set<Long> existingIds = existingCharactersWithIds.keySet();

        externalIds.removeAll(existingIds);

        List<MovieCharacter> charactersToSave = externalIds.stream()
                .map(i -> externalResponseDtoMapper.mapToModelExternalApi(externalDtos.get(i)))
                .collect(Collectors.toList());
        movieCharacterRepository.saveAll(charactersToSave);
    }
}
