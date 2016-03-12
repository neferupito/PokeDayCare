package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.GenerationProfileDao;
import com.nefee.pokedaycare.data.entity.generation.GenerationProfileEntity;
import com.nefee.pokedaycare.logic.manager.PokemonManager;
import com.nefee.pokedaycare.logic.dto.Pokemon;
import com.nefee.pokedaycare.logic.transformer.GenerationTransformer;
import com.nefee.pokedaycare.logic.transformer.PokemonTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service ("pokemonManager")
@Transactional (readOnly = true)
public class PokemonManagerImpl implements PokemonManager {

    @Autowired
    private GenerationProfileDao generationProfileDao;

    public Optional<Pokemon> findByNationalIdAndGeneration(Integer id, Integer gen) {

        Optional<GenerationProfileEntity> entity = generationProfileDao.findByNationalIdAndGeneration(id, GenerationTransformer.integerToGeneration(gen));

        if (!entity.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(PokemonTransformer.genProfileToPokemon(entity.get()));

    }

}
