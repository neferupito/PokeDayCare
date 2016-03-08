package com.nefee.pokedaycare.logic.manager;

import com.nefee.pokedaycare.logic.exception.PokeNotFoundException;
import com.nefee.pokedaycare.logic.model.Pokemon;

import java.util.List;

public interface BreedManager {

    List<Pokemon> findPotentialMoms(String babyName) throws PokeNotFoundException;

    List<Pokemon> findPotentialDads(String babyName) throws PokeNotFoundException;

}
