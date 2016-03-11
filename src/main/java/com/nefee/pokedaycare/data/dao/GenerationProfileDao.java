package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.breeding.EggGroup;
import com.nefee.pokedaycare.data.entity.generation.Generation;
import com.nefee.pokedaycare.data.entity.generation.GenerationProfileEntity;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository ("generationProfileDao")
public class GenerationProfileDao extends PokeDayCareDao<GenerationProfileEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerationProfileDao.class);

    private static final String QUERY_FINDBYGENNATID = "GenerationProfileEntity.findByGenerationAndNationalId";
    private static final String QUERY_FINDALLBYGEN = "GenerationProfileEntity.findAllByGeneration";
    private static final String QUERY_FINDALLBYEGGGROUP = "GenerationProfileEntity.findAllByEggGroup";

    public GenerationProfileDao() {
        super(GenerationProfileEntity.class);
    }

    public Optional<GenerationProfileEntity> findByNationalIdAndGeneration(Integer id, Generation gen) {

        try {

            GenerationProfileEntity queryResult = (GenerationProfileEntity) getSessionFactory().getCurrentSession()
                    .getNamedQuery(QUERY_FINDBYGENNATID)
                    .setParameter("national_id", id)
                    .setParameter("generation", gen)
                    .uniqueResult();

            if (queryResult != null) {
                Optional<GenerationProfileEntity> optional = Optional.of(queryResult);
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

    public List<GenerationProfileEntity> findAllByGeneration(Generation gen) {

        List<GenerationProfileEntity> pokemons = new ArrayList<>();

        try {

            pokemons = getSessionFactory().getCurrentSession()
                    .getNamedQuery(QUERY_FINDALLBYGEN)
                    .setParameter("generation", gen)
                    .list();

            if (pokemons.isEmpty()) {
                LOGGER.debug("Couldn't find any Pokemon for {}", gen);
            } else {
                LOGGER.debug("Found list of {} Pokemons in {}", pokemons.size(), gen);
            }

            return pokemons;

        } catch (HibernateException he) {
            LOGGER.error("Hibernate is unable to find any Pokemon", he);
            return pokemons;
        }

    }

    public List<GenerationProfileEntity> findAllPokemonsByEggGroupAndGeneration(EggGroup eggGroup, Generation gen) {

        List<GenerationProfileEntity> pokemons = getSessionFactory().getCurrentSession()
                .getNamedQuery(QUERY_FINDALLBYEGGGROUP)
                .setParameter("egg_group", eggGroup)
                .setParameter("generation", gen)
                .list();

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
