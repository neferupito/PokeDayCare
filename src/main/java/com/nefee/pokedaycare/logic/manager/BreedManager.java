package com.nefee.pokedaycare.logic.manager;

import com.nefee.pokedaycare.logic.exception.BreedingNotPossibleException;
import com.nefee.pokedaycare.logic.exception.PokeNotFoundException;
import com.nefee.pokedaycare.logic.dto.BreedCase;

public interface BreedManager {

    BreedCase buildBreedCase(Integer nationalId, Integer generation) throws PokeNotFoundException, BreedingNotPossibleException;

}
