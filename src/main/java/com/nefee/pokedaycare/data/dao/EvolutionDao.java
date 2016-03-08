package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.EvolutionEntity;
import org.springframework.stereotype.Repository;

@Repository ("evolutionDao")
public class EvolutionDao extends PokeDayCareDao<EvolutionEntity> {

    public EvolutionDao() {
        super(EvolutionEntity.class);
    }
}
