import com.sbt.rnd.meetup2017.api.ClientApi;
import com.sbt.rnd.meetup2017.dao.IDao;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "classpath:spring-beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientApiTest {
    @Autowired
    private IDao dao;

    @Autowired
    private EntityManager em;

    @Autowired
    private ClientApi clientApi;

    @Test
    public void testCreate() throws Exception {

        String name = "Пупкин";
        String inn = "09999991110";
        Client client = clientApi.create(name, inn, null);
        assertThat(client, is(notNullValue(Client.class)));
        assertThat(client.getId(), is(notNullValue(Long.class)));
        assertThat(client.getName(), is(name));
        assertThat(client.getInn(), is(inn));

    }

    @Test
    public void testUpdate() throws Exception {

        String inn = "09999991110";
        Client client=clientApi.create("Пупкин", inn, null);

        //List<Client> clientList =  dao.search("select c from Client c");
        client = dao.findById(Client.class, client.getId());
        assertThat(client, is(notNullValue(Client.class)));
        Long id = client.getId();
        assertThat(id, is(notNullValue(Long.class)));
        assertThat(client.getInn(), is(inn));
        client.setName("Иванов");
        inn = "123456";
        client.setInn(inn);

        clientApi.update(client);
        client = dao.findById(Client.class, id);
        assertThat(client.getInn(), is(inn));

    }

    @Test
    public void testDelete() throws Exception {

        Client client=clientApi.create("Пупкин", "12312312", null);
        assertThat(client, is(notNullValue(Client.class)));
        Long id = client.getId();
        clientApi.delete(id);
        client = dao.findById(Client.class, id);
        assertThat(client, is(nullValue(Client.class)));

    }
}
