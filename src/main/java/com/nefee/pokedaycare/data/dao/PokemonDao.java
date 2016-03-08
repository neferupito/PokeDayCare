package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.PokemonEntity;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("pokemonDao")
public class PokemonDao extends PokeDayCareDao<PokemonEntity> {

    private static final Logger logger = LoggerFactory.getLogger(PokemonDao.class);

    private static final String QUERY_FINDBYNAME = "PokemonEntity.findByName";

    public PokemonDao() {
        super(PokemonEntity.class);
    }

    public Optional<PokemonEntity> findByName(String name) {

        Optional<PokemonEntity> optional = Optional.empty();

        try {
            optional = Optional.of((PokemonEntity) getSessionFactory().getCurrentSession().getNamedQuery(QUERY_FINDBYNAME)
                    .setParameter("pokemon_name", name).uniqueResult());
            logger.info("Found Pokemon {}", optional.get().getName());
        } catch (HibernateException he) {
            he.printStackTrace();
            logger.error("HibernateError", he);
        }

        return optional;
    }
}
