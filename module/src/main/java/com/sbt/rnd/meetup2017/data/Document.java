package com.sbt.rnd.meetup2017.data;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sbt-raspopov-om on 01/11/2017.
 */
public class Document {
    private static final AtomicLong ID_GEN = new AtomicLong();

    @QuerySqlField(index = true)
    private Long id;
    @QuerySqlField(index = true)
    private Long debetAccount;
    @QuerySqlField(index = true)
    private Long creditAccount;
    private Long sumDoc;
    private Date dateDoc;
    private String aim;

    public Document(Long debetAccount, Long creditAccount, Long sumDoc, Date dateDoc, String aim) {
        this.id = ID_GEN.incrementAndGet();
        this.debetAccount = debetAccount;
        this.creditAccount = creditAccount;
        this.sumDoc = sumDoc;
        this.dateDoc = dateDoc;
        this.aim = aim;
    }
}
