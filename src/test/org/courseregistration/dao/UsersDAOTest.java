package org.courseregistration.dao;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.courseregistration.model.Course;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(BlockJUnit4ClassRunner.class)
public class UsersDAOTest extends BaseTest{


    // Checking the Isolation Level (READ COMMITTED)
    @Test
    public void check_isolation_level(){
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("insert into roles(role_id, name) values (3833, 'student')").executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("insert into roles(role_id, name) values (7777, 'professor')").executeUpdate();

    }

}
