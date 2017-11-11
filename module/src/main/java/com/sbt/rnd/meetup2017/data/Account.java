package com.sbt.rnd.meetup2017.data;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sbt-raspopov-om on 01/11/2017.
 */
public class Account {
    private static final AtomicLong ID_GEN = new AtomicLong();

    @QuerySqlField(index = true)
    private Long id;
    @QuerySqlField(index = true)
    private Long clientId;
    @QuerySqlField(index = true)
    private String accountNumber;
    private String name;
    @QuerySqlField(index = true)
    private Date openDate;
    @QuerySqlField(index = true)
    private Date closeDate;

    /* TODO возможно так не получится, надо коллекцию Long - ов*/
    private Collection<Document> docs;

    public Account(Long clientId, String accountNumber, String name) {
        this.id = ID_GEN.incrementAndGet();
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.name = name;
        this.openDate = new Date();
    }
}
