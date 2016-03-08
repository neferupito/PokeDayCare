package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.PokemonEntity;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository ("pokemonDao")
public class PokemonDao extends PokeDayCareDao<PokemonEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonDao.class);

    private static final String QUERY_FINDBYNAME = "PokemonEntity.findByName";

    public PokemonDao() {
        super(PokemonEntity.class);
    }

    public Optional<PokemonEntity> findByName(String name) {

        try {

            Optional<PokemonEntity> optional = Optional.of((PokemonEntity) getSessionFactory().getCurrentSession().getNamedQuery(QUERY_FINDBYNAME)
                    .setParameter("pokemon_name", name).uniqueResult());

            LOGGER.debug("Found Pokemon {}", optional.get().getName());

            return optional;

        } catch (HibernateException he) {
            LOGGER.error("Hibernate is unable to find Pokemon named {}", name, he);
            return Optional.empty();
        }

    }
}
