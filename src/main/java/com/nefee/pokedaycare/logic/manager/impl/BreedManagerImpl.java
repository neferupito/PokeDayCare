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
import com.nefee.pokedaycare.logic.dto.*;
import com.nefee.pokedaycare.logic.exception.BreedingNotPossibleException;
import com.nefee.pokedaycare.logic.exception.PokeNotFoundException;
import com.nefee.pokedaycare.logic.manager.BreedManager;
import com.nefee.pokedaycare.logic.transformer.GenerationTransformer;
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
    private PokemonDao pokemonDao;
    @Autowired
    private EvolutionDao evolutionDao;
    @Autowired
    private GenerationProfileDao generationProfileDao;

    public BreedCase buildBreedCase(Integer nationalId, Integer gen) throws PokeNotFoundException, BreedingNotPossibleException {

        if (gen == 1) {
            throw new BreedingNotPossibleException("Breeding not possible at generation 1");
        }

        Generation generation = GenerationTransformer.integerToGeneration(gen);

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
            baby = checkIfBabyIsFirstEvolution(baby);
        }

        if (!checkIfCanBeEgg(baby)) {
            throw new BreedingNotPossibleException("Pokemon " + baby.getPokemon().getName() + " can't breed");
        }

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
                .generation(gen)
                .babyGoals(getBabyGoals(baby))
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
            LOGGER.debug("{} can't breed", baby.getPokemon().getName());
            if (baby.hasEvolutions()) {
                LOGGER.debug("But {} has evolutions who can maybe breed", baby.getPokemon().getName());
                for (GenerationProfileEntity gp : baby.getEvolutionGroup().getPokemons()) {
                    if (checkIfCanBreed(gp)) {
                        LOGGER.debug("Eureka ! {} can breed !", gp.getPokemon().getName());
                        return true;
                    }
                }
                LOGGER.debug("{} has no evolution that can breed :(", baby.getPokemon().getName());
                return false;
            } else {
                LOGGER.debug("{} can't breed and has no evolution: breeding not possible :(", baby.getPokemon().getName());
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

            LOGGER.debug("{} is not the first evolution", baby.getPokemon().getName());

            EvolutionGroupEntity evolutionGroup = baby.getEvolutionGroup();

            Optional<EvolutionEntity> optional = evolutionDao.getEvolutionByRank(evolutionGroup, 1);

            if (!optional.isPresent()) {
                throw new PokeNotFoundException("Couldn't find any first evolution for " + baby.getPokemon().getName());
            }

            GenerationProfileEntity previous = optional.get().getPreviousPokemon();

            LOGGER.debug("{} is the first evolution", previous.getPokemon().getName());

            return previous;

        } else {
            return baby;

        }
    }

    private List<GenerationProfileEntity> findPotentialMoms(GenerationProfileEntity baby) throws PokeNotFoundException {

        if (baby.hasEvolutions()) {

            List<GenerationProfileEntity> evolutions = baby.getEvolutionGroup().getPokemons();
            List<GenerationProfileEntity> moms = new ArrayList<>();

            for (GenerationProfileEntity pokemon : evolutions) {
                if ((pokemon.getPercentFemale() > 0) && !pokemon.getEggGroups().contains(EggGroup.UNDISCOVERED)) {
                    LOGGER.debug("{} added in the potential moms for {}", pokemon.getPokemon().getName(), baby.getPokemon().getName());
                    moms.add(pokemon);
                }
            }

            Collections.sort(moms);
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

        for (GenerationProfileEntity pkmn : dads) {
            resultDads.add(PokemonParent.builder()
                    .name(pkmn.getPokemon().getName())
                    .nationalId(pkmn.getPokemon().getNationalId())
                    .gender(Gender.MALE)
                    .build());
        }

        Collections.sort(resultDads);
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

        Collections.sort(partners);

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

    private List<Baby> getBabyGoals(GenerationProfileEntity baby) {
        List<Baby> result = new ArrayList<>();
        result.add(Baby.builder()
                .baby(PokemonTransformer.genProfileToPokemon(baby))
                .requiredObject(baby.getRequiredObjectForEgg() == null ? null : baby.getRequiredObjectForEgg().getName())
                .build());
        if (baby.getRequiredObjectForEgg() != null) {
            if (!baby.getNextEvolutions().isEmpty()) {
                GenerationProfileEntity nextPokemon = baby.getNextEvolutions().get(0).getNextPokemon();
                result.add(Baby.builder()
                        .baby(PokemonTransformer.genProfileToPokemon(nextPokemon))
                        .requiredObject(nextPokemon.getRequiredObjectForEgg() == null ? null : nextPokemon.getRequiredObjectForEgg().getName())
                        .build());
            }
        }
        return result;
    }

}
