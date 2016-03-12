package com.nefee.pokedaycare.logic.manager;

import com.nefee.pokedaycare.logic.dto.Pokemon;

import java.util.List;

public interface PokedexManager {

    List<Pokemon> findAllByGeneration(Integer gen);

}
