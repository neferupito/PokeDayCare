package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.PokemonEntity;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("pokemonDao")
public class PokemonDao extends PokeDayCareDao<PokemonEntity> {

    private static final String QUERY_FINDBYNAME = "PokemonEntity.findByName";

    public PokemonDao() {
        super(PokemonEntity.class);
    }

    public Optional<PokemonEntity> findByName(String name) {

        Optional<PokemonEntity> optional = Optional.empty();

        if (name.equals("Pikachu")) {
            optional = optional.of(PokemonEntity.builder()
                    .name("Pikachu")
                    .birth(DateTime.now())
                    .build());
        }
//        try {
//            optional = Optional.of((PokemonEntity) getSessionFactory().getCurrentSession().getNamedQuery(QUERY_FINDBYNAME).uniqueResult());
//        } catch (HibernateException he) {
//            he.printStackTrace();
//        }

        return optional;
    }
}
