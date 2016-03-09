package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.EvolutionGroupEntity;
import com.nefee.pokedaycare.data.entity.PokemonEntity;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository ("evolutionGroupDao")
public class EvolutionGroupDao extends PokeDayCareDao<EvolutionGroupEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvolutionGroupDao.class);

    private static final String QUERY_FINDPOKEMONBYRANK = "EvolutionGroupEntity.findPokemonByRank";

    public EvolutionGroupDao() {
        super(EvolutionGroupEntity.class);
    }

    public Optional<PokemonEntity> getPokemonByRank(EvolutionGroupEntity evolutionGroup, Integer rank) {

        try {

            PokemonEntity queryResult = (PokemonEntity) getSessionFactory().getCurrentSession()
                    .getNamedQuery(QUERY_FINDPOKEMONBYRANK)
                    .setParameter("evolution_group", evolutionGroup)
                    .setParameter("rank", rank)
                    .uniqueResult();

            if (queryResult != null) {
                Optional<PokemonEntity> optional = Optional.of(queryResult);
                LOGGER.debug("Found Pokemon named {} at rank {}", optional.get().getName(), rank);
                return optional;
            } else {
                LOGGER.debug("Couldn't find any Pokemon at rank {} for this evolution group", rank);
                return Optional.empty();
            }

        } catch (HibernateException he) {
            LOGGER.error("Hibernate is unable to find any Pokemon at rank {} for this evolution group", rank, he);
            return Optional.empty();
        }

    }

}
