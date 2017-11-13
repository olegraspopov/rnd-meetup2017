package com.sbt.rnd.meetup2017.api.impl;

import com.sbt.rnd.meetup2017.api.AccountApi;
import com.sbt.rnd.meetup2017.dao.IDao;
import com.sbt.rnd.meetup2017.data.ogm.Account;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import com.sbt.rnd.meetup2017.data.ogm.dictionary.Currency;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountApiImpl implements AccountApi {

    @Autowired
    private IDao dao;

    @Override
    public Account create(Long clientId, String accountNumber, String name, Integer currencyIntCode) {
        Client client=dao.findById(Client.class,clientId);
        if (client==null)
            throw new RuntimeException("Клиент с id="+clientId+" не найден в системе");
        Account account = new Account(client, accountNumber,name);
        if (currencyIntCode!=null) {
           List<Currency> currencyList=dao.search("select c from Currency c where c.intCode="+currencyIntCode);
           if (currencyList.size()==0)
               throw new RuntimeException("Валюта с IntCode="+currencyIntCode+" не найдена в системе");
           account.setCurrency(currencyList.get(0));
        }
        dao.save(account);

        return account;
    }

    @Override
    public boolean update(Account account) {
        if (account!=null)
            return dao.save(account);
        return false;
    }

    @Override
    public boolean delete(Long accId) {
        Account account= dao.findById(Account.class, accId);
        if (account!=null)
            return dao.remove(account);
        return false;
    }

    @Override
    public boolean reserveAccount(Long clientId, String accountNumber) {
        return false;
    }

    @Override
    public boolean openAccount(String accountNumber) {
        return false;
    }

    @Override
    public List<Account> getAccountsByClient(Long clientId) {
        return null;
    }
}
