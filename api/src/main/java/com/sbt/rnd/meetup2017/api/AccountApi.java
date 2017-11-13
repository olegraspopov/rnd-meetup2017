package com.sbt.rnd.meetup2017.api;

import com.sbt.rnd.meetup2017.data.ogm.Account;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import com.sbt.rnd.meetup2017.data.ogm.dictionary.Currency;

import java.util.Collection;
import java.util.List;

public interface AccountApi {

    Account create(Long clientId, String accountNumber, String name, Integer currencyIntCode);

    boolean update(Account account);

    boolean delete(Long accId);

    boolean reserveAccount(Long clientId,String accountNumber);

    boolean openAccount(String accountNumber);

    List<Account> getAccountsByClient(Long clientId);
}
