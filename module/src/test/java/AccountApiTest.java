import com.sbt.rnd.meetup2017.api.AccountApi;
import com.sbt.rnd.meetup2017.api.ClientApi;
import com.sbt.rnd.meetup2017.dao.IDao;
import com.sbt.rnd.meetup2017.data.ogm.Account;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import com.sbt.rnd.meetup2017.data.ogm.dictionary.Currency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "classpath:spring-beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountApiTest {
    @Autowired
    private IDao dao;

    @Autowired
    private AccountApi accountApi;

    @Autowired
    private ClientApi clientApi;

    @Test
    public void testCreate() throws Exception {

        String name = "Борисов";
        String inn = "1234567810";
        Client client = clientApi.create(name, inn, null);
        assertThat(client, is(notNullValue(Client.class)));
        Currency currency = new Currency("RUB", 810, "Российский рубль");
        dao.save(currency);
        String accName = "Основной";
        String accNumber = "40817810000000000001";
        Account account = accountApi.create(client.getId(), accNumber, accName, currency.getIntCode());
        assertThat(account, is(notNullValue(Account.class)));
        assertThat(account.getId(), is(notNullValue(Long.class)));
        assertThat(account.getName(), is(accName));
        assertThat(account.getAccountNumber(), is(accNumber));

    }

    @Test
    public void testUpdate() throws Exception {

        String inn = "1255567890";
        Client client = clientApi.create("Николаев", inn, null);
        assertThat(client, is(notNullValue(Client.class)));
        String accName = "Основной";
        String accNumber = "40817810000000000001";
        Account account = accountApi.create(client.getId(), accNumber, accName, null);

        account = accountApi.getAccountById(account.getId());
        assertThat(account, is(notNullValue(Account.class)));
        Long id = account.getId();
        assertThat(id, is(notNullValue(Long.class)));
        assertThat(account.getAccountNumber(), is(accNumber));
        account.setName("РКО");
        accNumber = "40817810000000000003";
        account.setAccountNumber(accNumber);
        account.setBalance(BigDecimal.valueOf(100));
        accountApi.update(account);
        account = dao.findById(Account.class, id);
        assertThat(account.getAccountNumber(), is(accNumber));
        assertThat(account.getBalance(), is(BigDecimal.valueOf(100)));

    }

    @Test
    public void testDelete() throws Exception {

        Client client = clientApi.create("Александров", "4534567890", null);
        assertThat(client, is(notNullValue(Client.class)));
        String accName = "Основной";
        String accNumber = "40817810000000000001";
        Account account = accountApi.create(client.getId(), accNumber, accName, null);
        Long id = account.getId();
        accountApi.delete(id);
        account = dao.findById(Account.class, id);
        assertThat(account, is(nullValue(Account.class)));

    }

    @Test
    public void getAccountsByClient() throws Exception {
        Client client = clientApi.create("Пупкин", "1236767890", null);
        assertThat(client, is(notNullValue(Client.class)));
        String accName = "Основной";
        String accNumber = "40817810000000000001";
        Account account = accountApi.create(client.getId(), accNumber, accName, null);
        assertTrue(accountApi.getAccountsByClient(client.getId()).size()>0);

    }
}
