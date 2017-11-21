package com.sbt.rnd.meetup2017.api.impl;

import com.sbt.rnd.meetup2017.api.AccountApi;
import com.sbt.rnd.meetup2017.api.ClientApi;
import com.sbt.rnd.meetup2017.dao.IDao;
import com.sbt.rnd.meetup2017.data.ogm.Account;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import com.sbt.rnd.meetup2017.data.ogm.dictionary.Currency;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountApiImpl implements AccountApi {

    @Autowired
    private IDao dao;

    @Autowired
    ClientApi clientApi;

    @Autowired
    CurrencyUtils currencyUtils;

    private static ConcurrentMap<String, ReentrantLock> lockCurator = new ConcurrentHashMap<>();

    @Override
    public Account create(Client client, String accountNumber, String name, Integer currencyIntCode) {

        if (!lockCurator.containsKey(accountNumber)) {
            lockCurator.putIfAbsent(accountNumber, new ReentrantLock());
        }
        Lock lock = lockCurator.get(accountNumber);
        lock.lock();
        try {

            Account account = getAccountByNumber(accountNumber);
            if (account != null)
                throw new RuntimeException("Счет с номером=" + accountNumber + "  уже зарегистрирован в системе");

            account = new Account(client, accountNumber, name);
            if (currencyIntCode != null) {
                account.setCurrency(currencyUtils.getByIntCode(currencyIntCode));
            } else
                account.setCurrency(currencyUtils.getDefault());

            if (dao.save(account))
                return account;


        } finally {
            lock.unlock(); // снимаем блокировку
        }
        return null;

    }

    @Override
    public boolean update(Account account) {
        if (account != null)
            return dao.save(account,true);
        return false;
    }

    @Override
    public boolean delete(Long accId) {
        Account account = dao.findById(Account.class, accId);
        if (account != null)
            return dao.remove(account);
        return false;
    }

    @Override
    public boolean reserveAccount(Client client, String accountNumber, Integer currencyIntCode) {

        return create(client, accountNumber, "Резервирование счета для клиента id=" + client.getId(), currencyIntCode) != null;
    }

    @Override
    public boolean openAccount(String accountNumber) {
        Account account = getAccountByNumber(accountNumber);
        if (account == null)
            throw new RuntimeException("Счет с номером=" + accountNumber + " не найден в системе");
        if (accountIsOpen(account))
            throw new RuntimeException("Счет с номером=" + accountNumber + " уже открыт");
        account.setState(1);
        account.setOpenDate(new Date());
        Client client = account.getClient();
        account.setName(client.getName() + ". ИНН " + client.getInn());
        return true;
    }

    @Override
    public boolean accountIsOpen(Account account) {

        return account.getState() > 0;
    }

    @Override
    public List<Account> getAccountsByClient(Client client) {
        //Client client=clientApi.getClientById(clientId);
        //em.createQuery("select a from Account a where a.client=:client").setParameter("client",client).getResultList();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("client", client);
        return dao.search("select a from Account a where a.client=:client", parameters);
    }

    @Override
    public Account getAccountById(Long id) {
        Account account = dao.findById(Account.class, id);
        if (account == null)
            throw new RuntimeException("Счет с id=" + id + " не найден в системе");
        return account;
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("accountNumber", accountNumber);
        List<Account> accountList = dao.search("select a from Account a where a.accountNumber=:accountNumber", parameters);
        if (accountList.size() == 0)
            //throw new RuntimeException("Счет с номером=" + accountNumber + " не найден в системе");
            return null;
        else if (accountList.size() > 1)
            throw new RuntimeException("В системе найдено несколько счетов с номером=" + accountNumber);
        return accountList.get(0);
    }


}
