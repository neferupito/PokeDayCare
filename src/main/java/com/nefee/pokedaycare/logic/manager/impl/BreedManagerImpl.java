package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.EggGroupDao;
import com.nefee.pokedaycare.data.dao.EvolutionGroupDao;
import com.nefee.pokedaycare.data.dao.PokemonDao;
import com.nefee.pokedaycare.data.entity.EggGroupEntity;
import com.nefee.pokedaycare.data.entity.EvolutionEntity;
import com.nefee.pokedaycare.data.entity.EvolutionGroupEntity;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.logic.exception.PokeNotFoundException;
import com.nefee.pokedaycare.logic.manager.BreedManager;
import com.nefee.pokedaycare.logic.model.Pokemon;
import com.nefee.pokedaycare.logic.transformer.PokemonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("breedManager")
@Transactional(readOnly = true)
public class BreedManagerImpl implements BreedManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(BreedManager.class);

    @Autowired
    PokemonDao pokemonDao;
    @Autowired
    EggGroupDao eggGroupDao;
    @Autowired
    EvolutionGroupDao evolutionGroupDao;

    public List<Pokemon> findPotentialMoms(String babyName) throws PokeNotFoundException {
        return null;
    }

    public List<Pokemon> findPotentialDads(String babyName) throws PokeNotFoundException {

        // Get the baby entity
        PokemonEntity baby = findBaby(babyName);

        // Check if the baby has evolutions and if it's the first in the evolution, if not replaced by the first
        baby = checkIfBabyIsRank1Evolution(baby);

        // Get the egg groups of the whole evolution
        Set<EggGroupEntity> eggGroups = findEggGroups(baby);

        // Get all the pokemons from the eggGroups
        Set<PokemonEntity> allPokemons = new HashSet<PokemonEntity>();
        for (EggGroupEntity eggGroup : eggGroups) {
            List<PokemonEntity> result = eggGroupDao.findAllPokemonsByEggGroup(eggGroup);
            if (!result.isEmpty()) {
                for (PokemonEntity pokemon : result) {
                    allPokemons.add(pokemon);
                }
            }
        }
        if (allPokemons.isEmpty()) {
            throw new PokeNotFoundException("Couldn't find any pokemon in the eggGroups of " + babyName);
        }

        // Transform
        return PokemonTransformer.entitiesToPokemons(allPokemons);
    }

    private PokemonEntity findBaby(String babyName) throws PokeNotFoundException {
        Optional<PokemonEntity> optional = pokemonDao.findByName(babyName);
        if (!optional.isPresent()) {
            throw new PokeNotFoundException("Couldn't find any pokemon named " + babyName);
        }
        return optional.get();
    }

    private boolean checkIfCanBreed(PokemonEntity baby) {

        // check if baby or in the evolution can breed
        // can't breed if in Undiscovered
    }

    private PokemonEntity checkIfBabyIsRank1Evolution(PokemonEntity baby) throws PokeNotFoundException {
        if (baby.getEvolution() == null) {
            return baby;
        }
        if (baby.getEvolution().getEvolutionRank() != 1) {
            Optional<PokemonEntity> optional = evolutionGroupDao.getPokemonByRank(baby.getEvolution().getEvolutionGroup(), 1);
            if (!optional.isPresent()) {
                throw new PokeNotFoundException("Couldn't find any pokemon in first evolution of " + baby.getName());
            }
            return optional.get();
        } else {
            return baby;
        }
    }

    private Set<EggGroupEntity> findEggGroups(PokemonEntity baby) {
        Set<EggGroupEntity> eggGroups = new HashSet<EggGroupEntity>();
        for (EggGroupEntity eg : baby.getEggGroups()) {
            eggGroups.add(eg);
        }

        if (baby.getEvolution() != null) {
            EvolutionGroupEntity evolutionGroup = baby.getEvolution().getEvolutionGroup();
            for (EvolutionEntity evol : evolutionGroup.getEvolutions()) {
                for (EggGroupEntity eg : evol.getPokemon().getEggGroups()) {
                    eggGroups.add(eg);
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        for (EggGroupEntity eg : eggGroups) {
            sb.append(" ");
            sb.append(eg.getName());
        }

        LOGGER.debug("Found following eggGroups for breeding baby {} :{}", baby.getName(), sb.toString());

        return eggGroups;
    }

}
