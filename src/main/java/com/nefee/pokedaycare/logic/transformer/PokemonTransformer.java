package com.nefee.pokedaycare.logic.transformer;

import com.nefee.pokedaycare.data.entity.generation.GenerationProfileEntity;
import com.nefee.pokedaycare.logic.model.Pokemon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PokemonTransformer {

    public static List<Pokemon> entitiesToPokemons(Collection<GenerationProfileEntity> genProfiles) {
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        for (GenerationProfileEntity genProfile : genProfiles) {
            pokemons.add(genProfileToPokemon(genProfile));
        }
        return pokemons;
    }

    public static Pokemon genProfileToPokemon(GenerationProfileEntity genProfile) {
        return Pokemon.builder()
                .name(genProfile.getPokemon().getName())
                .nationalId(genProfile.getPokemon().getNationalId())
                .type1(genProfile.getType1().toString())
                .type2(genProfile.getType2() == null ? null : genProfile.getType2().toString())
                .build();
    }

}
