package com.amarkelovua.rickandmortyapi.repository;

import com.amarkelovua.rickandmortyapi.model.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {
}
