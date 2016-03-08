package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.EggGroupEntity;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("eggGroupDao")
public class EggGroupDao extends PokeDayCareDao<EggGroupEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EggGroupDao.class);

    private static final String QUERY_FINDBYNAME = "EggGroupEntity.findByName";
    private static final String QUERY_FINDALLPOKEMONS = "EggGroupEntity.findAllPokemons";

    public EggGroupDao() {
        super(EggGroupEntity.class);
    }

    public Optional<EggGroupEntity> findByName(String name) {

        try {

            EggGroupEntity queryResult = (EggGroupEntity) getSessionFactory().getCurrentSession().getNamedQuery(QUERY_FINDBYNAME)
                    .setParameter("egg_group_name", name).uniqueResult();

            if (queryResult != null) {
                Optional<EggGroupEntity> optional = Optional.of(queryResult);
                LOGGER.debug("Found EggGroup named {}", optional.get().getName());
                return optional;
            } else {
                LOGGER.debug("Couldn't find any EggGroup named {}", name);
                return Optional.empty();
            }

        } catch (HibernateException he) {
            LOGGER.error("Hibernate is unable to find EggGroup named {}", name, he);
            return Optional.empty();
        }

    }

    public List<PokemonEntity> findAllPokemonsByEggGroup(EggGroupEntity eggGroup) {

        List<PokemonEntity> pokemons = new ArrayList<PokemonEntity>();

        try {

            pokemons = getSessionFactory().getCurrentSession().getNamedQuery(QUERY_FINDALLPOKEMONS)
                    .setParameter("egg_group", eggGroup).list();

            if (pokemons.isEmpty()) {
                LOGGER.debug("Couldn't find any Pokemon for the eggGroup {}", eggGroup.getName());
            } else {
                LOGGER.debug("Found list of {} Pokemons for eggGroup {}", pokemons.size(), eggGroup.getName());
            }

            return pokemons;

        } catch (HibernateException he) {
            LOGGER.error("Hibernate is unable to find any Pokemon for the eggGroup {}", eggGroup.getName(), he);
            return pokemons;
        }

    }

}
