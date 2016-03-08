package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.PokemonDao;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.logic.exception.PokeDayCareException;
import com.nefee.pokedaycare.logic.manager.PokemonManager;
import com.nefee.pokedaycare.logic.model.Pokemon;
import com.nefee.pokedaycare.logic.transformer.PokemonTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("pokemonManager")
@Transactional(readOnly = true)
public class PokemonManagerImpl implements PokemonManager {

    @Autowired
    private PokemonDao pokemonDao;

    public Pokemon findByName(String name) throws PokeDayCareException {

        Optional<PokemonEntity> entity = pokemonDao.findByName(name);

        if (!entity.isPresent()) {
            throw new PokeDayCareException("Couldn't find the pokemon named " + name);
        }

        return PokemonTransformer.entityToPokemon(entity.get());

    }

}
