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

    private static final String QUERY_FINDBYNATIONALID = "PokemonEntity.findByNationalId";
    private static final String QUERY_FINDBYNAME = "PokemonEntity.findByName";

    public PokemonDao() {
        super(PokemonEntity.class);
    }

    public Optional<PokemonEntity> findByNationalId(Integer id) {

        try {

            PokemonEntity queryResult = (PokemonEntity) getSessionFactory().getCurrentSession().getNamedQuery(QUERY_FINDBYNATIONALID)
                    .setParameter("national_id", id).uniqueResult();

            if (queryResult != null) {
                Optional<PokemonEntity> optional = Optional.of(queryResult);
                LOGGER.debug("Found Pokemon with id {}", id);
                return optional;
            } else {
                LOGGER.debug("Couldn't find any Pokemon with id {}", id);
                return Optional.empty();
            }

        } catch (HibernateException he) {
            LOGGER.error("Hibernate is unable to find Pokemon with id {}", id, he);
            return Optional.empty();
        }

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

}
