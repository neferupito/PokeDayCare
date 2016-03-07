package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.TypeEntity;
import org.springframework.stereotype.Repository;

@Repository("typeDao")
public class TypeDao extends PokeDayCareDao<TypeEntity> {

    public TypeDao() {
        super(TypeEntity.class);
    }

}
