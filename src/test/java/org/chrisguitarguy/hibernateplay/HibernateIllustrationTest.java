package org.chrisguitarguy.hibernateplay;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
    public void testSaveGetRemoveAuthors() throws Exception {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Author("A", "Person"));
        em.persist(new Author("Another", "Person"));
        em.getTransaction().commit();
        em.close();

        Long authorId = null;
        em = emFactory.createEntityManager();
        /* This causes some unchecked warnings
        Query q = em.createQuery("from Author", Author.class);
        List<Author> result = q.getResultList();

        Source: http://stackoverflow.com/a/8790916/1031898
        You can get around those like this: */
        TypedQuery<Author> q = em.createQuery("FROM Author", Author.class);
        List<Author> result = q.getResultList();
        for (Author a : result) {
            Assert.assertNotNull(a.getFirstName());
            authorId = a.getId();
        }
        em.close();

        if (null != authorId) {
            em = emFactory.createEntityManager();
            Author a = em.find(Author.class, authorId);
            em.getTransaction().begin();
            if (null != a) {
                em.remove(a);
            }
            em.getTransaction().commit();
            em.close();
        }
    }
}
