package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.GenerationProfileDao;
import com.nefee.pokedaycare.logic.manager.PokedexManager;
import com.nefee.pokedaycare.logic.dto.Pokemon;
import com.nefee.pokedaycare.logic.transformer.GenerationTransformer;
import com.nefee.pokedaycare.logic.transformer.PokemonTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service ("pokedexManager")
@Transactional (readOnly = true)
public class PokedexManagerImpl implements PokedexManager {

    @Autowired
    private GenerationProfileDao generationProfileDao;

    public List<Pokemon> findAllByGeneration(Integer gen) {
        return PokemonTransformer.entitiesToPokemons(generationProfileDao.findAllByGeneration(GenerationTransformer.integerToGeneration(gen)));
    }
}
