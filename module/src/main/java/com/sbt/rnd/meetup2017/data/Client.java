package com.sbt.rnd.meetup2017.data;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sbt-raspopov-om on 01/11/2017.
 */
public class Client {
    private static final AtomicLong ID_GEN = new AtomicLong();

    @QuerySqlField(index = true)
    private Long id;
    @QuerySqlField(index = true)
    private String name;
    @QuerySqlField(index = true)
    private String inn;

    private Collection<Address> addresses;

    public Client(String name, String inn) {
        this.id = ID_GEN.incrementAndGet();
        this.name = name;
        this.inn = inn;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getInn() {
        return inn;
    }

    @Override
    public String toString() {
        return "com.sbt.rnd.meetup2017.data.Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", inn='" + inn + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
