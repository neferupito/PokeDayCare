package com.nefee.pokedaycare.logic.manager;

import com.nefee.pokedaycare.logic.exception.PokeDayCareException;
import com.nefee.pokedaycare.logic.model.Pokemon;

public interface PokemonManager {

    Pokemon findByName(String name) throws PokeDayCareException;

}
