package com.sbt.rnd.meetup2017.api.client;

import com.sbt.rnd.meetup2017.data.ogm.Address;
import com.sbt.rnd.meetup2017.data.ogm.Client;

import java.util.Collection;
import java.util.List;

public interface ClientApi {

    Client create(String name, String inn, Collection<Address> addresses);

    boolean update(Client client);

    boolean delete(Long clientId);

    List<Client> getClientByInn(String inn);

    Client getClientById(Long id);

}
