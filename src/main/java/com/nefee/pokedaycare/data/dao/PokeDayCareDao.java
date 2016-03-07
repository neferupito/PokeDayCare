package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.PokeDayCareEntity;
import com.nefee.pokedaycare.logic.exception.PokeDayCareException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PokeDayCareDao<T extends PokeDayCareEntity> {

    @Autowired
    @Getter
    @Setter
    private SessionFactory sessionFactory;

    @Getter
    private final Class<T> entityClass;

    public PokeDayCareDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    public void update(T entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(T entity) throws PokeDayCareException {
        try {
            sessionFactory.getCurrentSession().delete(entity);
        } catch (HibernateException he) {
            throw new PokeDayCareException("Could't delete this entity: " + he.getMessage());
        }
    }

}
