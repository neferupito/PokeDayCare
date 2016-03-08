package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.EggGroupEntity;
import org.springframework.stereotype.Repository;

@Repository ("eggGroupDao")
public class EggGroupDao extends PokeDayCareDao<EggGroupEntity> {

    public EggGroupDao() {
        super(EggGroupEntity.class);
    }

}
