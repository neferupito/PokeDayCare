package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.*;
import com.nefee.pokedaycare.data.entity.*;
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
    private TypeDao typeDao;
    @Autowired
    private EggGroupDao eggGroupDao;
    @Autowired
    private EvolutionDao evolutionDao;
    @Autowired
    private EvolutionGroupDao evolutionGroupDao;

    @Transactional
    public String createDB() {

        Optional<PokemonEntity> optional = pokemonDao.findByName("Bulbasaur");

        if (!optional.isPresent()) {

            // ========== TYPES ==========

            TypeEntity normal = TypeEntity.builder()
                    .name("Normal")
                    .build();
            typeDao.create(normal);
            TypeEntity fire = TypeEntity.builder()
                    .name("Fire")
                    .build();
            typeDao.create(fire);
            TypeEntity water = TypeEntity.builder()
                    .name("Water")
                    .build();
            typeDao.create(water);
            TypeEntity electric = TypeEntity.builder()
                    .name("Electric")
                    .build();
            typeDao.create(electric);
            TypeEntity grass = TypeEntity.builder()
                    .name("Grass")
                    .build();
            typeDao.create(grass);
            TypeEntity ice = TypeEntity.builder()
                    .name("Ice")
                    .build();
            typeDao.create(ice);
            TypeEntity fighting = TypeEntity.builder()
                    .name("Fighting")
                    .build();
            typeDao.create(fighting);
            TypeEntity poison = TypeEntity.builder()
                    .name("Poison")
                    .build();
            typeDao.create(poison);
            TypeEntity ground = TypeEntity.builder()
                    .name("Ground")
                    .build();
            typeDao.create(ground);
            TypeEntity flying = TypeEntity.builder()
                    .name("Flying")
                    .build();
            typeDao.create(flying);
            TypeEntity psychic = TypeEntity.builder()
                    .name("Psychic")
                    .build();
            typeDao.create(psychic);
            TypeEntity bug = TypeEntity.builder()
                    .name("Bug")
                    .build();
            typeDao.create(bug);
            TypeEntity rock = TypeEntity.builder()
                    .name("Rock")
                    .build();
            typeDao.create(rock);
            TypeEntity ghost = TypeEntity.builder()
                    .name("Ghost")
                    .build();
            typeDao.create(ghost);
            TypeEntity dragon = TypeEntity.builder()
                    .name("Dragon")
                    .build();
            typeDao.create(dragon);
            TypeEntity dark = TypeEntity.builder()
                    .name("Dark")
                    .build();
            typeDao.create(dark);
            TypeEntity steel = TypeEntity.builder()
                    .name("Steel")
                    .build();
            typeDao.create(steel);
            TypeEntity fairy = TypeEntity.builder()
                    .name("Fairy")
                    .build();
            typeDao.create(fairy);

            // ========== EGG GROUPS ==========

            EggGroupEntity eggAmorphous = EggGroupEntity.builder()
                    .name("Amorphous")
                    .build();
            eggGroupDao.create(eggAmorphous);
            EggGroupEntity eggBug = EggGroupEntity.builder()
                    .name("Bug")
                    .build();
            eggGroupDao.create(eggBug);
            EggGroupEntity eggDragon = EggGroupEntity.builder()
                    .name("Dragon")
                    .build();
            eggGroupDao.create(eggDragon);
            EggGroupEntity eggFairy = EggGroupEntity.builder()
                    .name("Fairy")
                    .build();
            eggGroupDao.create(eggFairy);
            EggGroupEntity eggField = EggGroupEntity.builder()
                    .name("Field")
                    .build();
            eggGroupDao.create(eggField);
            EggGroupEntity eggFlying = EggGroupEntity.builder()
                    .name("Flying")
                    .build();
            eggGroupDao.create(eggFlying);
            EggGroupEntity eggGrass = EggGroupEntity.builder()
                    .name("Grass")
                    .build();
            eggGroupDao.create(eggGrass);
            EggGroupEntity eggHuman = EggGroupEntity.builder()
                    .name("Human-like")
                    .build();
            eggGroupDao.create(eggHuman);
            EggGroupEntity eggMineral = EggGroupEntity.builder()
                    .name("Mineral")
                    .build();
            eggGroupDao.create(eggMineral);
            EggGroupEntity eggMonster = EggGroupEntity.builder()
                    .name("Monster")
                    .build();
            eggGroupDao.create(eggMonster);
            EggGroupEntity eggWater1 = EggGroupEntity.builder()
                    .name("Water1")
                    .build();
            eggGroupDao.create(eggWater1);
            EggGroupEntity eggWater2 = EggGroupEntity.builder()
                    .name("Water2")
                    .build();
            eggGroupDao.create(eggWater2);
            EggGroupEntity eggWater3 = EggGroupEntity.builder()
                    .name("Water3")
                    .build();
            eggGroupDao.create(eggWater3);
            EggGroupEntity eggDitto = EggGroupEntity.builder()
                    .name("Ditto")
                    .build();
            eggGroupDao.create(eggDitto);
            EggGroupEntity eggUndiscovered = EggGroupEntity.builder()
                    .name("Undiscovered")
                    .build();
            eggGroupDao.create(eggUndiscovered);

            // ========== POKEMONS + EVOLUTIONS ==========

            PokemonEntity ditto = PokemonEntity.builder()
                    .id(132L)
                    .nationalId("132")
                    .name("Ditto")
                    .type1(normal)
                    .type2(null)
                    .percentMale(null)
                    .percentFemale(null)
                    .eggGroups(Arrays.asList(eggDitto))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(ditto);

            EvolutionGroupEntity bulbasaurEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(bulbasaurEvolution);

            PokemonEntity bulbasaur = PokemonEntity.builder()
                    .nationalId("001")
                    .name("Bulbasaur")
                    .type1(grass)
                    .type2(poison)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(eggGrass, eggMonster))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(bulbasaur);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(bulbasaur)
                    .evolutionRank(1)
                    .evolutionGroup(bulbasaurEvolution)
                    .build());

            PokemonEntity ivysaur = PokemonEntity.builder()
                    .nationalId("002")
                    .name("Ivysaur")
                    .type1(grass)
                    .type2(poison)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(eggGrass, eggMonster))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(ivysaur);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(ivysaur)
                    .evolutionRank(2)
                    .evolutionGroup(bulbasaurEvolution)
                    .build());

            PokemonEntity venusaur = PokemonEntity.builder()
                    .nationalId("003")
                    .name("Venusaur")
                    .type1(grass)
                    .type2(poison)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(eggGrass, eggMonster))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(venusaur);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(venusaur)
                    .evolutionRank(3)
                    .evolutionGroup(bulbasaurEvolution)
                    .build());

//        -----

            EvolutionGroupEntity charmanderEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(charmanderEvolution);

            PokemonEntity charmander = PokemonEntity.builder()
                    .nationalId("004")
                    .name("Charmander")
                    .type1(fire)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(eggDragon, eggMonster))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(charmander);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(charmander)
                    .evolutionRank(1)
                    .evolutionGroup(charmanderEvolution)
                    .build());

            PokemonEntity charmeleon = PokemonEntity.builder()
                    .nationalId("005")
                    .name("Charmeleon")
                    .type1(fire)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(eggDragon, eggMonster))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(charmeleon);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(charmeleon)
                    .evolutionRank(2)
                    .evolutionGroup(charmanderEvolution)
                    .build());

            PokemonEntity charizard = PokemonEntity.builder()
                    .nationalId("006")
                    .name("Charizard")
                    .type1(fire)
                    .type2(flying)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(eggDragon, eggMonster))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(charizard);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(charizard)
                    .evolutionRank(3)
                    .evolutionGroup(charmanderEvolution)
                    .build());

//      -----

            EvolutionGroupEntity squirtleEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(squirtleEvolution);

            PokemonEntity squirtle = PokemonEntity.builder()
                    .nationalId("007")
                    .name("Squirtle")
                    .type1(water)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(eggMonster, eggWater1))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(squirtle);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(squirtle)
                    .evolutionRank(1)
                    .evolutionGroup(squirtleEvolution)
                    .build());

            PokemonEntity wartortle = PokemonEntity.builder()
                    .nationalId("008")
                    .name("Wartortle")
                    .type1(water)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(eggMonster, eggWater1))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(wartortle);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(wartortle)
                    .evolutionRank(2)
                    .evolutionGroup(squirtleEvolution)
                    .build());

            PokemonEntity blastoise = PokemonEntity.builder()
                    .nationalId("009")
                    .name("Blastoise")
                    .type1(water)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(eggMonster, eggWater1))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(blastoise);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(blastoise)
                    .evolutionRank(3)
                    .evolutionGroup(squirtleEvolution)
                    .build());

//        -----

            EvolutionGroupEntity caterpieEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(caterpieEvolution);

            PokemonEntity caterpie = PokemonEntity.builder()
                    .nationalId("010")
                    .name("Caterpie")
                    .type1(bug)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggBug))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(caterpie);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(caterpie)
                    .evolutionRank(1)
                    .evolutionGroup(caterpieEvolution)
                    .build());

            PokemonEntity metapod = PokemonEntity.builder()
                    .nationalId("011")
                    .name("Metapod")
                    .type1(bug)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggBug))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(metapod);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(metapod)
                    .evolutionRank(2)
                    .evolutionGroup(caterpieEvolution)
                    .build());

            PokemonEntity butterfree = PokemonEntity.builder()
                    .nationalId("012")
                    .name("Butterfree")
                    .type1(bug)
                    .type2(flying)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggBug))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(butterfree);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(butterfree)
                    .evolutionRank(3)
                    .evolutionGroup(caterpieEvolution)
                    .build());

//        -----

            EvolutionGroupEntity weedleEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(weedleEvolution);

            PokemonEntity weedle = PokemonEntity.builder()
                    .nationalId("013")
                    .name("Weedle")
                    .type1(bug)
                    .type2(poison)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggBug))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(weedle);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(weedle)
                    .evolutionRank(1)
                    .evolutionGroup(weedleEvolution)
                    .build());

            PokemonEntity kakuna = PokemonEntity.builder()
                    .nationalId("014")
                    .name("Kakuna")
                    .type1(bug)
                    .type2(poison)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggBug))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(kakuna);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(kakuna)
                    .evolutionRank(2)
                    .evolutionGroup(weedleEvolution)
                    .build());

            PokemonEntity beedrill = PokemonEntity.builder()
                    .nationalId("015")
                    .name("Beedrill")
                    .type1(bug)
                    .type2(poison)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggBug))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(beedrill);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(beedrill)
                    .evolutionRank(3)
                    .evolutionGroup(weedleEvolution)
                    .build());

//        -----

            EvolutionGroupEntity pidgeyEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(pidgeyEvolution);

            PokemonEntity pidgey = PokemonEntity.builder()
                    .nationalId("016")
                    .name("Pidgey")
                    .type1(normal)
                    .type2(flying)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggFlying))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(pidgey);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(pidgey)
                    .evolutionRank(1)
                    .evolutionGroup(pidgeyEvolution)
                    .build());

            PokemonEntity pidgeotto = PokemonEntity.builder()
                    .nationalId("017")
                    .name("Pidgeotto")
                    .type1(normal)
                    .type2(flying)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggFlying))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(pidgeotto);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(pidgeotto)
                    .evolutionRank(2)
                    .evolutionGroup(pidgeyEvolution)
                    .build());

            PokemonEntity pidgeot = PokemonEntity.builder()
                    .nationalId("018")
                    .name("Pidgeot")
                    .type1(normal)
                    .type2(flying)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggFlying))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(pidgeot);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(pidgeot)
                    .evolutionRank(3)
                    .evolutionGroup(pidgeyEvolution)
                    .build());

//        -----

            EvolutionGroupEntity rattataEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(rattataEvolution);

            PokemonEntity rattata = PokemonEntity.builder()
                    .nationalId("019")
                    .name("Rattata")
                    .type1(normal)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggField))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(rattata);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(rattata)
                    .evolutionRank(1)
                    .evolutionGroup(rattataEvolution)
                    .build());

            PokemonEntity raticate = PokemonEntity.builder()
                    .nationalId("020")
                    .name("Raticate")
                    .type1(normal)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggField))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(raticate);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(raticate)
                    .evolutionRank(2)
                    .evolutionGroup(rattataEvolution)
                    .build());

//        -----

            EvolutionGroupEntity spearowEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(spearowEvolution);

            PokemonEntity spearow = PokemonEntity.builder()
                    .nationalId("021")
                    .name("Spearow")
                    .type1(normal)
                    .type2(flying)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggFlying))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(spearow);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(spearow)
                    .evolutionRank(1)
                    .evolutionGroup(spearowEvolution)
                    .build());

            PokemonEntity fearow = PokemonEntity.builder()
                    .nationalId("022")
                    .name("Fearow")
                    .type1(normal)
                    .type2(flying)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggFlying))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(fearow);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(fearow)
                    .evolutionRank(2)
                    .evolutionGroup(spearowEvolution)
                    .build());

//        -----

            EvolutionGroupEntity ekansEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(ekansEvolution);

            PokemonEntity ekans = PokemonEntity.builder()
                    .nationalId("023")
                    .name("Ekans")
                    .type1(poison)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggDragon, eggField))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(ekans);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(ekans)
                    .evolutionRank(1)
                    .evolutionGroup(ekansEvolution)
                    .build());

            PokemonEntity arbok = PokemonEntity.builder()
                    .nationalId("024")
                    .name("Arbok")
                    .type1(poison)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggDragon, eggField))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(arbok);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(arbok)
                    .evolutionRank(2)
                    .evolutionGroup(ekansEvolution)
                    .build());

//        -----

            EvolutionGroupEntity pikaEvolution = EvolutionGroupEntity.builder().build();
            evolutionGroupDao.create(pikaEvolution);

            PokemonEntity pika = PokemonEntity.builder()
                    .nationalId("025")
                    .name("Pikachu")
                    .type1(electric)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggFairy, eggField))
                    .eggCycles(11)
                    .build();
            pokemonDao.create(pika);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(pika)
                    .evolutionRank(2)
                    .evolutionGroup(pikaEvolution)
                    .build());

            PokemonEntity raichu = PokemonEntity.builder()
                    .nationalId("026")
                    .name("Raichu")
                    .type1(electric)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(eggFairy, eggField))
                    .eggCycles(11)
                    .build();
            pokemonDao.create(raichu);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(raichu)
                    .evolutionRank(3)
                    .evolutionGroup(pikaEvolution)
                    .build());

            return "OK";
        } else {
            return "DB already exists";
        }
    }

}
