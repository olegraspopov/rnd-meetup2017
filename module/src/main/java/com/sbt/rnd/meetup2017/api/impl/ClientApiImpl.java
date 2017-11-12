package com.sbt.rnd.meetup2017.api.impl;

import com.sbt.rnd.meetup2017.api.ClientApi;
import com.sbt.rnd.meetup2017.dao.IDao;
import com.sbt.rnd.meetup2017.dao.IExecutor;
import com.sbt.rnd.meetup2017.data.ogm.Address;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Collection;

public class ClientApiImpl implements ClientApi {

    @Autowired
    private IDao dao;

    @Override
    public Client create(String name, String inn, Collection<Address> addresses) {
        Client client = new Client(name, inn);
        client.setAddresses(addresses);

        if (dao.save(client))
            return client;
        else
            return null;
    }

    @Override
    public boolean update(Client client) {
        if (client!=null)
            return dao.save(client);
        return false;
    }

    @Override
    public boolean delete(Long clientId) {
        Client client = dao.find(Client.class, clientId);
        if (client!=null)
            return dao.remove(client);
        return false;
    }
}
