package com.nefee.pokedaycare.data.dao;

import com.nefee.pokedaycare.data.entity.PokeDayCareEntity;
import com.nefee.pokedaycare.logic.exception.PokeDayCareException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PokeDayCareDao<T extends PokeDayCareEntity> {

    private static final Logger logger = LoggerFactory.getLogger(PokeDayCareDao.class);

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

        logger.info("Entity {} well created", entityClass.getName());
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
