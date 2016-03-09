package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.EvolutionDao;
import com.nefee.pokedaycare.data.dao.EvolutionGroupDao;
import com.nefee.pokedaycare.data.dao.PokemonDao;
import com.nefee.pokedaycare.data.entity.*;
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

    @Transactional
    public String createDB() {

        Optional<PokemonEntity> optional = pokemonDao.findByName("Bulbasaur");

        if (!optional.isPresent()) {

            // ========== POKEMONS + EVOLUTIONS ==========

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
                    .build();
            pokemonDao.create(bulbasaur);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(bulbasaur)
                    .evolutionRank(1)
                    .evolutionGroup(bulbasaurEvolution)
                    .build());

            PokemonEntity ivysaur = PokemonEntity.builder()
                    .nationalId(2)
                    .name("Ivysaur")
                    .type1(Type.GRASS)
                    .type2(Type.POISON)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.GRASS, EggGroup.MONSTER))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(ivysaur);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(ivysaur)
                    .evolutionRank(2)
                    .evolutionGroup(bulbasaurEvolution)
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
                    .nationalId(4)
                    .name("Charmander")
                    .type1(Type.FIRE)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.MONSTER))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(charmander);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(charmander)
                    .evolutionRank(1)
                    .evolutionGroup(charmanderEvolution)
                    .build());

            PokemonEntity charmeleon = PokemonEntity.builder()
                    .nationalId(5)
                    .name("Charmeleon")
                    .type1(Type.FIRE)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.MONSTER))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(charmeleon);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(charmeleon)
                    .evolutionRank(2)
                    .evolutionGroup(charmanderEvolution)
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
                    .nationalId(7)
                    .name("Squirtle")
                    .type1(Type.WATER)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.MONSTER, EggGroup.WATER1))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(squirtle);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(squirtle)
                    .evolutionRank(1)
                    .evolutionGroup(squirtleEvolution)
                    .build());

            PokemonEntity wartortle = PokemonEntity.builder()
                    .nationalId(8)
                    .name("Wartortle")
                    .type1(Type.WATER)
                    .type2(null)
                    .percentMale(87.5)
                    .percentFemale(12.5)
                    .eggGroups(Arrays.asList(EggGroup.MONSTER, EggGroup.WATER1))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(wartortle);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(wartortle)
                    .evolutionRank(2)
                    .evolutionGroup(squirtleEvolution)
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
                    .nationalId(10)
                    .name("Caterpie")
                    .type1(Type.BUG)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(caterpie);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(caterpie)
                    .evolutionRank(1)
                    .evolutionGroup(caterpieEvolution)
                    .build());

            PokemonEntity metapod = PokemonEntity.builder()
                    .nationalId(11)
                    .name("Metapod")
                    .type1(Type.BUG)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(metapod);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(metapod)
                    .evolutionRank(2)
                    .evolutionGroup(caterpieEvolution)
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
                    .nationalId(13)
                    .name("Weedle")
                    .type1(Type.BUG)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(weedle);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(weedle)
                    .evolutionRank(1)
                    .evolutionGroup(weedleEvolution)
                    .build());

            PokemonEntity kakuna = PokemonEntity.builder()
                    .nationalId(14)
                    .name("Kakuna")
                    .type1(Type.BUG)
                    .type2(Type.POISON)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.BUG))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(kakuna);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(kakuna)
                    .evolutionRank(2)
                    .evolutionGroup(weedleEvolution)
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
                    .nationalId(16)
                    .name("Pidgey")
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(pidgey);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(pidgey)
                    .evolutionRank(1)
                    .evolutionGroup(pidgeyEvolution)
                    .build());

            PokemonEntity pidgeotto = PokemonEntity.builder()
                    .nationalId(17)
                    .name("Pidgeotto")
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(pidgeotto);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(pidgeotto)
                    .evolutionRank(2)
                    .evolutionGroup(pidgeyEvolution)
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
                    .nationalId(19)
                    .name("Rattata")
                    .type1(Type.NORMAL)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(rattata);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(rattata)
                    .evolutionRank(1)
                    .evolutionGroup(rattataEvolution)
                    .build());

            PokemonEntity raticate = PokemonEntity.builder()
                    .nationalId(20)
                    .name("Raticate")
                    .type1(Type.NORMAL)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
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
                    .nationalId(21)
                    .name("Spearow")
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
                    .eggCycles(16)
                    .build();
            pokemonDao.create(spearow);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(spearow)
                    .evolutionRank(1)
                    .evolutionGroup(spearowEvolution)
                    .build());

            PokemonEntity fearow = PokemonEntity.builder()
                    .nationalId(22)
                    .name("Fearow")
                    .type1(Type.NORMAL)
                    .type2(Type.FLYING)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FLYING))
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
                    .nationalId(23)
                    .name("Ekans")
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.FIELD))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(ekans);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(ekans)
                    .evolutionRank(1)
                    .evolutionGroup(ekansEvolution)
                    .build());

            PokemonEntity arbok = PokemonEntity.builder()
                    .nationalId(24)
                    .name("Arbok")
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.DRAGON, EggGroup.FIELD))
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
                    .nationalId(25)
                    .name("Pikachu")
                    .type1(Type.ELECTRIC)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FAIRY, EggGroup.FIELD))
                    .eggCycles(11)
                    .build();
            pokemonDao.create(pika);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(pika)
                    .evolutionRank(2)
                    .evolutionGroup(pikaEvolution)
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
                    .build();
            pokemonDao.create(raichu);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(raichu)
                    .evolutionRank(3)
                    .evolutionGroup(pikaEvolution)
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
                    .build();
            pokemonDao.create(sandshrew);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(sandshrew)
                    .evolutionRank(1)
                    .evolutionGroup(sabeEvolution)
                    .build());

            PokemonEntity sandslash = PokemonEntity.builder()
                    .nationalId(28)
                    .name("Sandslash")
                    .type1(Type.GROUND)
                    .type2(null)
                    .percentMale(50d)
                    .percentFemale(50d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(sandslash);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(sandslash)
                    .evolutionRank(2)
                    .evolutionGroup(sabeEvolution)
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
                    .build();
            pokemonDao.create(nidoF);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(nidoF)
                    .evolutionRank(1)
                    .evolutionGroup(nidoFEvolution)
                    .build());

            PokemonEntity nidorina = PokemonEntity.builder()
                    .nationalId(30)
                    .name("Nidorina")
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(0d)
                    .percentFemale(100d)
                    .eggGroups(Arrays.asList(EggGroup.UNDISCOVERED))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(nidorina);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(nidorina)
                    .evolutionRank(2)
                    .evolutionGroup(nidoFEvolution)
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
                    .build();
            pokemonDao.create(nidoqueen);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(nidoqueen)
                    .evolutionRank(3)
                    .evolutionGroup(nidoFEvolution)
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
                    .build();
            pokemonDao.create(nidoM);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(nidoM)
                    .evolutionRank(1)
                    .evolutionGroup(nidoMEvolution)
                    .build());

            PokemonEntity nidorino = PokemonEntity.builder()
                    .nationalId(33)
                    .name("Nidorino")
                    .type1(Type.POISON)
                    .type2(null)
                    .percentMale(100d)
                    .percentFemale(0d)
                    .eggGroups(Arrays.asList(EggGroup.FIELD, EggGroup.MONSTER))
                    .eggCycles(21)
                    .build();
            pokemonDao.create(nidorino);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(nidorino)
                    .evolutionRank(2)
                    .evolutionGroup(nidoMEvolution)
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
                    .build();
            pokemonDao.create(nidoking);
            evolutionDao.create(EvolutionEntity.builder()
                    .pokemon(nidoking)
                    .evolutionRank(3)
                    .evolutionGroup(nidoMEvolution)
                    .build());

            return "OK";
        } else {
            return "DB already exists";
        }
    }

}
