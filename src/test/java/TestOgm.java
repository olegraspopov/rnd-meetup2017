import data.ogm.Account;
import data.ogm.Address;
import data.ogm.Client;
import data.ogm.Document;
import data.ogm.breed_n_dog.Breed;
import data.ogm.breed_n_dog.Dog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class TestOgm {
    private EntityManager em;

    public static void main(String[] args) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ogm-jpa-tutorial");
        EntityManager em = emf.createEntityManager();
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
        Dog ourDina = em.find(Dog.class, dinaId);
        System.out.println("Dina:" + ourDina);
        em.close();
    }

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ogm-jpa-tutorial");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() throws Exception {
        em.close();
    }

    @Test
    public void testAccountPersistAndRead() throws Exception {
        Account account = new Account(50L, "123123123", "Счет 123123123");
        assertThat(account.getId(), is(nullValue(Long.class)));

        em.getTransaction().begin();
        assertThat(account.getId(), is(nullValue(Long.class)));

        em.persist(account);
        assertThat(account.getId(), is(notNullValue(Long.class)));

        Long id = account.getId();
        em.getTransaction().commit();
        assertThat(account.getId(), is(id));

        Account accountRead = em.find(Account.class, id);
        assertThat(accountRead, is(notNullValue(Account.class)));
        assertThat(accountRead.getId(), is(id));
    }

    @Test
    public void testAddressPersistAndRead() throws Exception {
        Address address = new Address("ул. Ленина", 344000);
        assertThat(address.getId(), is(nullValue(Long.class)));

        em.getTransaction().begin();
        assertThat(address.getId(), is(nullValue(Long.class)));

        em.persist(address);
        assertThat(address.getId(), is(notNullValue(Long.class)));

        Long id = address.getId();
        em.getTransaction().commit();
        assertThat(address.getId(), is(id));

        Address addressRead = em.find(Address.class, id);
        assertThat(addressRead, is(notNullValue(Address.class)));
        assertThat(addressRead.getId(), is(id));
    }

    @Test
    public void testClientPersistAndRead() throws Exception {
        Client client = new Client("Пупкин", "09999991110");
        assertThat(client.getId(), is(nullValue(Long.class)));

        em.getTransaction().begin();
        assertThat(client.getId(), is(nullValue(Long.class)));

        em.persist(client);
        assertThat(client.getId(), is(notNullValue(Long.class)));

        Long id = client.getId();
        em.getTransaction().commit();
        assertThat(client.getId(), is(id));

        Client clientRead = em.find(Client.class, id);
        assertThat(clientRead, is(notNullValue(Client.class)));
        assertThat(clientRead.getId(), is(id));
    }

    @Test
    public void testDocumentPersistAndRead() throws Exception {
        Document document = new Document(123L, 456L, 10000L, new Date(), "aim");
        assertThat(document.getId(), is(nullValue(Long.class)));

        em.getTransaction().begin();
        assertThat(document.getId(), is(nullValue(Long.class)));

        em.persist(document);
        assertThat(document.getId(), is(notNullValue(Long.class)));

        Long id = document.getId();
        em.getTransaction().commit();
        assertThat(document.getId(), is(id));

        Document documentRead = em.find(Document.class, id);
        assertThat(documentRead, is(notNullValue(Document.class)));
        assertThat(documentRead.getId(), is(id));
    }
}
