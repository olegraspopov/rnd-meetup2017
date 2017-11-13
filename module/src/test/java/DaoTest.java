import com.sbt.rnd.meetup2017.api.ClientApi;
import com.sbt.rnd.meetup2017.dao.IDao;
import com.sbt.rnd.meetup2017.dao.IExecutor;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "classpath:spring-beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoTest {
    @Autowired
    private IDao dao;

    @Autowired
    private EntityManager em;

    @Test
    public void testExecute() throws Exception {

        String name = "Пупкин";
        String inn = "09999991110";
        Client client = new Client(name, inn);
        assertTrue(dao.execute(new IExecutor<Boolean>() {
            @Override
            public Boolean execute(EntityManager em) throws Exception {
                em.persist(client);
                return true;
            }
        }));

    }

    @Test
    public void testSave() throws Exception {

        String name = "Пупкин";
        String inn = "09999991110";
        Client client = new Client(name, inn);
        assertTrue(dao.save(client));

    }

    @Test
    public void testRemove() throws Exception {

        String name = "Пупкин";
        String inn = "09999991110";
        Client client = new Client(name, inn);
        assertTrue(dao.save(client));
        assertTrue(dao.remove(client));

    }

    @Test
    public void testFind() throws Exception {

        String name = "Пупкин";
        String inn = "09999991110";
        Client client = new Client(name, inn);
        assertTrue(dao.save(client));
        assertNotNull(dao.findById(Client.class,client.getId()));

    }

    @Test
    public void testSearch() throws Exception {

        String name = "Пупкин";
        String inn = "09999991110";
        Client client = new Client(name, inn);
        assertTrue(dao.save(client));
        assertNotNull(dao.search("select c from Client c where c.inn='09999991110'"));

    }
}
