package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.evolution.EvolveConditionEntity;
import org.springframework.stereotype.Repository;

@Repository("evolveConditionDao")
public class EvolveConditionDao extends PokeDayCareDao<EvolveConditionEntity> {

    public EvolveConditionDao() {
        super(EvolveConditionEntity.class);
    }

}
