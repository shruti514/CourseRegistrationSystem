package org.courseregistration.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.UUID;

class GenericDAO<T extends Serializable> {
    private final Class<T> clazz;
    private final EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(GenericDAO.class);

    protected GenericDAO(Class<T> clazz, EntityManager entityManager) {
        this.clazz = clazz;
        this.entityManager = entityManager;
    }

    public T findById(UUID id) {

        T toReturn = null;
        try {
            toReturn = entityManager.find(clazz, id);
        } catch (Exception exception) {
            logger.error("Error occurred finding an entity of type:{}", clazz.getName(), exception);
            throw exception;
        }

        return (toReturn);
    }

    public T update(T updatedObject) {
        logger.debug("Updating object of type: {}", clazz.getName());

        T mergedInstance;
        try {
            entityManager.getTransaction().begin();
            mergedInstance = entityManager.merge(updatedObject);
           // entityManager.flush();
            //entityManager.detach(mergedInstance);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            logger.error("Error updating object of type: {}", clazz.getName(), exception);
            throw exception;
        }

        logger.debug("Done updating object of type: {}", clazz.getName());

        return mergedInstance;
    }

    public void save(T newObject) {
        logger.debug("Saving new object of type: {}", clazz.getName());

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newObject);
           // entityManager.flush();
           // entityManager.detach(newObject);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            logger.error("Error saving object of type: {}", clazz.getName(), exception);
            throw exception;
        }
        logger.debug("Done saving object of type: {}", clazz.getName());
    }

    public void delete(T toDelete) {
        logger.debug("Deleting a object of type: {}", clazz.getName());

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(toDelete);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            logger.error("Error deleting object of type: {}", clazz.getName(), exception);
            throw exception;
        }
        logger.debug("Done deleting object of type: {}", clazz.getName());
    }

    public void delete(UUID id) {
        T toDelete = entityManager.find(clazz, id);
        if (toDelete != null) {
            logger.debug("Deleting a object of type: {} with id: {}", clazz.getName(), id);
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(toDelete);
                entityManager.getTransaction().commit();
            } catch (Exception exception) {
                logger.error("Error deleting object of type: {} with id: {}", clazz.getName(), id, exception);
                throw exception;
            }
            logger.debug("Done deleting a object of type: {} with id: {}", clazz.getName(), id);
        }

    }


}
