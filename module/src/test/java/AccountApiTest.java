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

import javax.persistence.EntityManager;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

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

        String name = "Пупкин";
        String inn = "09999991110";
        Client client = clientApi.create(name, inn, null);
        assertThat(client, is(notNullValue(Client.class)));
        Currency currency = new Currency("RUB", 810, "Российский рубль");
        String accName = "Основной";
        String accNumber = "40817810000000000001";
        Account account = accountApi.create(client, accNumber, accName, currency);
        assertThat(account, is(notNullValue(Account.class)));
        assertThat(account.getId(), is(notNullValue(Long.class)));
        assertThat(account.getName(), is(accName));
        assertThat(account.getAccountNumber(), is(accNumber));

    }

    @Test
    public void testUpdate() throws Exception {

        String inn = "09999991110";
        Client client = clientApi.create("Пупкин", inn, null);
        assertThat(client, is(notNullValue(Client.class)));
        String accName = "Основной";
        String accNumber = "40817810000000000001";
        Account account = accountApi.create(client, accNumber, accName, null);

        account = dao.find(Account.class, account.getId());
        assertThat(account, is(notNullValue(Account.class)));
        Long id = account.getId();
        assertThat(id, is(notNullValue(Long.class)));
        assertThat(account.getAccountNumber(), is(accNumber));
        account.setName("РКО");
        accNumber = "40817810000000000003";
        account.setAccountNumber(accNumber);
        account.setBalance(BigDecimal.valueOf(100));
        accountApi.update(account);
        account = dao.find(Account.class, id);
        assertThat(account.getAccountNumber(), is(accNumber));
        assertThat(account.getBalance(), is(BigDecimal.valueOf(100)));

    }

    @Test
    public void testDelete() throws Exception {

        Client client = clientApi.create("Пупкин", "12312312", null);
        assertThat(client, is(notNullValue(Client.class)));
        String accName = "Основной";
        String accNumber = "40817810000000000001";
        Account account = accountApi.create(client, accNumber, accName, null);
        Long id = account.getId();
        accountApi.delete(id);
        account = dao.find(Account.class, id);
        assertThat(account, is(nullValue(Account.class)));

    }
}
