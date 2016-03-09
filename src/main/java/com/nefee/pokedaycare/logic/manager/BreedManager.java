package com.nefee.pokedaycare.logic.manager;

import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.logic.exception.BreedingNotPossibleException;
import com.nefee.pokedaycare.logic.exception.PokeNotFoundException;
import com.nefee.pokedaycare.logic.model.BreedCase;
import com.nefee.pokedaycare.logic.model.PokemonParent;

import java.util.Collection;
import java.util.List;

public interface BreedManager {

    BreedCase buildBreedCase(String babyName) throws PokeNotFoundException, BreedingNotPossibleException;

    Collection<PokemonParent> findPotentialMoms(PokemonEntity baby) throws PokeNotFoundException;

    Collection<PokemonParent> findPotentialDads(PokemonEntity baby) throws PokeNotFoundException;

}
