package org.courseregistration.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A class to perform CRUD operations on the entities involved in the system
 * To be able to perform operations mentioned in the class Entities should implement serializable.
 */
abstract class GenericDAO<T extends Serializable> {
    private final Class<T> clazz;
    private final EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(GenericDAO.class);

    protected GenericDAO(Class<T> clazz, EntityManager entityManager) {
        this.clazz = clazz;
        this.entityManager = entityManager;
    }

    /**
     * Finds an entity based on the given id
     *
     * @param id of an entity to be searched
     */
    public T findById(Long id) {

        T toReturn = null;
        try {
            toReturn = entityManager.find(clazz, id);
        } catch (Exception exception) {
            logger.error("Error occurred finding an entity of type:{}", clazz.getName(), exception);
            throw exception;
        }

        return (toReturn);
    }

    /**
     * Returns a list of entities of any given type.
     * As different entities will have different display orders so
     * the method is abstract and its up to entities to pull all the data
     * in the order they want
     */
    public abstract List<T> findAll();

    /**
     * Update an Entity objects with the given values
     *
     * @param updatedObject an entity object to be updated in db
     * @return returns an updated object
     */
    public T update(T updatedObject) {
        logger.debug("Updating object of type: {}", clazz.getName());

        T mergedInstance;
        try {
            entityManager.getTransaction().begin();
            mergedInstance = entityManager.merge(updatedObject);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            logger.error("Error updating object of type: {}", clazz.getName(), exception);
            throw exception;
        }

        logger.debug("Done updating object of type: {}", clazz.getName());

        return mergedInstance;
    }

    /**
     * Save an Entity objects with the given values
     *
     * @param newObject an entity object to be stored in db
     */
    public void save(T newObject) {
        logger.debug("Saving new object of type: {}", clazz.getName());

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newObject);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            logger.error("Error saving object of type: {}", clazz.getName(), exception);
            throw exception;
        }
        logger.debug("Done saving object of type: {}", clazz.getName());
    }

    /**
     * Bulk save operation on Entity objects with the given values
     *
     * @param entities entity objects object to be stored in db
     */
    public void save(Collection<T> entities) {
        logger.debug("Saving new object of type: {}", clazz.getName());

        try {
            entityManager.getTransaction().begin();
            int counter = 1;
            for (T entity : entities) {
                entityManager.persist(entity);
                if ((counter % 10000) == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            logger.error("Error saving object of type: {}", clazz.getName(), exception);
            throw exception;
        }
        logger.debug("Done saving object of type: {}", clazz.getName());
    }

    /**
     * Delete  Entity objects with the given ids
     *
     * @param ids ids of the TableReference objects to be deleted
     */
    public void delete(Collection<Long> ids) {
        checkNotNull(ids, "ids should not be null");
        checkArgument(!ids.isEmpty(), "ids should not be empty");
        entityManager.getTransaction().begin();
        int count = entityManager.createQuery("delete from " + clazz.getName() + " where id in (:ids)")
            .setParameter("ids", ids).executeUpdate();
        entityManager.getTransaction().commit();
        logger.debug("Deleted {} Entity objects of type {} for given ids of size {}", clazz.getName(), count, ids.size());

    }

    /**
     * Delete given entity object
     *
     * @param toDelete entity to delete
     */
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

    /**
     * Delete given entity object by its id
     *
     * @param id entity to delete
     */
    public void delete(Long id) {
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
