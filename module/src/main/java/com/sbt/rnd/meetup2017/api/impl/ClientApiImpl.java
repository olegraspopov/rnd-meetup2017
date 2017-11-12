package com.sbt.rnd.meetup2017.api.impl;

import com.sbt.rnd.meetup2017.api.ClientApi;
import com.sbt.rnd.meetup2017.data.ogm.Address;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Collection;

public class ClientApiImpl implements ClientApi {

    @Autowired
    private EntityManager em;

    @Override
    public Client create(String name, String inn, Collection<Address> addresses) {
        Client client = new Client(name, inn);
        client.setAddresses(addresses);
        em.getTransaction().begin();

        em.persist(client);

        em.getTransaction().commit();
        return client;
    }

    @Override
    public boolean update(Client client) {
        em.getTransaction().begin();

        em.persist(client);

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean delete(Long clientId) {
        Client client = em.find(Client.class, clientId);
        em.remove(client);
        return true;
    }
}
