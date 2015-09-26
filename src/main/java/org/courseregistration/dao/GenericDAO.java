package org.courseregistration.dao;


import org.courseregistration.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.UUID;

class GenericDAO<T extends Serializable>  {
    private final Class<T> clazz;
    private final EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(GenericDAO.class);

    protected GenericDAO(Class<T> clazz,EntityManager entityManager){
        this.clazz = clazz;
        this.entityManager = entityManager;
    }

    public T findById(UUID id) {

        T toReturn = null;
        try {
            toReturn = entityManager.find(clazz,id);
        } catch (Exception e) {
            logger.error("Error occurred finding an entity of type:{}",clazz.getName(),e);
            throw e;
        }

        return(toReturn);
    }

    public T update(T updatedObject) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        logger.debug("Updating object of type: {}",clazz.getName());
        T mergedInstance;
        try {
            mergedInstance= entityManager.merge(updatedObject);
            entityManager.flush();
            entityManager.detach(mergedInstance);
            transaction.commit();
        }catch(Exception exception){
            logger.error("Error updating object of type: {}",clazz.getName(),exception);
            throw exception;
        }
        logger.debug("Done updating object of type: {}",clazz.getName());
        return mergedInstance;
    }

    public void save(T newObject) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        logger.debug("Saving new object of type: {}",clazz.getName());

        try {
            entityManager.persist(newObject);
            entityManager.flush();
            entityManager.detach(newObject);
            transaction.commit();
        }catch(Exception exception){
            logger.error("Error saving object of type: {}",clazz.getName(),exception);
            throw exception;
        }
        logger.debug("Done saving object of type: {}",clazz.getName());
    }

    public void delete(T toDelete) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        logger.debug("Deleting a object of type: {}",clazz.getName());

        try {
            //entityManager.merge(toDelete);
            entityManager.remove(toDelete);
           // entityManager.flush();
            transaction.commit();
        }catch(Exception exception){
            logger.error("Error deleting object of type: {}",clazz.getName(),exception);
            throw exception;
        }
        logger.debug("Done deleting object of type: {}",clazz.getName());
    }

    public void delete(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        logger.debug("Deleting a object of type: {} with id: {}",clazz.getName(),id.toString());

        try {
            entityManager.createQuery("delete from "+ clazz.getName()+" where id=:id")
                    .setParameter("id",id)
                    .executeUpdate();
            transaction.commit();
        }catch(Exception exception){
            logger.error("Error deleting object of type: {}",clazz.getName(),exception);
            throw exception;
        }
        logger.debug("Done deleting object of type: {}",clazz.getName());
    }


}
