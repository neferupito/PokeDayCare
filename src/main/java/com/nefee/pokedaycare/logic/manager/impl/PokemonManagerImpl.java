package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.PokemonDao;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.logic.manager.PokemonManager;
import com.nefee.pokedaycare.logic.model.Pokemon;
import com.nefee.pokedaycare.logic.transformer.PokemonTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("pokemonManager")
@Transactional(readOnly = true)
public class PokemonManagerImpl implements PokemonManager {

    @Autowired
    private PokemonDao pokemonDao;

    public Optional<Pokemon> findByNationalId(Integer id) {

        Optional<PokemonEntity> entity = pokemonDao.findByNationalId(id);

        if (!entity.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(PokemonTransformer.entityToPokemon(entity.get()));

    }

    public Optional<Pokemon> findByName(String name) {

        Optional<PokemonEntity> entity = pokemonDao.findByName(name);

        if (!entity.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(PokemonTransformer.entityToPokemon(entity.get()));

    }

    public List<Pokemon> findAll() {
        return PokemonTransformer.entitiesToPokemons(pokemonDao.findAll());
    }

}
