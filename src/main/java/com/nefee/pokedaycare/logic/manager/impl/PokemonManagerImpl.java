package com.nefee.pokedaycare.logic.manager.impl;

import com.nefee.pokedaycare.data.dao.EggGroupDao;
import com.nefee.pokedaycare.data.dao.PokemonDao;
import com.nefee.pokedaycare.data.dao.TypeDao;
import com.nefee.pokedaycare.data.entity.EggGroupEntity;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import com.nefee.pokedaycare.data.entity.TypeEntity;
import com.nefee.pokedaycare.logic.exception.PokeDayCareException;
import com.nefee.pokedaycare.logic.manager.PokemonManager;
import com.nefee.pokedaycare.logic.model.Pokemon;
import com.nefee.pokedaycare.logic.transformer.PokemonTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service("pokemonManager")
@Transactional(readOnly = true)
public class PokemonManagerImpl implements PokemonManager {

    @Autowired
    private PokemonDao pokemonDao;
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private EggGroupDao eggGroupDao;

    public Pokemon findByName(String name) throws PokeDayCareException {

        Optional<PokemonEntity> entity = pokemonDao.findByName(name);

        if (!entity.isPresent()) {
            throw new PokeDayCareException("Couldn't find the pokemon named " + name);
        }

        return PokemonTransformer.entityToPokemon(entity.get());

    }

    @Override
    @Transactional
    public String createDB() {
        typeDao.create(TypeEntity.builder()
                .name("Normal")
                .build());
        TypeEntity fire = TypeEntity.builder()
                .name("Fire")
                .build();
        typeDao.create(fire);
        TypeEntity water = TypeEntity.builder()
                .name("Water")
                .build();
        typeDao.create(water);
        typeDao.create(TypeEntity.builder()
                .name("Electric")
                .build());
        TypeEntity grass = TypeEntity.builder()
                .name("Grass")
                .build();
        typeDao.create(grass);
        typeDao.create(TypeEntity.builder()
                .name("Ice")
                .build());
        typeDao.create(TypeEntity.builder()
                .name("Fighting")
                .build());
        TypeEntity poison = TypeEntity.builder()
                .name("Poison")
                .build();
        typeDao.create(poison);
        typeDao.create(TypeEntity.builder()
                .name("Ground")
                .build());
        TypeEntity flying = TypeEntity.builder()
                .name("Flying")
                .build();
        typeDao.create(flying);
        typeDao.create(TypeEntity.builder()
                .name("Psychic")
                .build());
        typeDao.create(TypeEntity.builder()
                .name("Bug")
                .build());
        typeDao.create(TypeEntity.builder()
                .name("Rock")
                .build());
        typeDao.create(TypeEntity.builder()
                .name("Ghost")
                .build());
        typeDao.create(TypeEntity.builder()
                .name("Dragon")
                .build());
        typeDao.create(TypeEntity.builder()
                .name("Dark")
                .build());
        typeDao.create(TypeEntity.builder()
                .name("Steel")
                .build());
        typeDao.create(TypeEntity.builder()
                .name("Fairy")
                .build());

        eggGroupDao.create(EggGroupEntity.builder()
                .name("Amorphous")
                .build());
        eggGroupDao.create(EggGroupEntity.builder()
                .name("Bug")
                .build());
        EggGroupEntity eggDragon = EggGroupEntity.builder()
                .name("Dragon")
                .build();
        eggGroupDao.create(eggDragon);
        eggGroupDao.create(EggGroupEntity.builder()
                .name("Fairy")
                .build());
        eggGroupDao.create(EggGroupEntity.builder()
                .name("Field")
                .build());
        eggGroupDao.create(EggGroupEntity.builder()
                .name("Flying")
                .build());
        EggGroupEntity eggGrass = EggGroupEntity.builder()
                .name("Grass")
                .build();
        eggGroupDao.create(eggGrass);
        eggGroupDao.create(EggGroupEntity.builder()
                .name("Human-like")
                .build());
        eggGroupDao.create(EggGroupEntity.builder()
                .name("Mineral")
                .build());
        EggGroupEntity eggMonster = EggGroupEntity.builder()
                .name("Monster")
                .build();
        eggGroupDao.create(eggMonster);
        EggGroupEntity eggWater1 = EggGroupEntity.builder()
                .name("Water1")
                .build();
        eggGroupDao.create(eggWater1);
        eggGroupDao.create(EggGroupEntity.builder()
                .name("Water2")
                .build());
        eggGroupDao.create(EggGroupEntity.builder()
                .name("Water3")
                .build());
        eggGroupDao.create(EggGroupEntity.builder()
                .name("Ditto")
                .build());
        eggGroupDao.create(EggGroupEntity.builder()
                .name("Undiscovered")
                .build());

        pokemonDao.create(PokemonEntity.builder()
                .nationalId("001")
                .name("Bulbasaur")
                .type1(grass)
                .type2(poison)
                .eggGroups(Arrays.asList(eggGrass, eggMonster))
                .eggCycles(21)
                .build());
        pokemonDao.create(PokemonEntity.builder()
                .nationalId("002")
                .name("Ivysaur")
                .type1(grass)
                .type2(poison)
                .eggGroups(Arrays.asList(eggGrass, eggMonster))
                .eggCycles(21)
                .build());
        pokemonDao.create(PokemonEntity.builder()
                .nationalId("003")
                .name("Venusaur")
                .type1(grass)
                .type2(poison)
                .eggGroups(Arrays.asList(eggGrass, eggMonster))
                .eggCycles(21)
                .build());
        pokemonDao.create(PokemonEntity.builder()
                .nationalId("004")
                .name("Charmander")
                .type1(fire)
                .type2(null)
                .eggGroups(Arrays.asList(eggDragon, eggMonster))
                .eggCycles(21)
                .build());
        pokemonDao.create(PokemonEntity.builder()
                .nationalId("005")
                .name("Charmeleon")
                .type1(fire)
                .type2(null)
                .eggGroups(Arrays.asList(eggDragon, eggMonster))
                .eggCycles(21)
                .build());
        pokemonDao.create(PokemonEntity.builder()
                .nationalId("006")
                .name("Charizard")
                .type1(fire)
                .type2(flying)
                .eggGroups(Arrays.asList(eggDragon, eggMonster))
                .eggCycles(21)
                .build());
        pokemonDao.create(PokemonEntity.builder()
                .nationalId("007")
                .name("Squirtle")
                .type1(water)
                .type2(null)
                .eggGroups(Arrays.asList(eggMonster, eggWater1))
                .eggCycles(21)
                .build());
        pokemonDao.create(PokemonEntity.builder()
                .nationalId("008")
                .name("Wartortle")
                .type1(water)
                .type2(null)
                .eggGroups(Arrays.asList(eggMonster, eggWater1))
                .eggCycles(21)
                .build());
        pokemonDao.create(PokemonEntity.builder()
                .nationalId("009")
                .name("Blastoise")
                .type1(water)
                .type2(null)
                .eggGroups(Arrays.asList(eggMonster, eggWater1))
                .eggCycles(21)
                .build());

        return "OK";
    }

}
