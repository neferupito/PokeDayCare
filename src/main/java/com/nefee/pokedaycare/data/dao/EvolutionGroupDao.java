package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.evolution.EvolutionEntity;
import com.nefee.pokedaycare.data.entity.evolution.EvolutionGroupEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("evolutionGroupDao")
public class EvolutionGroupDao extends PokeDayCareDao<EvolutionGroupEntity> {

    public EvolutionGroupDao() {
        super(EvolutionGroupEntity.class);
    }

}
