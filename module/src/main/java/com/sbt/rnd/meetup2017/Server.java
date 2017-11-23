package com.sbt.rnd.meetup2017;

import com.sbt.rnd.meetup2017.api.account.AccountApi;
import com.sbt.rnd.meetup2017.api.client.ClientApi;
import com.sbt.rnd.meetup2017.data.ogm.Client;
import com.sbt.rnd.meetup2017.transport.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Server {

    public static void main(String[] args) {
       /* ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-beans.xml");
        AccountApi accountApi = (AccountApi) context.getBean("accountApi");
        accountApi.getAccountByNumber("21123123");
        ClientApi clientApi = (ClientApi) context.getBean("clientApi");
        Client client = clientApi.create("sdkjfds", "asdasd", null);
        System.out.println(client.getInn());*/

        int numConsumers = 1;
        String groupId = "octopus";
        List<String> topics = Arrays.asList("test-topic");

        new TransportListener(numConsumers, groupId, "localhost:9092", topics).start(new MsgHandler<ConsumerRecord<String, byte[]>>() {
            @Override
            public <V> V  handle(ConsumerRecord<String, byte[]> record) {
                Msg msg = Serializer.deserialize(record.value());
                System.out.println("msg: " + msg.toString());
                Msg respMsg = new Msg("123456", new Date());
                MsgProducer respProd = new MsgProducer("test-topic-reply", respMsg, groupId, "localhost:9092");
                respProd.sendMsg();
                System.out.println("12334send answer: " + respMsg);

                return null;
            }
        });

    }


}
