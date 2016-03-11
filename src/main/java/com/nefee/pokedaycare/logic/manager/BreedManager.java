package com.nefee.pokedaycare.logic.manager;

import com.nefee.pokedaycare.data.entity.generation.Generation;
import com.nefee.pokedaycare.logic.exception.BreedingNotPossibleException;
import com.nefee.pokedaycare.logic.exception.PokeNotFoundException;
import com.nefee.pokedaycare.logic.model.BreedCase;

public interface BreedManager {

    BreedCase buildBreedCase(Integer nationalId, Generation generation) throws PokeNotFoundException, BreedingNotPossibleException;

}
