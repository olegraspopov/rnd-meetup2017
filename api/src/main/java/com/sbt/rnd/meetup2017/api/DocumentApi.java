package com.sbt.rnd.meetup2017.api;

import com.sbt.rnd.meetup2017.data.ogm.Account;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import com.sbt.rnd.meetup2017.data.ogm.Document;
import com.sbt.rnd.meetup2017.data.ogm.dictionary.Currency;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface DocumentApi {

    Document create(Long debetAccount, Long creditAccount, BigDecimal sumDoc, String aim);

    boolean update(Document document);

    boolean delete(Long docId);

    boolean workOut(Long docId, Date dateWork);

    List<Document> getDocumentsByClient(Long clientId,DocumentFilter filter);

    Document getDocumentById(Long id);

}
