package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.EvolutionGroupDao;
import com.nefee.pokedaycare.data.dao.PokemonDao;
import com.nefee.pokedaycare.data.entity.EggGroup;
import com.nefee.pokedaycare.data.entity.EvolutionEntity;
import com.nefee.pokedaycare.data.entity.EvolutionGroupEntity;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.logic.exception.BreedingNotPossibleException;
import com.nefee.pokedaycare.logic.exception.PokeNotFoundException;
import com.nefee.pokedaycare.logic.manager.BreedManager;
import com.nefee.pokedaycare.logic.model.*;
import com.nefee.pokedaycare.logic.transformer.PokemonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service ("breedManager")
@Transactional (readOnly = true)
public class BreedManagerImpl implements BreedManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(BreedManager.class);

    @Autowired
    PokemonDao pokemonDao;
    @Autowired
    EvolutionGroupDao evolutionGroupDao;

    public BreedCase buildBreedCase(String babyName) throws PokeNotFoundException, BreedingNotPossibleException {

        PokemonEntity baby = findBaby(babyName);

        if (baby.getName().equals("Nidorina") || baby.getName().equals("Nidoqueen")) {
            return SpecialCaseProvider.forNidorina();
        }
        if (baby.getName().equals("Miltank")) {
            return SpecialCaseProvider.forMiltank();
        }
        if (baby.getName().equals("Tauros")) {
            return SpecialCaseProvider.forTauros();
        }

        // Check if the baby has evolutions and if it's the first in the evolution, if not replaced by the first
        baby = checkIfBabyIsRank1Evolution(baby);

        // Check if it is a Legendary or exception
        if (!checkIfCanBreed(baby)) {
            throw new BreedingNotPossibleException("Pokemon " + baby.getName() + "can't breed");
        }

        // Standard case
        Collection<PokemonParent> moms = findPotentialMoms(baby);
        Collection<PokemonParent> dads = findPotentialDads(baby);
        Collection<PokemonParent> dittoPartners = findDittoPartners(baby);

        return BreedCase.builder()
                .babyGoal(PokemonTransformer.entityToPokemon(baby))
                .normalCase(NormalCase.builder()
                        .potentialMoms(new ArrayList<>(moms))
                        .potentialDads(new ArrayList<>(dads))
                        .build())
                .dittoCase(DittoCase.builder()
                        .potentialPartners(new ArrayList<>(dittoPartners))
                        .build())
                .build();
    }

    private PokemonEntity findBaby(String babyName) throws PokeNotFoundException {
        Optional<PokemonEntity> optional = pokemonDao.findByName(babyName);
        if (!optional.isPresent()) {
            throw new PokeNotFoundException("Couldn't find any pokemon named " + babyName);
        }
        return optional.get();
    }

    private boolean checkIfCanBreed(PokemonEntity baby) {

        // If Ditto or Legendary, false
        for (EggGroup eg : baby.getEggGroups()) {
            if (eg.equals(EggGroup.UNDISCOVERED) || eg.equals(EggGroup.DITTO)) {
                return false;
            }
        }

        return true;

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

    private Set<EggGroup> findEggGroups(PokemonEntity baby) {

        Set<EggGroup> eggGroups = new HashSet<EggGroup>();

        EvolutionGroupEntity evolutionGroup = baby.getEvolution().getEvolutionGroup();

        for (EvolutionEntity evolution : evolutionGroup.getEvolutions()) {
            for (EggGroup eg : evolution.getPokemon().getEggGroups()) {
                if (!eg.equals(EggGroup.UNDISCOVERED))
                    eggGroups.add(eg);
            }
        }

        StringBuffer sb = new StringBuffer();
        for (EggGroup eg : eggGroups) {
            sb.append(" ");
            sb.append(eg);
        }

        LOGGER.debug("Found following eggGroups for breeding baby {} :{}", baby.getName(), sb.toString());

        return eggGroups;
    }

    // Return all the evolutions that can breed
    public Collection<PokemonParent> findPotentialMoms(PokemonEntity baby) throws PokeNotFoundException {

        EvolutionGroupEntity evolutionGroup = baby.getEvolution().getEvolutionGroup();

        Set<PokemonParent> moms = new HashSet<PokemonParent>();

        for (EvolutionEntity evolution : evolutionGroup.getEvolutions()) {
            PokemonEntity pokemon = evolution.getPokemon();
            if (checkIfCanBreed(pokemon)) {
                moms.add(PokemonParent.builder()
                        .name(pokemon.getName())
                        .nationalId(pokemon.getNationalId())
                        .gender(Gender.FEMALE)
                        .object(null)
                        .build());
            }
        }

        return moms;
    }

    public Collection<PokemonParent> findPotentialDads(PokemonEntity baby) throws PokeNotFoundException {

        Set<EggGroup> eggGroups = findEggGroups(baby);

        Set<PokemonParent> dads = new HashSet<PokemonParent>();

        for (EggGroup eg : eggGroups) {
            List<PokemonEntity> pokemons = pokemonDao.findAllPokemonsByEggGroup(eg);
            if (!pokemons.isEmpty()) {
                for (PokemonEntity pkmn : pokemons) {
                    dads.add(PokemonParent.builder()
                            .name(pkmn.getName())
                            .nationalId(pkmn.getNationalId())
                            .gender(Gender.FEMALE)
                            .object(null)
                            .build());
                }
            }
        }

        return dads;
    }

    private Collection<PokemonParent> findDittoPartners(PokemonEntity baby) {

        EvolutionGroupEntity evolutionGroup = baby.getEvolution().getEvolutionGroup();

        Set<PokemonParent> partners = new HashSet<PokemonParent>();

        for (EvolutionEntity evolution : evolutionGroup.getEvolutions()) {
            PokemonEntity pokemon = evolution.getPokemon();
            if (checkIfCanBreed(pokemon)) {
                partners.add(PokemonParent.builder()
                        .name(pokemon.getName())
                        .nationalId(pokemon.getNationalId())
                        .gender(Gender.ALL)
                        .object(null)
                        .build());
            }
        }

        return partners;
    }

}
