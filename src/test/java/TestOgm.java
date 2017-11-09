import data.ogm.Address;
import data.ogm.breed_n_dog.Breed;
import data.ogm.breed_n_dog.Dog;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.transaction.jta.platform.spi.JtaPlatform;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class TestOgm {
    private EntityManager em;

    public static void main(String[] args) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ogm-jpa-tutorial");
        EntityManager em =  emf.createEntityManager();
        em.getTransaction().begin();
        Breed collie = new Breed();
        collie.setName("breed-collie");
        em.persist(collie);
        Dog dina = new Dog();
        dina.setName("dina");
        dina.setBreed(collie);
        //persist dina
        em.persist(dina);
        em.getTransaction().commit();

        //get ID dina
        Long dinaId = dina.getId();
        // query
        Dog ourDina =  em.find(Dog.class, dinaId);
        System.out.println("Dina:"+ ourDina);
        em.close();
    }

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ogm-jpa-tutorial");
        em =  emf.createEntityManager();
    }

    @Test
    public void testSimpleEntity() throws Exception {
        Address address = new Address("ул. Ленина", 344000);
        assertThat(address.getId(), is(nullValue(Long.class)));

        em.getTransaction().begin();
        assertThat(address.getId(), is(nullValue(Long.class)));

        em.persist(address);
        assertThat(address.getId(), is(notNullValue(Long.class)));

        em.getTransaction().commit();

    }
}
