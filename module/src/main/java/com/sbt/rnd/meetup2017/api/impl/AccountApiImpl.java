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

public class AccountApiImpl implements AccountApi {

    @Autowired
    private IDao dao;

    @Autowired
    ClientApi clientApi;

    @Autowired
    CurrencyUtils currencyUtils;

    @Override
    public Account create(Long clientId, String accountNumber, String name, Integer currencyIntCode) {
        Client client = clientApi.getClientById(clientId);
        Account account = new Account(client, accountNumber, name);
        if (currencyIntCode != null) {
            account.setCurrency(currencyUtils.getByIntCode(currencyIntCode));
        } else
            account.setCurrency(currencyUtils.getDefault());

        dao.save(account);

        return account;
    }

    @Override
    public boolean update(Account account) {
        if (account != null)
            return dao.save(account);
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
    public boolean reserveAccount(Long clientId, String accountNumber,Integer currencyIntCode) {
        Account account = getAccountByNumber(accountNumber);
        if (account != null)
            throw new RuntimeException("Счет с номером=" + accountNumber + "  уже зарезервирован");

        return create(clientId, accountNumber, "Резервирование счета для клиента id=" + clientId, currencyIntCode) != null;
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

        return account.getState()>0;
    }

    @Override
    public List<Account> getAccountsByClient(Long clientId) {
        //Client client=clientApi.getClientById(clientId);
        //em.createQuery("select a from Account a where a.client=:client").setParameter("client",client).getResultList();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("clientId", clientId);
        return dao.search("select a from Account a where a.clientId=:clientId", parameters);
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
