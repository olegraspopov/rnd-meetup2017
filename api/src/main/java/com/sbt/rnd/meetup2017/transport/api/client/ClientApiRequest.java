package com.sbt.rnd.meetup2017.transport.api.client;

import com.sbt.rnd.meetup2017.data.ogm.Address;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import com.sbt.rnd.meetup2017.transport.api.ApiRequest;
import com.sbt.rnd.meetup2017.transport.api.Request;

import java.util.Collection;
import java.util.List;

@ApiRequest(api=ClientApi.class)
public interface ClientApiRequest {

    Request<Client> create(String name, String inn, Collection<Address> addresses);

    Request<Boolean> update(Client client);

    Request<Boolean> delete(Long clientId);

    Request<List<Client>> getClientByInn(String inn);

    Request<Client> getClientById(Long id);

}
