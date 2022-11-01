package com.amarkelovua.rickandmortyapi.dto.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T model);
}
