package com.nefee.pokedaycare.logic.manager;

import com.nefee.pokedaycare.logic.model.Pokemon;

import java.util.List;
import java.util.Optional;

public interface PokemonManager {

    Optional<Pokemon> findByNationalIdAndGeneration(Integer id, Integer gen);

//    Optional<Pokemon> findByName(String name);

    List<Pokemon> findAllByGeneration(Integer gen);

}
