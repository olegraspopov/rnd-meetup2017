package com.sbt.rnd.meetup2017.api.account;

import com.sbt.rnd.meetup2017.data.ogm.Account;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import com.sbt.rnd.meetup2017.data.ogm.dictionary.Currency;

import java.util.Collection;
import java.util.List;

public interface AccountApi {

    Account create(Client client, String accountNumber, String name, Integer currencyIntCode);

    boolean update(Account account);

    boolean delete(Long accId);

    boolean reserveAccount(Client client,String accountNumber,Integer currencyIntCode);

    boolean openAccount(String accountNumber);

    List<Account> getAccountsByClient(Client client);

    Account getAccountById(Long id);

    Account getAccountByNumber(String accountNumber);

    boolean accountIsOpen(Account account);
}
