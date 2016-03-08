package com.nefee.pokedaycare.logic.manager;

import com.nefee.pokedaycare.logic.exception.PokeDayCareException;
import com.nefee.pokedaycare.logic.model.Pokemon;

import java.util.Optional;

public interface PokemonManager {

    Optional<Pokemon> findByName(String name);

}
