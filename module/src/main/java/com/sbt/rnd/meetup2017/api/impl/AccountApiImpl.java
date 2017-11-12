package com.sbt.rnd.meetup2017.api.impl;

import com.sbt.rnd.meetup2017.api.AccountApi;
import com.sbt.rnd.meetup2017.api.ClientApi;
import com.sbt.rnd.meetup2017.dao.IDao;
import com.sbt.rnd.meetup2017.data.ogm.Account;
import com.sbt.rnd.meetup2017.data.ogm.Address;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import com.sbt.rnd.meetup2017.data.ogm.dictionary.Currency;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Collection;

public class AccountApiImpl implements AccountApi {

    @Autowired
    private IDao dao;

    @Override
    public Account create(Client client, String accountNumber, String name, Currency currency) {

        Account account = new Account(client, accountNumber,name);
        if (currency!=null)
            account.setCurrency(currency);
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
        Account account= dao.find(Account.class, accId);
        if (account!=null)
            return dao.remove(account);
        return false;
    }
}
