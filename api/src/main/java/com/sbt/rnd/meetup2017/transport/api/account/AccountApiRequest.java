package com.sbt.rnd.meetup2017.transport.api.account;

import com.sbt.rnd.meetup2017.data.ogm.Account;
import com.sbt.rnd.meetup2017.transport.api.ApiRequest;
import com.sbt.rnd.meetup2017.transport.api.Request;

import java.util.List;

@ApiRequest(api=AccountApi.class)
public interface AccountApiRequest {

    Request<Account> create(Long clientId, String accountNumber, String name, Integer currencyIntCode);

    Request<Boolean> update(Account account);

    Request<Boolean> delete(Long accId);

    Request<Boolean> reserveAccount(Long clientId, String accountNumber, Integer currencyIntCode);

    Request<Boolean> openAccount(String accountNumber);

    Request<List<Account>> getAccountsByClient(Long clientId);

    Request<Account> getAccountById(Long id);

    Request<Account> getAccountByNumber(String accountNumber);

    Request<Boolean> accountIsOpen(Account account);
}
