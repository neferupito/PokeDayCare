package com.nefee.pokedaycare.logic.transformer;

import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.logic.model.Pokemon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PokemonTransformer {

    public static List<Pokemon> entitiesToPokemons(Collection<PokemonEntity> entities) {
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        for (PokemonEntity entity : entities) {
            pokemons.add(entityToPokemon(entity));
        }
        return pokemons;
    }

    public static Pokemon entityToPokemon(PokemonEntity entity) {
        return Pokemon.builder()
                .name(entity.getName())
                .nationalId(entity.getNationalId())
                .type1(entity.getType1().getName())
                .type2(entity.getType2() == null ? null : entity.getType2().getName())
                .build();
    }

}
