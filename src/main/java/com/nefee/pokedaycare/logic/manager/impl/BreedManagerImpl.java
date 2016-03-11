package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.EvolutionDao;
import com.nefee.pokedaycare.data.dao.GenerationProfileDao;
import com.nefee.pokedaycare.data.dao.PokemonDao;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.data.entity.breeding.EggGroup;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionEntity;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionGroupEntity;
import com.nefee.pokedaycare.data.entity.generation.Generation;
import com.nefee.pokedaycare.data.entity.generation.GenerationProfileEntity;
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

@Service ("breedManager")
@Transactional (readOnly = true)
public class BreedManagerImpl implements BreedManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(BreedManager.class);

    @Autowired
    private PokemonDao pokemonDao;
    @Autowired
    private EvolutionDao evolutionDao;
    @Autowired
    private GenerationProfileDao generationProfileDao;

    @PostConstruct
    public void init() {
        System.err.println("== BreedManager initiated ==");
    }

    @PreDestroy
    public void close() {
        System.err.println("== BreedManager stopped ==");
    }

    public BreedCase buildBreedCase(Integer nationalId, Generation generation) throws PokeNotFoundException, BreedingNotPossibleException {

        if (generation == null) {
            generation = Generation.GEN6;
        }

        GenerationProfileEntity baby = findBaby(generation, nationalId);

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
            System.err.println(baby.getPokemon().getName() + " has evolutions");
            // Check if the baby has evolutions and if it's the first in the evolution, if not replaced by the first
            baby = checkIfBabyIsFirstEvolution(baby);
        }


        // Check if it is a Legendary or exception
        if (!checkIfCanBeEgg(baby)) {
            throw new BreedingNotPossibleException("Pokemon " + baby.getPokemon().getName() + "can't breed");
        }

        // Standard case

        List<PotentialPartners> potentialPartners = new ArrayList<>();
        List<GenerationProfileEntity> moms = findPotentialMoms(baby);

        for (GenerationProfileEntity mom : moms) {
            List<PokemonParent> dads = findPotentialDads(mom, generation);
            potentialPartners.add(PotentialPartners.builder()
                    .partner(PokemonParent.builder()
                            .name(mom.getPokemon().getName())
                            .nationalId(mom.getPokemon().getNationalId())
                            .gender(Gender.FEMALE)
                            .build())
                    .potentialPartners(dads)
                    .build());
        }

        PotentialPartners dittoPartners = findDittoPartners(moms);
        potentialPartners.add(dittoPartners);

        return BreedCase.builder()
                .babyGoal(PokemonTransformer.genProfileToPokemon(baby))
                .requiredObject(baby.getRequiredObjectForEgg() == null ? null : baby.getRequiredObjectForEgg().getName())
                .potentialPartners(potentialPartners)
                .build();
    }

    private GenerationProfileEntity findBaby(Generation gen, Integer id) throws PokeNotFoundException {
        Optional<GenerationProfileEntity> optional = generationProfileDao.findByNationalIdAndGeneration(id, gen);
        if (!optional.isPresent()) {
            throw new PokeNotFoundException("Couldn't find any pokemon with id " + id);
        }
        return optional.get();
    }

    private boolean checkIfCanBeEgg(GenerationProfileEntity baby) {
        if (!checkIfCanBreed(baby)) {
            System.err.println(baby.getPokemon().getName() + " can't breed");
            if (baby.hasEvolutions()) {
                System.err.println(baby.getPokemon().getName() + " has evolutions who can maybe breed");
                for (GenerationProfileEntity gp : baby.getEvolutionGroup().getPokemons()) {
                    if (checkIfCanBreed(gp)) {
                        System.err.println(gp.getPokemon().getName() + " can breed " + baby.getPokemon().getName());
                        return true;
                    }
                }
                System.err.println(baby.getPokemon().getName() + " has no evolution who can breed");
                return false;
            } else {
                System.err.println(baby.getPokemon().getName() + " has no evolution so no breeding possible");
                return false;
            }
        } else {
            return true;
        }
    }

    private boolean checkIfCanBreed(GenerationProfileEntity baby) {
        // If Ditto or Legendary or baby => false
        for (EggGroup eg : baby.getEggGroups()) {
            if (eg.equals(EggGroup.UNDISCOVERED) || eg.equals(EggGroup.DITTO)) {
                return false;
            }
        }
        return true;

    }

    private GenerationProfileEntity checkIfBabyIsFirstEvolution(GenerationProfileEntity baby) throws PokeNotFoundException {

        if (baby.getPreviousEvolution() != null) {

            System.err.println(baby.getPokemon().getName() + " has previous evolution, he's not the first one");

            EvolutionGroupEntity evolutionGroup = baby.getEvolutionGroup();

            Optional<EvolutionEntity> optional = evolutionDao.getEvolutionByRank(evolutionGroup, 1);

            if (!optional.isPresent()) {
                throw new PokeNotFoundException("Couldn't find any first evolution for " + baby.getPokemon().getName());
            }

            GenerationProfileEntity previous = optional.get().getPreviousPokemon();

            System.err.println("First Pokemon of evolution group is " + previous.getPokemon().getName());

            return previous;

        } else {

            System.err.println(baby.getPokemon().getName() + "has no previous evolution");
            return baby;

        }
    }

    private List<GenerationProfileEntity> findPotentialMoms(GenerationProfileEntity baby) throws PokeNotFoundException {

        if (baby.hasEvolutions()) {

            List<GenerationProfileEntity> evolutions = baby.getEvolutionGroup().getPokemons();
            List<GenerationProfileEntity> moms = new ArrayList<>();

            for (GenerationProfileEntity pokemon : evolutions) {
                if ((pokemon.getPercentFemale() > 0) && !pokemon.getEggGroups().contains(EggGroup.UNDISCOVERED)) {
                    System.err.println(pokemon.getPokemon().getName() + " added in the potential moms");
                    moms.add(pokemon);
                }
            }

            return moms;

        } else {
            return Arrays.asList(baby);
        }

    }

    private List<PokemonParent> findPotentialDads(GenerationProfileEntity mom, Generation gen) throws PokeNotFoundException {

        List<PokemonParent> resultDads = new ArrayList<>();
        Set<GenerationProfileEntity> dads = new HashSet<>();

        for (EggGroup eg : mom.getEggGroups()) {
            if (eg != EggGroup.UNDISCOVERED) {
                List<GenerationProfileEntity> eggGroupPokemons = generationProfileDao.findAllPokemonsByEggGroupAndGeneration(eg, gen);
                if (!eggGroupPokemons.isEmpty()) {
                    for (GenerationProfileEntity pkmn : eggGroupPokemons) {
                        if (checkIfCanBreed(pkmn)) {
                            if (pkmn.getPercentMale() > 0)

                                dads.add(pkmn);
                        }
                    }
                }
            }
        }

        // ADD EVOL ???

        for (GenerationProfileEntity pkmn : dads)

        {
            resultDads.add(PokemonParent.builder()
                    .name(pkmn.getPokemon().getName())
                    .nationalId(pkmn.getPokemon().getNationalId())
                    .gender(Gender.MALE)
                    .build());
        }

        return resultDads;
    }

    private PotentialPartners findDittoPartners(List<GenerationProfileEntity> moms) {

        List<PokemonParent> partners = new ArrayList<>();
        for (GenerationProfileEntity mom : moms) {
            partners.add(PokemonParent.builder()
                    .name(mom.getPokemon().getName())
                    .nationalId(mom.getPokemon().getNationalId())
                    .gender(Gender.ALL)
                    .build());
        }

        PokemonEntity ditto = pokemonDao.findByNationalId(132).get();

        return PotentialPartners.builder()
                .partner(PokemonParent.builder()
                        .name(ditto.getName())
                        .nationalId(ditto.getNationalId())
                        .gender(Gender.NEUTRAL)
                        .build())
                .potentialPartners(partners)
                .build();
    }

}
