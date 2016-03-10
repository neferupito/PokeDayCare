package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.EvolutionGroupDao;
import com.nefee.pokedaycare.data.dao.EvolutionDao;
import com.nefee.pokedaycare.data.dao.ObjectDao;
import com.nefee.pokedaycare.data.dao.PokemonDao;
import com.nefee.pokedaycare.data.entity.ObjectEntity;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.data.entity.Type;
import com.nefee.pokedaycare.data.entity.breeding.EggGroup;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionContext;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionGroupEntity;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionEntity;
import com.nefee.pokedaycare.data.entity.evolution.EvolveConditionEntity;
import com.nefee.pokedaycare.logic.manager.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service("databaseManager")
@Transactional(readOnly = true)
public class DatabaseManagerImpl implements DatabaseManager {

    @Autowired
    private PokemonDao pokemonDao;
    @Autowired
    private EvolutionDao evolutionDao;
    @Autowired
    private EvolutionGroupDao evolutionGroupDao;
    @Autowired
    private ObjectDao objectDao;

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

            // ========== POKEMONS + EVOLUTIONS ==========

            EvolutionGroupEntity bulbasaurEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(bulbasaurEvolution);

            PokemonEntity bulbasaur = PokemonEntity.builder()
                    .nationalId(1)
                    .name("Bulbasaur")
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.GRASS, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(bulbasaurEvolution)
                    .build();
            pokemonDao.create(bulbasaur);
            
            PokemonEntity ivysaur = PokemonEntity.builder()
                    .nationalId(2)
                    .name("Ivysaur")
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.GRASS, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(bulbasaurEvolution)
                    .build();
            pokemonDao.create(ivysaur);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(bulbasaur)
                    .nextPokemon(ivysaur)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(16)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity venusaur = PokemonEntity.builder()
                    .nationalId(3)
                    .name("Venusaur")
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.GRASS, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(bulbasaurEvolution)
                    .build();
            pokemonDao.create(venusaur);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(ivysaur)
                    .nextPokemon(venusaur)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(32)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity charmanderEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(charmanderEvolution);

            PokemonEntity charmander = PokemonEntity.builder()
                    .nationalId(4)
                    .name("Charmander")
                    .type1(Type.FIRE)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(charmanderEvolution)
                    .build();
            pokemonDao.create(charmander);

            PokemonEntity charmeleon = PokemonEntity.builder()
                    .nationalId(5)
                    .name("Charmeleon")
                    .type1(Type.FIRE)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(charmanderEvolution)
                    .build();
            pokemonDao.create(charmeleon);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(charmander)
                    .nextPokemon(charmeleon)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(16)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity charizard = PokemonEntity.builder()
                    .nationalId(6)
                    .name("Charizard")
                    .type1(Type.FIRE)
                    .type2(Type.FLYING)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(charmanderEvolution)
                    .build();
            pokemonDao.create(charizard);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(charmeleon)
                    .nextPokemon(charizard)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(36)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//      -----

            EvolutionGroupEntity squirtleEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(squirtleEvolution);

            PokemonEntity squirtle = PokemonEntity.builder()
                    .nationalId(7)
                    .name("Squirtle")
                    .type1(Type.WATER)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.MONSTER, EggGroup.WATER1))
                    .eggCycles(21)
                    .evolutionGroup(squirtleEvolution)
                    .build();
            pokemonDao.create(squirtle);

            PokemonEntity wartortle = PokemonEntity.builder()
                    .nationalId(8)
                    .name("Wartortle")
                    .type1(Type.WATER)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.MONSTER, EggGroup.WATER1))
                    .eggCycles(21)
                    .evolutionGroup(squirtleEvolution)
                    .build();
            pokemonDao.create(wartortle);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(squirtle)
                    .nextPokemon(wartortle)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(16)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity blastoise = PokemonEntity.builder()
                    .nationalId(9)
                    .name("Blastoise")
                    .type1(Type.WATER)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.MONSTER, EggGroup.WATER1))
                    .eggCycles(21)
                    .evolutionGroup(squirtleEvolution)
                    .build();
            pokemonDao.create(blastoise);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(wartortle)
                    .nextPokemon(blastoise)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(36)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity caterpieEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(caterpieEvolution);

            PokemonEntity caterpie = PokemonEntity.builder()
                    .nationalId(10)
                    .name("Caterpie")
                    .type1(Type.BUG)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .evolutionGroup(caterpieEvolution)
                    .build();
            pokemonDao.create(caterpie);

            PokemonEntity metapod = PokemonEntity.builder()
                    .nationalId(11)
                    .name("Metapod")
                    .type1(Type.BUG)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .evolutionGroup(caterpieEvolution)
                    .build();
            pokemonDao.create(metapod);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(caterpie)
                    .nextPokemon(metapod)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(7)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity butterfree = PokemonEntity.builder()
                    .nationalId(12)
                    .name("Butterfree")
                    .type1(Type.BUG)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .evolutionGroup(caterpieEvolution)
                    .build();
            pokemonDao.create(butterfree);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(metapod)
                    .nextPokemon(butterfree)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(10)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity weedleEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(weedleEvolution);

            PokemonEntity weedle = PokemonEntity.builder()
                    .nationalId(13)
                    .name("Weedle")
                    .type1(Type.BUG)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .evolutionGroup(weedleEvolution)
                    .build();
            pokemonDao.create(weedle);

            PokemonEntity kakuna = PokemonEntity.builder()
                    .nationalId(14)
                    .name("Kakuna")
                    .type1(Type.BUG)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .evolutionGroup(weedleEvolution)
                    .build();
            pokemonDao.create(kakuna);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(weedle)
                    .nextPokemon(kakuna)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(7)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity beedrill = PokemonEntity.builder()
                    .nationalId(15)
                    .name("Beedrill")
                    .type1(Type.BUG)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .evolutionGroup(weedleEvolution)
                    .build();
            pokemonDao.create(beedrill);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(kakuna)
                    .nextPokemon(beedrill)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(10)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity pidgeyEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(pidgeyEvolution);

            PokemonEntity pidgey = PokemonEntity.builder()
                    .nationalId(16)
                    .name("Pidgey")
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(pidgeyEvolution)
                    .build();
            pokemonDao.create(pidgey);

            PokemonEntity pidgeotto = PokemonEntity.builder()
                    .nationalId(17)
                    .name("Pidgeotto")
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(pidgeyEvolution)
                    .build();
            pokemonDao.create(pidgeotto);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(pidgey)
                    .nextPokemon(pidgeotto)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(18)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity pidgeot = PokemonEntity.builder()
                    .nationalId(18)
                    .name("Pidgeot")
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(pidgeyEvolution)
                    .build();
            pokemonDao.create(pidgeot);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(pidgeotto)
                    .nextPokemon(pidgeot)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(36)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity rattataEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(rattataEvolution);

            PokemonEntity rattata = PokemonEntity.builder()
                    .nationalId(19)
                    .name("Rattata")
                    .type1(Type.NORMAL)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(16)
                    .evolutionGroup(rattataEvolution)
                    .build();
            pokemonDao.create(rattata);

            PokemonEntity raticate = PokemonEntity.builder()
                    .nationalId(20)
                    .name("Raticate")
                    .type1(Type.NORMAL)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(16)
                    .evolutionGroup(rattataEvolution)
                    .build();
            pokemonDao.create(raticate);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(rattata)
                    .nextPokemon(raticate)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(20)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity spearowEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(spearowEvolution);

            PokemonEntity spearow = PokemonEntity.builder()
                    .nationalId(21)
                    .name("Spearow")
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(spearowEvolution)
                    .build();
            pokemonDao.create(spearow);

            PokemonEntity fearow = PokemonEntity.builder()
                    .nationalId(22)
                    .name("Fearow")
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(spearowEvolution)
                    .build();
            pokemonDao.create(fearow);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(spearow)
                    .nextPokemon(fearow)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(20)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity ekansEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(ekansEvolution);

            PokemonEntity ekans = PokemonEntity.builder()
                    .nationalId(23)
                    .name("Ekans")
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.FIELD))
                    .eggCycles(21)
                    .evolutionGroup(ekansEvolution)
                    .build();
            pokemonDao.create(ekans);

            PokemonEntity arbok = PokemonEntity.builder()
                    .nationalId(24)
                    .name("Arbok")
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.FIELD))
                    .eggCycles(21)
                    .evolutionGroup(ekansEvolution)
                    .build();
            pokemonDao.create(arbok);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(ekans)
                    .nextPokemon(arbok)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(22)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity pikaEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(pikaEvolution);

            PokemonEntity pichu = PokemonEntity.builder()
                    .nationalId(172)
                    .name("Pichu")
                    .type1(Type.ELECTRIC)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(11)
                    .evolutionGroup(pikaEvolution)
                    .build();
            pokemonDao.create(pichu);

            PokemonEntity pika = PokemonEntity.builder()
                    .nationalId(25)
                    .name("Pikachu")
                    .type1(Type.ELECTRIC)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY, EggGroup.FIELD))
                    .eggCycles(11)
                    .evolutionGroup(pikaEvolution)
                    .build();
            pokemonDao.create(pika);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(pichu)
                    .nextPokemon(pika)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .evolutionContext(EvolutionContext.HAPPINESS)
                            .build()))
                    .build());

            PokemonEntity raichu = PokemonEntity.builder()
                    .nationalId(26)
                    .name("Raichu")
                    .type1(Type.ELECTRIC)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY, EggGroup.FIELD))
                    .eggCycles(11)
                    .evolutionGroup(pikaEvolution)
                    .build();
            pokemonDao.create(raichu);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(pika)
                    .nextPokemon(raichu)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(thunderstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity sabeEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(sabeEvolution);

            PokemonEntity sandshrew = PokemonEntity.builder()
                    .nationalId(27)
                    .name("Sandshrew")
                    .type1(Type.GROUND)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(21)
                    .evolutionGroup(sabeEvolution)
                    .build();
            pokemonDao.create(sandshrew);

            PokemonEntity sandslash = PokemonEntity.builder()
                    .nationalId(28)
                    .name("Sandslash")
                    .type1(Type.GROUND)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(21)
                    .evolutionGroup(sabeEvolution)
                    .build();
            pokemonDao.create(sandslash);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(sandshrew)
                    .nextPokemon(sandslash)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(22)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity nidoFEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(nidoFEvolution);

            PokemonEntity nidoF = PokemonEntity.builder()
                    .nationalId(29)
                    .name("NidoranF")
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(0d)
                    .percentFemale(100d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(nidoFEvolution)
                    .build();
            pokemonDao.create(nidoF);

            PokemonEntity nidorina = PokemonEntity.builder()
                    .nationalId(30)
                    .name("Nidorina")
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(0d)
                    .percentFemale(100d)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(21)
                    .evolutionGroup(nidoFEvolution)
                    .build();
            pokemonDao.create(nidorina);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(nidoF)
                    .nextPokemon(nidorina)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(16)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity nidoqueen = PokemonEntity.builder()
                    .nationalId(31)
                    .name("Nidoqueen")
                    .type1(Type.POISON)
                    .type2(Type.GROUND)
                    .percentMale(0d)
                    .percentFemale(100d)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(21)
                    .evolutionGroup(nidoFEvolution)
                    .build();
            pokemonDao.create(nidoqueen);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(nidorina)
                    .nextPokemon(nidoqueen)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(moonstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity nidoMEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(nidoMEvolution);

            PokemonEntity nidoM = PokemonEntity.builder()
                    .nationalId(32)
                    .name("NidoranM")
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(100d)
                    .percentFemale(0d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(nidoMEvolution)
                    .build();
            pokemonDao.create(nidoM);

            PokemonEntity nidorino = PokemonEntity.builder()
                    .nationalId(33)
                    .name("Nidorino")
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(100d)
                    .percentFemale(0d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(nidoMEvolution)
                    .build();
            pokemonDao.create(nidorino);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(nidoM)
                    .nextPokemon(nidorino)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(16)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity nidoking = PokemonEntity.builder()
                    .nationalId(34)
                    .name("Nidoking")
                    .type1(Type.POISON)
                    .type2(Type.GROUND)
                    .percentMale(100d)
                    .percentFemale(0d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD, EggGroup.MONSTER))
                    .eggCycles(21)
                    .evolutionGroup(nidoMEvolution)
                    .build();
            pokemonDao.create(nidoking);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(nidorino)
                    .nextPokemon(nidoking)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(moonstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity clefMEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(clefMEvolution);

            PokemonEntity cleffa = PokemonEntity.builder()
                    .nationalId(173)
                    .name("Cleffa")
                    .type1(Type.FAIRY)
                    .type2(null)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(11)
                    .evolutionGroup(clefMEvolution)
                    .build();
            pokemonDao.create(cleffa);

            PokemonEntity clefairy = PokemonEntity.builder()
                    .nationalId(35)
                    .name("Clefairy")
                    .type1(Type.FAIRY)
                    .type2(null)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY))
                    .eggCycles(11)
                    .evolutionGroup(clefMEvolution)
                    .build();
            pokemonDao.create(clefairy);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(cleffa)
                    .nextPokemon(clefairy)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .evolutionContext(EvolutionContext.HAPPINESS)
                            .build()))
                    .build());

            PokemonEntity clefable = PokemonEntity.builder()
                    .nationalId(36)
                    .name("Clefable")
                    .type1(Type.FAIRY)
                    .type2(null)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY))
                    .eggCycles(11)
                    .evolutionGroup(clefMEvolution)
                    .build();
            pokemonDao.create(clefable);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(clefairy)
                    .nextPokemon(clefable)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(moonstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity vulpixMEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(vulpixMEvolution);

            PokemonEntity vulpix = PokemonEntity.builder()
                    .nationalId(37)
                    .name("Vulpix")
                    .type1(Type.FIRE)
                    .type2(null)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(21)
                    .evolutionGroup(vulpixMEvolution)
                    .build();
            pokemonDao.create(vulpix);

            PokemonEntity ninetales = PokemonEntity.builder()
                    .nationalId(38)
                    .name("Ninetales")
                    .type1(Type.FIRE)
                    .type2(null)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(21)
                    .evolutionGroup(vulpixMEvolution)
                    .build();
            pokemonDao.create(ninetales);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(vulpix)
                    .nextPokemon(ninetales)
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
                    .type1(Type.NORMAL)
                    .type2(Type.FAIRY)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(11)
                    .evolutionGroup(jiggMEvolution)
                    .build();
            pokemonDao.create(igglybuff);

            PokemonEntity jigglypuff = PokemonEntity.builder()
                    .nationalId(39)
                    .name("Jigglypuff")
                    .type1(Type.NORMAL)
                    .type2(Type.FAIRY)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY))
                    .eggCycles(11)
                    .evolutionGroup(jiggMEvolution)
                    .build();
            pokemonDao.create(jigglypuff);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(igglybuff)
                    .nextPokemon(jigglypuff)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .evolutionContext(EvolutionContext.HAPPINESS)
                            .build()))
                    .build());

            PokemonEntity wigglytuff = PokemonEntity.builder()
                    .nationalId(40)
                    .name("Wigglytuff")
                    .type1(Type.NORMAL)
                    .type2(Type.FAIRY)
                    .percentMale(25d)
                    .percentFemale(75d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY))
                    .eggCycles(11)
                    .evolutionGroup(jiggMEvolution)
                    .build();
            pokemonDao.create(wigglytuff);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(jigglypuff)
                    .nextPokemon(wigglytuff)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(moonstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity batMEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(batMEvolution);

            PokemonEntity zubat = PokemonEntity.builder()
                    .nationalId(41)
                    .name("Zubat")
                    .type1(Type.POISON)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(batMEvolution)
                    .build();
            pokemonDao.create(zubat);

            PokemonEntity golbat = PokemonEntity.builder()
                    .nationalId(42)
                    .name("Golbat")
                    .type1(Type.POISON)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(batMEvolution)
                    .build();
            pokemonDao.create(golbat);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(zubat)
                    .nextPokemon(golbat)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(22)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());
            PokemonEntity crobat = PokemonEntity.builder()
                    .nationalId(169)
                    .name("Crobat")
                    .type1(Type.POISON)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .evolutionGroup(batMEvolution)
                    .build();
            pokemonDao.create(crobat);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(golbat)
                    .nextPokemon(crobat)
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
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.GRASS))
                    .eggCycles(21)
                    .evolutionGroup(oddishEvolution)
                    .build();
            pokemonDao.create(oddish);

            PokemonEntity gloom = PokemonEntity.builder()
                    .nationalId(44)
                    .name("Gloom")
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.GRASS))
                    .eggCycles(21)
                    .evolutionGroup(oddishEvolution)
                    .build();
            pokemonDao.create(gloom);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(oddish)
                    .nextPokemon(gloom)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(21)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());

            PokemonEntity vileplume = PokemonEntity.builder()
                    .nationalId(45)
                    .name("Vileplume")
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.GRASS))
                    .eggCycles(21)
                    .evolutionGroup(oddishEvolution)
                    .build();
            pokemonDao.create(vileplume);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(gloom)
                    .nextPokemon(vileplume)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(leafstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

            PokemonEntity bellossom = PokemonEntity.builder()
                    .nationalId(182)
                    .name("Bellossom")
                    .type1(Type.GRASS)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.GRASS))
                    .eggCycles(21)
                    .evolutionGroup(oddishEvolution)
                    .build();
            pokemonDao.create(bellossom);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(gloom)
                    .nextPokemon(bellossom)
                    .evolutionRank(2)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .object(sunstone)
                            .evolutionContext(EvolutionContext.USE_OBJECT)
                            .build()))
                    .build());

//        -----

            EvolutionGroupEntity parasEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(parasEvolution);

            PokemonEntity paras = PokemonEntity.builder()
                    .nationalId(46)
                    .name("Paras")
                    .type1(Type.BUG)
                    .type2(Type.GRASS)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG, EggGroup.GRASS))
                    .eggCycles(21)
                    .evolutionGroup(parasEvolution)
                    .build();
            pokemonDao.create(paras);

            PokemonEntity parasect = PokemonEntity.builder()
                    .nationalId(47)
                    .name("Parasect")
                    .type1(Type.BUG)
                    .type2(Type.GRASS)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG, EggGroup.GRASS))
                    .eggCycles(21)
                    .evolutionGroup(parasEvolution)
                    .build();
            pokemonDao.create(parasect);
            evolutionDao.create(EvolutionEntity.builder()
                    .previousPokemon(paras)
                    .nextPokemon(parasect)
                    .evolutionRank(1)
                    .conditions(Arrays.asList(EvolveConditionEntity.builder()
                            .level(24)
                            .evolutionContext(EvolutionContext.LEVEL)
                            .build()))
                    .build());


            PokemonEntity ditto = PokemonEntity.builder()
                    .nationalId(132)
                    .name("Ditto")
                    .type1(Type.NORMAL)
                    .type2(null)
                    .percentMale(null)
                    .percentFemale(null)
                    .eggGroups(Arrays.asList(EggGroup.DITTO))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(ditto);

            return "OK";
        } else {
            return "DB already exists";
        }
    }

}
