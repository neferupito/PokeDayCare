package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.EvolutionDao;
import com.nefee.pokedaycare.data.dao.PokemonDao;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.data.entity.breeding.EggGroup;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionEntity;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionGroupEntity;
import com.nefee.pokedaycare.logic.exception.BreedingNotPossibleException;
import com.nefee.pokedaycare.logic.exception.PokeNotFoundException;
import com.nefee.pokedaycare.logic.manager.BreedManager;
import com.nefee.pokedaycare.logic.model.BreedCase;
import com.nefee.pokedaycare.logic.model.Gender;
import com.nefee.pokedaycare.logic.model.PokemonParent;
import com.nefee.pokedaycare.logic.model.PotentialPartners;
import com.nefee.pokedaycare.logic.transformer.PokemonTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;

@Service("breedManager")
@Transactional(readOnly = true)
public class BreedManagerImpl implements BreedManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(BreedManager.class);

    @Autowired
    private PokemonDao pokemonDao;
    @Autowired
    private EvolutionDao evolutionDao;

    @PostConstruct
    public void init() {
        System.err.println("== BreedManager initiated ==");
    }

    @PreDestroy
    public void close() {
        System.err.println("== BreedManager stopped ==");
    }

    public BreedCase buildBreedCase(Integer nationalId) throws PokeNotFoundException, BreedingNotPossibleException {

        PokemonEntity baby = findBaby(nationalId);

//        if (baby.getName().equals("Nidorina") || baby.getName().equals("Nidoqueen")) {
//            return SpecialCaseProvider.forNidorina();
//        }
//        if (baby.getName().equals("Miltank")) {
//            return SpecialCaseProvider.forMiltank();
//        }
//        if (baby.getName().equals("Tauros")) {
//            return SpecialCaseProvider.forTauros();
//        }

        if (baby.hasEvolutions()) {
            System.err.println(baby.getName() + " has evolutions");
            // Check if the baby has evolutions and if it's the first in the evolution, if not replaced by the first
            baby = checkIfBabyIsFirstEvolution(baby);
        }

        // Check if it is a Legendary or exception
//        if (!checkIfCanBreed(baby)) {
//            throw new BreedingNotPossibleException("Pokemon " + baby.getName() + "can't breed");
//        }

        // Standard case

        List<PotentialPartners> potentialPartners = new ArrayList<>();
        Collection<PokemonParent> moms = findPotentialMoms(baby);

        for (PokemonParent mom : moms) {
            List<PokemonParent> dads = findPotentialDads(mom);
            potentialPartners.add(PotentialPartners.builder()
                    .partner(mom)
                    .potentialPartners(dads)
                    .build());
        }

        PotentialPartners dittoPartners = findDittoPartners(baby);
        potentialPartners.add(dittoPartners);

        return BreedCase.builder()
                .babyGoal(PokemonTransformer.entityToPokemon(baby))
                .potentialPartners(potentialPartners)
                .build();
    }

    private PokemonEntity findBaby(Integer id) throws PokeNotFoundException {
        Optional<PokemonEntity> optional = pokemonDao.findByNationalId(id);
        if (!optional.isPresent()) {
            throw new PokeNotFoundException("Couldn't find any pokemon with id " + id);
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

    private PokemonEntity checkIfBabyIsFirstEvolution(PokemonEntity baby) throws PokeNotFoundException {

        if (baby.getPreviousEvolution() != null) {

            System.err.println(baby.getName() + " has previous evolution, he's not the first one");

            EvolutionGroupEntity evolutionGroup = baby.getEvolutionGroup();

            Optional<EvolutionEntity> optional = evolutionDao.getEvolutionByRank(evolutionGroup, 1);

            if (!optional.isPresent()) {
                throw new PokeNotFoundException("Couldn't find any first evolution for " + baby.getName());
            }

            PokemonEntity previous = optional.get().getPreviousPokemon();

            System.err.println("First Pokemon of evolution group is " + previous.getName());

            return previous;

        } else {

            System.err.println(baby.getName() + "has no previous evolution");
            // le bébé n'a pas de première évolution
            return baby;
        }
    }

//    private Set<EggGroup> findEggGroupsForBabyAndEvolutions(PokemonEntity baby) {
//
//        Set<EggGroup> eggGroups = new HashSet<EggGroup>();
//
//        if (hasEvolutions) {
//
//            int i = 1;
//
//            for (EvolutionEntity evolution : evolutionGroup.getEvolutions()) {
//
//                System.err.println("Evolution n°" + i++ + " is between " + evolution.getPreviousPokemon().getName() + " and " + evolution.getNextPokemon().getName());
//
//                // TODO: to improve
//
//                for (EggGroup eg : evolution.getPreviousPokemon().getEggGroups()) {
//
//                    System.err.println("Fetching eggGroups of " + evolution.getPreviousPokemon().getName());
//
//                    if (!eg.equals(EggGroup.UNDISCOVERED)) {
//                        eggGroups.add(eg);
//                        System.err.println(eg + " added");
//                    }
//                }
//
//                System.err.println("Fetching eggGroups of " + evolution.getNextPokemon().getName());
//
//                for (EggGroup eg : evolution.getNextPokemon().getEggGroups()) {
//                    if (!eg.equals(EggGroup.UNDISCOVERED)) {
//                        eggGroups.add(eg);
//                        System.err.println(eg + " added");
//                    }
//                }
//            }
//
//        } else {
//
//            System.err.println(baby.getName() + " has no evolution so fetching only the eggroups for him");
//
//            for (EggGroup eg : baby.getEggGroups()) {
//                eggGroups.add(eg);
//                System.err.println(eg + " added");
//            }
//        }
//
//        StringBuffer sb = new StringBuffer();
//        for (EggGroup eg : eggGroups) {
//            sb.append(" ");
//            sb.append(eg);
//        }
//
//        LOGGER.debug("Found following eggGroups for breeding baby {} :{}", baby.getName(), sb.toString());
//
//        return eggGroups;
//    }

    // Return all the evolutions that can breed
    private List<PokemonParent> findPotentialMoms(PokemonEntity baby) throws PokeNotFoundException {


        if (baby.hasEvolutions()) {

            List<PokemonParent> result = new ArrayList<>();
            List<PokemonEntity> evolutions = baby.getEvolutionGroup().getPokemons();

            for (PokemonEntity pokemon : evolutions) {
                if (pokemon.getPercentFemale() > 0 && !pokemon.getEggGroups().contains(EggGroup.UNDISCOVERED)) {
                    result.add(PokemonParent.builder()
                            .name(pokemon.getName())
                            .nationalId(pokemon.getNationalId())
                            .gender(Gender.FEMALE)
                            .object(null)
                            .build());
                }
            }

            return result;

        } else {
            List<PokemonParent> moms = new ArrayList<>();
            moms.add(PokemonParent.builder()
                    .name(baby.getName())
                    .nationalId(baby.getNationalId())
                    .gender(Gender.FEMALE)
                    .object(null)
                    .build());
            return moms;
        }

    }

    private List<PokemonParent> findPotentialDads(PokemonParent mom) throws PokeNotFoundException {

//        Set<EggGroup> eggGroups = findEggGroupsForBabyAndEvolutions(baby);

        List<PokemonParent> resultDads = new ArrayList<>();
        Set<PokemonEntity> dads = new HashSet<>();

        PokemonEntity momEntity = pokemonDao.findByNationalId(mom.getNationalId()).get();

        for (EggGroup eg : momEntity.getEggGroups()) {
            if (eg != EggGroup.UNDISCOVERED) {
                List<PokemonEntity> pokemons = pokemonDao.findAllPokemonsByEggGroup(eg);
                if (!pokemons.isEmpty()) {
                    for (PokemonEntity pkmn : pokemons) {
                        if (checkIfCanBreed(pkmn)) {
                            if (pkmn.getPercentMale() > 0)
                                dads.add(pkmn);
                        }
                    }
                }
            }
        }

        // ADD EVOL

        for (PokemonEntity pkmn : dads)

        {
            resultDads.add(PokemonParent.builder()
                    .name(pkmn.getName())
                    .nationalId(pkmn.getNationalId())
                    .gender(Gender.MALE)
                    .object(null)
                    .build());
        }

        return resultDads;
    }

    private PotentialPartners findDittoPartners(PokemonEntity baby) {

        List<PokemonParent> partners = new ArrayList<>();
        List<PokemonEntity> pokemons = baby.getEvolutionGroup().getPokemons();

        for (PokemonEntity pokemon : pokemons) {
            if (!pokemon.getEggGroups().contains(EggGroup.UNDISCOVERED)) {
                partners.add(PokemonParent.builder()
                        .name(pokemon.getName())
                        .nationalId(pokemon.getNationalId())
                        .gender(Gender.ALL)
                        .object(null)
                        .build());
            }
        }

        PokemonEntity ditto = pokemonDao.findByNationalId(132).get();

        return PotentialPartners.builder()
                .partner(PokemonParent.builder()
                        .name(ditto.getName())
                        .nationalId(ditto.getNationalId())
                        .gender(Gender.NEUTRAL)
                        .object(null)
                        .build())
                .potentialPartners(partners)
                .build();
    }

//    private Set<PokemonEntity> collectPokemonsFromEvolutionGroup(EvolutionGroupEntity evolutionGroup) {
//
//        Set<PokemonEntity> pokemonSet = new HashSet<>();
//
//        for (EvolutionEntity evolution : evolutionGroup.getEvolutions()) {
//            PokemonEntity previousPokemon = evolution.getPreviousPokemon();
//            if (checkIfCanBreed(previousPokemon)) {
//                pokemonSet.add(previousPokemon);
//                System.err.println(previousPokemon.getName() + " added in the potential moms");
//            }
//            PokemonEntity nextPokemon = evolution.getNextPokemon();
//            if (checkIfCanBreed(nextPokemon)) {
//                pokemonSet.add(nextPokemon);
//                System.err.println(previousPokemon.getName() + " added in the potential moms");
//            }
//        }
//
//        return pokemonSet;
//    }

}
