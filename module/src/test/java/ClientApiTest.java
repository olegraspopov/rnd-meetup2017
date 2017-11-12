import com.sbt.rnd.meetup2017.api.ClientApi;
import com.sbt.rnd.meetup2017.data.ogm.Account;
import com.sbt.rnd.meetup2017.data.ogm.Address;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import com.sbt.rnd.meetup2017.data.ogm.Document;
import com.sbt.rnd.meetup2017.data.ogm.breed_n_dog.Breed;
import com.sbt.rnd.meetup2017.data.ogm.breed_n_dog.Dog;
import com.sbt.rnd.meetup2017.data.ogm.dictionary.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "classpath:spring-beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientApiTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private ClientApi clientApi;

    @Test
    public void testCreate() throws Exception {

        String name= "Пупкин";
        String inn="09999991110";
        Client client = clientApi.create(name, inn,null);

        assertThat(client.getId(), is(notNullValue(Long.class)));
        assertThat(client.getName(), is(name));
        assertThat(client.getInn(), is(inn));

    }

    @Test
    public void testUpdate() throws Exception {


        String inn="09999991110";
       /* Client client =  em.find(Client.class, inn);
        Long id=client.getId();
        assertThat(id, is(notNullValue(Long.class)));
        assertThat(client.getInn(), is(inn));
        client.setName("Иванов");
        client.setInn("123456");

        clientApi.update(client);
        client =  em.find(Client.class, id);*/


    }
}
