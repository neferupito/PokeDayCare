package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.ObjectEntity;
import org.springframework.stereotype.Repository;

@Repository("objectDao")
public class ObjectDao extends PokeDayCareDao<ObjectEntity> {

    public ObjectDao() {
        super(ObjectEntity.class);
    }

}
