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

    private static final Logger LOGGER = LoggerFactory.getLogger(PokeDayCareDao.class);

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
        LOGGER.info("Entity {} well created", entityClass.getName());
    }

    public void update(T entity) {
        sessionFactory.getCurrentSession().merge(entity);
        LOGGER.info("Entity {} well updated", entityClass.getName());
    }

    public void delete(T entity) throws PokeDayCareException {
        try {

            sessionFactory.getCurrentSession().delete(entity);

            LOGGER.debug("Entity {} has been correctly deleted", entityClass.getName());

        } catch (HibernateException he) {
            LOGGER.error("Error while trying to delete {}", entityClass.getName(), he);
            throw new PokeDayCareException("Could't delete this entity: " + he.getMessage());
        }
    }

}
