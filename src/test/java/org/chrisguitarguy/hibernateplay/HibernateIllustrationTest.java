package org.chrisguitarguy.hibernateplay;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateIllustrationTest {
    private EntityManagerFactory emFactory;

    @Before
    public void setUp() throws Exception {
        emFactory = Persistence.createEntityManagerFactory("org.chrisguitarguy.hibernateplay");
    }

    @After
    public void tearDown() throws Exception {
        if (null != emFactory) {
            emFactory.close();
        }
    }

    @Test
    public void testStuff() throws Exception {
    }
}
