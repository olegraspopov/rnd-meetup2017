package com.sbt.rnd.meetup2017.transport.api.document;

import com.sbt.rnd.meetup2017.data.ogm.Document;
import com.sbt.rnd.meetup2017.transport.api.Api;
import com.sbt.rnd.meetup2017.transport.api.ApiRequest;
import com.sbt.rnd.meetup2017.transport.api.Request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiRequest(api=DocumentApi.class)
public interface DocumentApiRequest {

    Request<Document> create(Long debetAccount, Long creditAccount, BigDecimal sumDoc, String aim);

    Request<Boolean> update(Document document);

    Request<Boolean> delete(Long docId);

    Request<Boolean> workOut(Long docId, Date dateWork);

    Request<List<Document>> getDocumentsByClient(Long clientId, DocumentFilter filter);

    Request<Document> getDocumentById(Long id);

}
