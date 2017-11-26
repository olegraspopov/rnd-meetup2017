package com.sbt.rnd.meetup2017.transport.client;

import com.sbt.rnd.meetup2017.data.ogm.Account;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import com.sbt.rnd.meetup2017.transport.api.account.AccountApiRequest;
import com.sbt.rnd.meetup2017.transport.api.client.ClientApiRequest;
import com.sbt.rnd.meetup2017.transport.api.Request;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        Request<Account> requestAcc = accountApiRequest.create(client.getId(), "40817000000000", "test",null);
        Account account=requestAcc.execute();
        System.out.println(account.toString());

    }
}
