package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.EggGroup;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository ("pokemonDao")
public class PokemonDao extends PokeDayCareDao<PokemonEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonDao.class);

    private static final String QUERY_FINDBYNAME = "PokemonEntity.findByName";
    private static final String QUERY_FINDALL = "PokemonEntity.findAll";
    private static final String QUERY_FINDALLBYEGGGROUP = "PokemonEntity.findAllPokemonsByEggGroup";

    public PokemonDao() {
        super(PokemonEntity.class);
    }

    public Optional<PokemonEntity> findByName(String name) {

        try {

            PokemonEntity queryResult = (PokemonEntity) getSessionFactory().getCurrentSession().getNamedQuery(QUERY_FINDBYNAME)
                    .setParameter("pokemon_name", name).uniqueResult();

            if (queryResult != null) {
                Optional<PokemonEntity> optional = Optional.of(queryResult);
                LOGGER.debug("Found Pokemon named {}", optional.get().getName());
                return optional;
            } else {
                LOGGER.debug("Couldn't find any Pokemon named {}", name);
                return Optional.empty();
            }

        } catch (HibernateException he) {
            LOGGER.error("Hibernate is unable to find Pokemon named {}", name, he);
            return Optional.empty();
        }

    }

    public List<PokemonEntity> findAll() {

        List<PokemonEntity> pokemons = new ArrayList<PokemonEntity>();

        try {

            pokemons = getSessionFactory().getCurrentSession().getNamedQuery(QUERY_FINDALL).list();

            if (pokemons.isEmpty()) {
                LOGGER.debug("Couldn't find any Pokemon");
            } else {
                LOGGER.debug("Found list of {} Pokemons", pokemons.size());
            }

            return pokemons;

        } catch (HibernateException he) {
            LOGGER.error("Hibernate is unable to find any Pokemon", he);
            return pokemons;
        }

    }

    public List<PokemonEntity> findAllPokemonsByEggGroup(EggGroup eggGroup) {

        List<PokemonEntity> pokemons = getSessionFactory().getCurrentSession().getNamedQuery(QUERY_FINDALLBYEGGGROUP)
                .setParameter("egg_group", eggGroup).list();

        try {

            if (pokemons.isEmpty()) {
                LOGGER.debug("Couldn't find any Pokemon for the eggGroup {}", eggGroup);
            } else {
                LOGGER.debug("Found list of {} Pokemons for eggGroup {}", pokemons.size(), eggGroup);
            }

            return pokemons;

        } catch (HibernateException he) {
            LOGGER.error("Hibernate is unable to find any Pokemon for the eggGroup {}", eggGroup, he);
            return pokemons;
        }

    }


}
