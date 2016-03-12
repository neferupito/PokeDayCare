package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.*;
import com.nefee.pokedaycare.data.entity.ObjectEntity;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.data.entity.Type;
import com.nefee.pokedaycare.data.entity.breeding.EggGroup;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionContext;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionEntity;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionGroupEntity;
import com.nefee.pokedaycare.data.entity.evolution.EvolveConditionEntity;
import com.nefee.pokedaycare.data.entity.generation.Generation;
import com.nefee.pokedaycare.data.entity.generation.GenerationProfileEntity;
import com.nefee.pokedaycare.logic.manager.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service ("databaseManager")
@Transactional (readOnly = true)
public class DatabaseManagerImpl implements DatabaseManager {

    @Autowired
    private PokemonDao pokemonDao;
    @Autowired
    private EvolutionDao evolutionDao;
    @Autowired
    private EvolutionGroupDao evolutionGroupDao;
    @Autowired
    private ObjectDao objectDao;
    @Autowired
    private GenerationProfileDao generationProfileDao;

    @Transactional
    public String createDB() {

        Optional<PokemonEntity> optional = pokemonDao.findByName("Bulbasaur");

        if (!optional.isPresent()) {
            // ========== OBJECTS ==========

            ObjectEntity thunderstone = ObjectEntity.builder()
                    .name("Thunder Stone")
                    .build();
            objectDao.create(thunderstone);

            ObjectEntity moonstone = ObjectEntity.builder()
                    .name("Moon Stone")
                    .build();
            objectDao.create(moonstone);

            ObjectEntity firestone = ObjectEntity.builder()
                    .name("Fire Stone")
                    .build();
            objectDao.create(firestone);

            ObjectEntity leafstone = ObjectEntity.builder()
                    .name("Leaf Stone")
                    .build();
            objectDao.create(leafstone);

            ObjectEntity sunstone = ObjectEntity.builder()
                    .name("Sun Stone")
                    .build();
            objectDao.create(sunstone);

            ObjectEntity waterstone = ObjectEntity.builder()
                    .name("Water Stone")
                    .build();
            objectDao.create(waterstone);

            ObjectEntity ovalstone = ObjectEntity.builder()
                    .name("Oval Stone")
                    .build();
            objectDao.create(ovalstone);

            ObjectEntity shinystone = ObjectEntity.builder()
                    .name("Shiny Stone")
                    .build();
            objectDao.create(shinystone);

            ObjectEntity duskstone = ObjectEntity.builder()
                    .name("Dusk Stone")
                    .build();
            objectDao.create(duskstone);

            ObjectEntity dawnstone = ObjectEntity.builder()
                    .name("Dawn Stone")
                    .build();
            objectDao.create(dawnstone);

//            ======== INCENSES ========

            ObjectEntity seaincense = ObjectEntity.builder()
                    .name("Sea Incense")
                    .build();
            objectDao.create(seaincense);

            ObjectEntity laxincense = ObjectEntity.builder()
                    .name("Lax Incense")
                    .build();
            objectDao.create(laxincense);

            ObjectEntity roseincense = ObjectEntity.builder()
                    .name("Rose Incense")
                    .build();
            objectDao.create(roseincense);

            ObjectEntity rockincense = ObjectEntity.builder()
                    .name("Rock Incense")
                    .build();
            objectDao.create(rockincense);

            ObjectEntity oddincense = ObjectEntity.builder()
                    .name("Odd Incense")
                    .build();
            objectDao.create(oddincense);

            ObjectEntity luckincense = ObjectEntity.builder()
                    .name("Luck Incense")
                    .build();
            objectDao.create(luckincense);

            ObjectEntity pureincense = ObjectEntity.builder()
                    .name("Pure Incense")
                    .build();
            objectDao.create(pureincense);

            ObjectEntity fullincense = ObjectEntity.builder()
                    .name("Full Incense")
                    .build();
            objectDao.create(fullincense);

            ObjectEntity waveincense = ObjectEntity.builder()
                    .name("Wave Incense")
                    .build();
            objectDao.create(waveincense);

            // ========== POKEMONS + EVOLUTIONS ==========

            EvolutionGroupEntity bulbasaurEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity bulbasaur = PokemonEntity.builder()
                    .nationalId(1)
                    .name("Bulbasaur")
                    .build();

            GenerationProfileEntity bulbasaurGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(bulbasaur)
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.GRASS, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(bulbasaurEvolution)
                    .build();
            generationProfileDao.create(bulbasaurGEN6);

            PokemonEntity ivysaur = PokemonEntity.builder()
                    .nationalId(2)
                    .name("Ivysaur")
                    .build();

            GenerationProfileEntity ivysaurGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(ivysaur)
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.GRASS, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(bulbasaurEvolution)
                    .build();
            generationProfileDao.create(ivysaurGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(bulbasaurGEN6)
                    .nextPokemon(ivysaurGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(16)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity venusaur = PokemonEntity.builder()
                    .nationalId(3)
                    .name("Venusaur")
                    .build();

            GenerationProfileEntity venusaurGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(venusaur)
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.GRASS, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(bulbasaurEvolution)
                    .build();
            generationProfileDao.create(venusaurGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(ivysaurGEN6)
                    .nextPokemon(venusaurGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(32)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity charmanderEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity charmander = PokemonEntity.builder()
                    .nationalId(4)
                    .name("Charmander")
                    .build();

            GenerationProfileEntity charmanderGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(charmander)
                    .type1(Type.FIRE)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(charmanderEvolution)
                    .build();
            generationProfileDao.create(charmanderGEN6);

            PokemonEntity charmeleon = PokemonEntity.builder()
                    .nationalId(5)
                    .name("Charmeleon")
                    .build();

            GenerationProfileEntity charmeleonGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(charmeleon)
                    .type1(Type.FIRE)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(charmanderEvolution)
                    .build();
            generationProfileDao.create(charmeleonGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(charmanderGEN6)
                    .nextPokemon(charmeleonGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(16)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity charizard = PokemonEntity.builder()
                    .nationalId(6)
                    .name("Charizard")
                    .build();

            GenerationProfileEntity charizardGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(charizard)
                    .type1(Type.FIRE)
                    .type2(Type.FLYING)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(charmanderEvolution)
                    .build();
            generationProfileDao.create(charizardGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(charmeleonGEN6)
                    .nextPokemon(charizardGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(36)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//      -----

            EvolutionGroupEntity squirtleEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity squirtle = PokemonEntity.builder()
                    .nationalId(7)
                    .name("Squirtle")
                    .build();

            GenerationProfileEntity squirtleGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(squirtle)
                    .type1(Type.WATER)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.MONSTER, EggGroup.WATER1))
                    .eggCycles(21)
                    .evolutionGroup(squirtleEvolution)
                    .build();
            generationProfileDao.create(squirtleGEN6);

            PokemonEntity wartortle = PokemonEntity.builder()
                    .nationalId(8)
                    .name("Wartortle")
                    .build();

            GenerationProfileEntity wartortleGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(wartortle)
                    .type1(Type.WATER)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.MONSTER, EggGroup.WATER1))
                    .eggCycles(21)
                    .evolutionGroup(squirtleEvolution)
                    .build();
            generationProfileDao.create(wartortleGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(squirtleGEN6)
                    .nextPokemon(wartortleGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(16)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity blastoise = PokemonEntity.builder()
                    .nationalId(9)
                    .name("Blastoise")
                    .build();

            GenerationProfileEntity blastoiseGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(blastoise)
                    .type1(Type.WATER)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.MONSTER, EggGroup.WATER1))
                    .eggCycles(21)
                    .evolutionGroup(squirtleEvolution)
                    .build();
            generationProfileDao.create(blastoiseGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(wartortleGEN6)
                    .nextPokemon(blastoiseGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(36)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity caterpieEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity caterpie = PokemonEntity.builder()
                    .nationalId(10)
                    .name("Caterpie")
                    .build();

            GenerationProfileEntity caterpieGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(caterpie)
                    .type1(Type.BUG)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .evolutionGroup(caterpieEvolution)
                    .build();
            generationProfileDao.create(caterpieGEN6);

            PokemonEntity metapod = PokemonEntity.builder()
                    .nationalId(11)
                    .name("Metapod")
                    .build();

            GenerationProfileEntity metapodGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(metapod)
                    .type1(Type.BUG)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .evolutionGroup(caterpieEvolution)
                    .build();
            generationProfileDao.create(metapodGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(caterpieGEN6)
                    .nextPokemon(metapodGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(7)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity butterfree = PokemonEntity.builder()
                    .nationalId(12)
                    .name("Butterfree")
                    .build();

            GenerationProfileEntity butterfreeGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(butterfree)
                    .type1(Type.BUG)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .evolutionGroup(caterpieEvolution)
                    .build();
            generationProfileDao.create(butterfreeGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(metapodGEN6)
                    .nextPokemon(butterfreeGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(10)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity weedleEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity weedle = PokemonEntity.builder()
                    .nationalId(13)
                    .name("Weedle")
                    .build();

            GenerationProfileEntity weedleGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(weedle)
                    .type1(Type.BUG)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .evolutionGroup(weedleEvolution)
                    .build();
            generationProfileDao.create(weedleGEN6);

            PokemonEntity kakuna = PokemonEntity.builder()
                    .nationalId(14)
                    .name("Kakuna")
                    .build();

            GenerationProfileEntity kakunaGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(kakuna)
                    .type1(Type.BUG)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .evolutionGroup(weedleEvolution)
                    .build();
            generationProfileDao.create(kakunaGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(weedleGEN6)
                    .nextPokemon(kakunaGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(7)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity beedrill = PokemonEntity.builder()
                    .nationalId(15)
                    .name("Beedrill")
                    .build();

            GenerationProfileEntity beedrillGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(beedrill)
                    .type1(Type.BUG)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .evolutionGroup(weedleEvolution)
                    .build();
            generationProfileDao.create(beedrillGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(kakunaGEN6)
                    .nextPokemon(beedrillGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(10)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity pidgeyEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity pidgey = PokemonEntity.builder()
                    .nationalId(16)
                    .name("Pidgey")
                    .build();

            GenerationProfileEntity pidgeyGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(pidgey)
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(pidgeyEvolution)
                    .build();
            generationProfileDao.create(pidgeyGEN6);

            PokemonEntity pidgeotto = PokemonEntity.builder()
                    .nationalId(17)
                    .name("Pidgeotto")
                    .build();

            GenerationProfileEntity pidgeottoGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(pidgeotto)
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(pidgeyEvolution)
                    .build();
            generationProfileDao.create(pidgeottoGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(pidgeyGEN6)
                    .nextPokemon(pidgeottoGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(18)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity pidgeot = PokemonEntity.builder()
                    .nationalId(18)
                    .name("Pidgeot")
                    .build();

            GenerationProfileEntity pidgeotGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(pidgeot)
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(pidgeyEvolution)
                    .build();
            generationProfileDao.create(pidgeotGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(pidgeottoGEN6)
                    .nextPokemon(pidgeotGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(36)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity rattataEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity rattata = PokemonEntity.builder()
                    .nationalId(19)
                    .name("Rattata")
                    .build();

            GenerationProfileEntity rattataGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(rattata)
                    .type1(Type.NORMAL)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(16)
                    .evolutionGroup(rattataEvolution)
                    .build();
            generationProfileDao.create(rattataGEN6);

            PokemonEntity raticate = PokemonEntity.builder()
                    .nationalId(20)
                    .name("Raticate")
                    .build();

            GenerationProfileEntity raticateGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(raticate)
                    .type1(Type.NORMAL)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(16)
                    .evolutionGroup(rattataEvolution)
                    .build();
            generationProfileDao.create(raticateGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(rattataGEN6)
                    .nextPokemon(raticateGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(20)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity spearowEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity spearow = PokemonEntity.builder()
                    .nationalId(21)
                    .name("Spearow")
                    .build();

            GenerationProfileEntity spearowGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(spearow)
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(spearowEvolution)
                    .build();
            generationProfileDao.create(spearowGEN6);

            PokemonEntity fearow = PokemonEntity.builder()
                    .nationalId(22)
                    .name("Fearow")
                    .build();

            GenerationProfileEntity fearowGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(fearow)
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(spearowEvolution)
                    .build();
            generationProfileDao.create(fearowGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(spearowGEN6)
                    .nextPokemon(fearowGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(20)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity ekansEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity ekans = PokemonEntity.builder()
                    .nationalId(23)
                    .name("Ekans")
                    .build();

            GenerationProfileEntity ekansGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(ekans)
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.FIELD))
                    .eggCycles(21)
                    .evolutionGroup(ekansEvolution)
                    .build();
            generationProfileDao.create(ekansGEN6);

            PokemonEntity arbok = PokemonEntity.builder()
                    .nationalId(24)
                    .name("Arbok")
                    .build();

            GenerationProfileEntity arbokGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(arbok)
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.FIELD))
                    .eggCycles(21)
                    .evolutionGroup(ekansEvolution)
                    .build();
            generationProfileDao.create(arbokGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(ekansGEN6)
                    .nextPokemon(arbokGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(22)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity pikaEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity pichu = PokemonEntity.builder()
                    .nationalId(172)
                    .name("Pichu")
                    .build();

            GenerationProfileEntity pichuGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(pichu)
                    .type1(Type.ELECTRIC)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(11)
                    .evolutionGroup(pikaEvolution)
                    .build();
            generationProfileDao.create(pichuGEN6);

            PokemonEntity pika = PokemonEntity.builder()
                    .nationalId(25)
                    .name("Pikachu")
                    .build();

            GenerationProfileEntity pikachuGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(pika)
                    .type1(Type.ELECTRIC)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY, EggGroup.FIELD))
                    .eggCycles(11)
                    .evolutionGroup(pikaEvolution)
                    .build();
            generationProfileDao.create(pikachuGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(pichuGEN6)
                    .nextPokemon(pikachuGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .evolutionContext(EvolutionContext.HAPPINESS)
                            .build()))
                    .build());

            PokemonEntity raichu = PokemonEntity.builder()
                    .nationalId(26)
                    .name("Raichu")
                    .build();

            GenerationProfileEntity raichuGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(raichu)
                    .type1(Type.ELECTRIC)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY, EggGroup.FIELD))
                    .eggCycles(11)
                    .evolutionGroup(pikaEvolution)
                    .build();
            generationProfileDao.create(raichuGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(pikachuGEN6)
                    .nextPokemon(raichuGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(thunderstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity sabeEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity sandshrew = PokemonEntity.builder()
                    .nationalId(27)
                    .name("Sandshrew")
                    .build();

            GenerationProfileEntity sandshrewGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(sandshrew)
                    .type1(Type.GROUND)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(21)
                    .evolutionGroup(sabeEvolution)
                    .build();
            generationProfileDao.create(sandshrewGEN6);

            PokemonEntity sandslash = PokemonEntity.builder()
                    .nationalId(28)
                    .name("Sandslash")
                    .build();

            GenerationProfileEntity sandslashGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(sandslash)
                    .type1(Type.GROUND)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(21)
                    .evolutionGroup(sabeEvolution)
                    .build();
            generationProfileDao.create(sandslashGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(sandshrewGEN6)
                    .nextPokemon(sandslashGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(22)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity nidoFEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity nidoF = PokemonEntity.builder()
                    .nationalId(29)
                    .name("NidoranF")
                    .build();

            GenerationProfileEntity nidofGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(nidoF)
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(0d)
                    .percentFemale(100d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(nidoFEvolution)
                    .build();
            generationProfileDao.create(nidofGEN6);

            PokemonEntity nidorina = PokemonEntity.builder()
                    .nationalId(30)
                    .name("Nidorina")
                    .build();

            GenerationProfileEntity nidorinaGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(nidorina)
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(0d)
                    .percentFemale(100d)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(21)
                    .evolutionGroup(nidoFEvolution)
                    .build();
            generationProfileDao.create(nidorinaGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(nidofGEN6)
                    .nextPokemon(nidorinaGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(16)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity nidoqueen = PokemonEntity.builder()
                    .nationalId(31)
                    .name("Nidoqueen")
                    .build();

            GenerationProfileEntity nidoqueenGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(nidoqueen)
                    .type1(Type.POISON)
                    .type2(Type.GROUND)
                    .percentMale(0d)
                    .percentFemale(100d)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(21)
                    .evolutionGroup(nidoFEvolution)
                    .build();
            generationProfileDao.create(nidoqueenGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(nidorinaGEN6)
                    .nextPokemon(nidoqueenGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(moonstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity nidoMEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity nidoM = PokemonEntity.builder()
                    .nationalId(32)
                    .name("NidoranM")
                    .build();

            GenerationProfileEntity nidomGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(nidoM)
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(100d)
                    .percentFemale(0d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(nidoMEvolution)
                    .build();
            generationProfileDao.create(nidomGEN6);

            PokemonEntity nidorino = PokemonEntity.builder()
                    .nationalId(33)
                    .name("Nidorino")
                    .build();

            GenerationProfileEntity nidorinoGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(nidorino)
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(100d)
                    .percentFemale(0d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(nidoMEvolution)
                    .build();
            generationProfileDao.create(nidorinoGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(nidomGEN6)
                    .nextPokemon(nidorinoGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(16)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity nidoking = PokemonEntity.builder()
                    .nationalId(34)
                    .name("Nidoking")
                    .build();

            GenerationProfileEntity nidokingGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(nidoking)
                    .type1(Type.POISON)
                    .type2(Type.GROUND)
                    .percentMale(100d)
                    .percentFemale(0d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(nidoMEvolution)
                    .build();
            generationProfileDao.create(nidokingGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(nidorinoGEN6)
                    .nextPokemon(nidokingGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(moonstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity clefMEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity cleffa = PokemonEntity.builder()
                    .nationalId(173)
                    .name("Cleffa")
                    .build();

            GenerationProfileEntity cleffaGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(cleffa)
                    .type1(Type.FAIRY)
                    .type2(null)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(11)
                    .evolutionGroup(clefMEvolution)
                    .build();
            generationProfileDao.create(cleffaGEN6);

            PokemonEntity clefairy = PokemonEntity.builder()
                    .nationalId(35)
                    .name("Clefairy")
                    .build();

            GenerationProfileEntity clefairyGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(clefairy)
                    .type1(Type.FAIRY)
                    .type2(null)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY))
                    .eggCycles(11)
                    .evolutionGroup(clefMEvolution)
                    .build();
            generationProfileDao.create(clefairyGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(cleffaGEN6)
                    .nextPokemon(clefairyGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .evolutionContext(EvolutionContext.HAPPINESS)
                            .build()))
                    .build());

            PokemonEntity clefable = PokemonEntity.builder()
                    .nationalId(36)
                    .name("Clefable")
                    .build();

            GenerationProfileEntity clefableGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(clefable)
                    .type1(Type.FAIRY)
                    .type2(null)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY))
                    .eggCycles(11)
                    .evolutionGroup(clefMEvolution)
                    .build();
            generationProfileDao.create(clefableGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(clefairyGEN6)
                    .nextPokemon(clefableGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(moonstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity vulpixMEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity vulpix = PokemonEntity.builder()
                    .nationalId(37)
                    .name("Vulpix")
                    .build();

            GenerationProfileEntity vulpixGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(vulpix)
                    .type1(Type.FIRE)
                    .type2(null)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(21)
                    .evolutionGroup(vulpixMEvolution)
                    .build();
            generationProfileDao.create(vulpixGEN6);

            PokemonEntity ninetales = PokemonEntity.builder()
                    .nationalId(38)
                    .name("Ninetales")
                    .build();

            GenerationProfileEntity ninetalesGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(ninetales)
                    .type1(Type.FIRE)
                    .type2(null)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(21)
                    .evolutionGroup(vulpixMEvolution)
                    .build();
            generationProfileDao.create(ninetalesGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(vulpixGEN6)
                    .nextPokemon(ninetalesGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(firestone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity jiggMEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(jiggMEvolution);

            PokemonEntity igglybuff = PokemonEntity.builder()
                    .nationalId(174)
                    .name("Igglybuff")
                    .build();

            GenerationProfileEntity igglyGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(igglybuff)
                    .type1(Type.NORMAL)
                    .type2(Type.FAIRY)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(11)
                    .evolutionGroup(jiggMEvolution)
                    .build();
            generationProfileDao.create(igglyGEN6);

            PokemonEntity jigglypuff = PokemonEntity.builder()
                    .nationalId(39)
                    .name("Jigglypuff")
                    .build();

            GenerationProfileEntity jigglyGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(jigglypuff)
                    .type1(Type.NORMAL)
                    .type2(Type.FAIRY)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY))
                    .eggCycles(11)
                    .evolutionGroup(jiggMEvolution)
                    .build();
            generationProfileDao.create(jigglyGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(igglyGEN6)
                    .nextPokemon(jigglyGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .evolutionContext(EvolutionContext.HAPPINESS)
                            .build()))
                    .build());

            PokemonEntity wigglytuff = PokemonEntity.builder()
                    .nationalId(40)
                    .name("Wigglytuff")
                    .build();

            GenerationProfileEntity wigglyGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(wigglytuff)
                    .type1(Type.NORMAL)
                    .type2(Type.FAIRY)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY))
                    .eggCycles(11)
                    .evolutionGroup(jiggMEvolution)
                    .build();
            generationProfileDao.create(wigglyGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(jigglyGEN6)
                    .nextPokemon(wigglyGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(moonstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity batMEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity zubat = PokemonEntity.builder()
                    .nationalId(41)
                    .name("Zubat")
                    .build();

            GenerationProfileEntity zubatGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(zubat)
                    .type1(Type.POISON)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(batMEvolution)
                    .build();
            generationProfileDao.create(zubatGEN6);

            PokemonEntity golbat = PokemonEntity.builder()
                    .nationalId(42)
                    .name("Golbat")
                    .build();

            GenerationProfileEntity golbatGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(golbat)
                    .type1(Type.POISON)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(batMEvolution)
                    .build();
            generationProfileDao.create(golbatGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(zubatGEN6)
                    .nextPokemon(golbatGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(22)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());
            PokemonEntity crobat = PokemonEntity.builder()
                    .nationalId(169)
                    .name("Crobat")
                    .build();

            GenerationProfileEntity crobatGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(crobat)
                    .type1(Type.POISON)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(batMEvolution)
                    .build();
            generationProfileDao.create(crobatGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(golbatGEN6)
                    .nextPokemon(crobatGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .evolutionContext(EvolutionContext.HAPPINESS)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity oddishEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(oddishEvolution);

            PokemonEntity oddish = PokemonEntity.builder()
                    .nationalId(43)
                    .name("Oddish")
                    .build();

            GenerationProfileEntity oddishGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(oddish)
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.GRASS))
                    .eggCycles(21)
                    .evolutionGroup(oddishEvolution)
                    .build();
            generationProfileDao.create(oddishGEN6);

            PokemonEntity gloom = PokemonEntity.builder()
                    .nationalId(44)
                    .name("Gloom")
                    .build();

            GenerationProfileEntity gloomGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(gloom)
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.GRASS))
                    .eggCycles(21)
                    .evolutionGroup(oddishEvolution)
                    .build();
            generationProfileDao.create(gloomGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(oddishGEN6)
                    .nextPokemon(gloomGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(21)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity vileplume = PokemonEntity.builder()
                    .nationalId(45)
                    .name("Vileplume")
                    .build();

            GenerationProfileEntity vileGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(vileplume)
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.GRASS))
                    .eggCycles(21)
                    .evolutionGroup(oddishEvolution)
                    .build();
            generationProfileDao.create(vileGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(gloomGEN6)
                    .nextPokemon(vileGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(leafstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

            PokemonEntity bellossom = PokemonEntity.builder()
                    .nationalId(182)
                    .name("Bellossom")
                    .build();

            GenerationProfileEntity bellossomGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(bellossom)
                    .type1(Type.GRASS)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.GRASS))
                    .eggCycles(21)
                    .evolutionGroup(oddishEvolution)
                    .build();
            generationProfileDao.create(bellossomGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(gloomGEN6)
                    .nextPokemon(bellossomGEN6)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(sunstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity parasEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity paras = PokemonEntity.builder()
                    .nationalId(46)
                    .name("Paras")
                    .build();

            GenerationProfileEntity parasGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(paras)
                    .type1(Type.BUG)
                    .type2(Type.GRASS)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG, EggGroup.GRASS))
                    .eggCycles(21)
                    .evolutionGroup(parasEvolution)
                    .build();
            generationProfileDao.create(parasGEN6);

            PokemonEntity parasect = PokemonEntity.builder()
                    .nationalId(47)
                    .name("Parasect")
                    .build();

            GenerationProfileEntity parasectGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(parasect)
                    .type1(Type.BUG)
                    .type2(Type.GRASS)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG, EggGroup.GRASS))
                    .eggCycles(21)
                    .evolutionGroup(parasEvolution)
                    .build();
            generationProfileDao.create(parasectGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(parasGEN6)
                    .nextPokemon(parasectGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(24)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity snorlaxEvolution = EvolutionGroupEntity.builder().build();

            PokemonEntity munchlax = PokemonEntity.builder()
                    .nationalId(446)
                    .name("Munchlax")
                    .build();

            GenerationProfileEntity munchlaxGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(munchlax)
                    .type1(Type.NORMAL)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(41)
                    .evolutionGroup(snorlaxEvolution)
                    .requiredObjectForEgg(fullincense)
                    .build();
            generationProfileDao.create(munchlaxGEN6);

            PokemonEntity snorlax = PokemonEntity.builder()
                    .nationalId(143)
                    .name("Snorlax")
                    .build();

            GenerationProfileEntity snorlaxGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(snorlax)
                    .type1(Type.NORMAL)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.MONSTER))
                    .eggCycles(41)
                    .evolutionGroup(snorlaxEvolution)
                    .build();
            generationProfileDao.create(snorlaxGEN6);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(munchlaxGEN6)
                    .nextPokemon(snorlaxGEN6)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .evolutionContext(EvolutionContext.HAPPINESS)
                            .build()))
                    .build());

//        -----

            PokemonEntity ditto = PokemonEntity.builder()
                    .nationalId(132)
                    .name("Ditto")
                    .build();

            GenerationProfileEntity dittoGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(ditto)
                    .type1(Type.NORMAL)
                    .type2(null)
                    .percentMale(null)
                    .percentFemale(null)
                    .eggGroups(Arrays.asList(EggGroup.DITTO))
                    .eggCycles(21)
                    .build();
            generationProfileDao.create(dittoGEN6);

            PokemonEntity mew = PokemonEntity.builder()
                    .nationalId(151)
                    .name("Mew")
                    .build();

            GenerationProfileEntity mewGEN6 = GenerationProfileEntity.builder()
                    .generation(Generation.GEN6)
                    .pokemon(mew)
                    .type1(Type.PSYCHIC)
                    .type2(null)
                    .percentMale(null)
                    .percentFemale(null)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(121)
                    .build();
            generationProfileDao.create(mewGEN6);

            return "OK";
        } else {
            return "DB already exists";
        }
    }

}
