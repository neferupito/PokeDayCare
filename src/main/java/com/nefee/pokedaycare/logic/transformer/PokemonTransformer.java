package com.nefee.pokedaycare.logic.transformer;

import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.logic.model.Pokemon;

public class PokemonTransformer {

    public static Pokemon entityToPokemon(PokemonEntity entity) {
        return Pokemon.builder()
                .name(entity.getName())
                .birth(entity.getBirth())
                .build();
    }

}
