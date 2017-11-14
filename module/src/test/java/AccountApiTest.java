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
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
        Currency currency = new Currency("USD", 840, "Доллар");
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
        String accNumber = "40817810000000000002";
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
        String accNumber = "40817810000000000003";
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
        String accNumber = "40817810000000000004";
        Account account = accountApi.create(client.getId(), accNumber, accName, null);
        assertTrue(accountApi.getAccountsByClient(client.getId()).size() > 0);

    }

    @Test
    public void testReserve() throws Exception {

        Client client = clientApi.create("Погодин", "1111111111", null);
        assertThat(client, is(notNullValue(Client.class)));
        String accNumber = "40817810000000000111";
        assertTrue(accountApi.reserveAccount(client.getId(), accNumber, null));

        Account account = accountApi.getAccountByNumber(accNumber);
        assertThat(account, is(notNullValue(Account.class)));
        assertFalse(accountApi.accountIsOpen(account));

        try {
            accountApi.reserveAccount(client.getId(), accNumber, null);
        } catch (RuntimeException ex) {
            assertEquals(ex.getMessage(), "Счет с номером=" + accNumber + "  уже зарегистрирован в системе");
        }
    }

    @Test
    public void testOpen() throws Exception {

        Client client = clientApi.create("Погодин", "1111111111", null);
        assertThat(client, is(notNullValue(Client.class)));
        String accNumber = "40817810000000000222";
        assertTrue(accountApi.reserveAccount(client.getId(), accNumber, null));

        assertTrue(accountApi.openAccount(accNumber));
        Account account = accountApi.getAccountByNumber(accNumber);
        assertThat(account, is(notNullValue(Account.class)));
        assertTrue(accountApi.accountIsOpen(account));

    }

    @Test
    public void testReserveSimultaneous() throws Exception {

        Client client = clientApi.create("Погодин", "1111111666", null);
        assertThat(client, is(notNullValue(Client.class)));
        String accNumber = "40817810000000000666";
        int n = 10;
        Semaphore s = new Semaphore(1 - n);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    accountApi.reserveAccount(client.getId(), accNumber, null);
                } catch (RuntimeException ex) {
                    assertEquals(ex.getMessage(), "Счет с номером=" + accNumber + "  уже зарегистрирован в системе");
                } finally {
                    s.release();
                }
            }
        };

        ArrayList<Thread> threadList = new ArrayList();
        for (int i = 0; i < n; i++) {
            threadList.add(new Thread(runnable));
        }
        for (Thread t : threadList) {
            t.start();
        }
        s.acquire();
        Account account = accountApi.getAccountByNumber(accNumber);
        assertThat(account, is(notNullValue(Account.class)));
        assertFalse(accountApi.accountIsOpen(account));

    }

}
