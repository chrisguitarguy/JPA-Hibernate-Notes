package org.chrisguitarguy.hibernateplay;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.Assert;
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
    public void testSaveGetAuthors() throws Exception {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Author("A", "Person"));
        em.persist(new Author("Another", "Person"));
        em.getTransaction().commit();
        em.close();

        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("from Author", Author.class);
        List<Author> result = q.getResultList();
        for (Author a : result) {
            Assert.assertNotNull(a.getFirstName());
        }
        em.getTransaction().commit();
        em.close()
    }
}
