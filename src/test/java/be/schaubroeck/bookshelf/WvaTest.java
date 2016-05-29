package be.schaubroeck.bookshelf;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by Evert on 28/05/2016.
 */
public class WvaTest extends BookshelfTestBase {

    @Test
    public void testSelectMaxId(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookshelf");
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Long> queryAuteur = cb.createQuery(Long.class);

        Root<Auteur> myRoot = queryAuteur.from(Auteur.class);
        queryAuteur.select(cb.max(myRoot.get(Auteur_.id)));

        Assert.assertEquals(3l, em.createQuery(queryAuteur).getSingleResult().longValue());

        em.close();
    }

}
