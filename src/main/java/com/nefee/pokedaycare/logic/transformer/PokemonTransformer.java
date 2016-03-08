package com.nefee.pokedaycare.logic.transformer;

import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.logic.model.Pokemon;

public class PokemonTransformer {

    public static Pokemon entityToPokemon(PokemonEntity entity) {
        return Pokemon.builder()
                .name(entity.getName())
                .nationalId(entity.getNationalId())
                .type1(entity.getType1().getName())
                .type2(entity.getType2()==null?null:entity.getType2().getName())
                .build();
    }

}
