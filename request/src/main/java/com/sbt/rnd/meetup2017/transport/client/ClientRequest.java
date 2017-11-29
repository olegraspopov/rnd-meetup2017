package com.sbt.rnd.meetup2017.transport.client;

import com.sbt.rnd.meetup2017.data.ogm.Account;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import com.sbt.rnd.meetup2017.data.ogm.Document;
import com.sbt.rnd.meetup2017.transport.api.account.AccountApiRequest;
import com.sbt.rnd.meetup2017.transport.api.client.ClientApiRequest;
import com.sbt.rnd.meetup2017.transport.api.Request;
import com.sbt.rnd.meetup2017.transport.api.document.DocumentApiRequest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.concurrent.*;

public class ClientRequest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.sbt.rnd.meetup2017.transport.config");
        ClientApiRequest clientApiRequest = (ClientApiRequest) context.getBean("clientApiRequest");
        Request<Client> request = clientApiRequest.create("test", "1234567890", null);
        Client client=request.execute();
        System.out.println(client.toString());

        AccountApiRequest accountApiRequest = (AccountApiRequest) context.getBean("accountApiRequest");
        Request<Account> requestAcc = accountApiRequest.create(client.getId(), "408170000000001223", "test",null);
        Account account=requestAcc.execute();
        System.out.println(account.toString());

        requestAcc = accountApiRequest.create(client.getId(), "408170000000002343", "test",null);
        Account accountCt=requestAcc.execute();
        System.out.println(accountCt.toString());

        DocumentApiRequest documentApiRequest = (DocumentApiRequest) context.getBean("documentApiRequest");
        Request<Document> requestDoc = documentApiRequest.create(account.getId(),accountCt.getId(), BigDecimal.valueOf(100), "test");
        Document document=requestDoc.execute();
        System.out.println(document.toString());

        Request<Boolean> requestAccRes = accountApiRequest.reserveAccount(client.getId(), "408170000000001223",null);
        Boolean accRes=requestAccRes.execute();
        System.out.println("reserved="+accRes);

    }
}
