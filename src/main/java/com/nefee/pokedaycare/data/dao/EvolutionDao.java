package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.evolution.EvolutionEntity;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionGroupEntity;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("evolutionDao")
public class EvolutionDao extends PokeDayCareDao<EvolutionEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EvolutionDao.class);

    private static final String QUERY_FINDEVOLUTIONBYRANK = "EvolutionEntity.findEvolutionByRank";

    public EvolutionDao() {
        super(EvolutionEntity.class);
    }

    public Optional<EvolutionEntity> getEvolutionByRank(EvolutionGroupEntity evolutionGroup, Integer rank) {

        try {

            EvolutionEntity queryResult = (EvolutionEntity) getSessionFactory().getCurrentSession()
                    .getNamedQuery(QUERY_FINDEVOLUTIONBYRANK)
                    .setParameter("evolution_group", evolutionGroup)
                    .setParameter("rank", rank)
                    .uniqueResult();

            if (queryResult != null) {
                Optional<EvolutionEntity> optional = Optional.of(queryResult);
                LOGGER.debug("Found Evolution between {} and {} at rank {}", optional.get().getPreviousPokemon().getName(), optional.get().getNextPokemon().getName(), rank);
                return optional;
            } else {
                LOGGER.debug("Couldn't find any Evolution at rank {} for this evolution group", rank);
                return Optional.empty();
            }

        } catch (HibernateException he) {
            LOGGER.error("Hibernate is unable to find any Evolution at rank {} for this evolution group", rank, he);
            return Optional.empty();
        }

    }

}
