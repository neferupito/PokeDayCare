package com.nefee.pokedaycare.logic.manager;

import com.nefee.pokedaycare.logic.dto.Pokemon;

import java.util.Optional;

public interface PokemonManager {

    Optional<Pokemon> findByNationalIdAndGeneration(Integer id, Integer gen);

}
