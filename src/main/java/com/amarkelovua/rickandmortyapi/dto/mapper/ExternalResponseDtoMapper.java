package com.amarkelovua.rickandmortyapi.dto.mapper;

public interface ExternalResponseDtoMapper<D, T> {
    T mapToModelExternalApi(D dto);
}
