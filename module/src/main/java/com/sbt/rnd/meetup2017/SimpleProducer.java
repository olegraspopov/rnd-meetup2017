package com.sbt.rnd.meetup2017;

import com.sbt.rnd.meetup2017.transport.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Arrays;
import java.util.concurrent.*;

public class SimpleProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String topic = "test-topic";

        Msg msg = new Msg("123456", "this is message SimpleProducer");

        MsgProducer msgProducer = new MsgProducer(topic, msg, "octopus", "localhost:9092");

        msgProducer.sendMsg();
        System.out.println("message sent.");
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                String result;
                MsgConsumer msgConsumer = new MsgConsumer(134, "octopus", "localhost:9092", Arrays.asList(topic + "-reply"), new MsgHandler<ConsumerRecord<String, byte[]>>() {

                    @Override
                    public <V> V handle(ConsumerRecord<String, byte[]> record) {
                        Msg msg = Serializer.deserialize(record.value());
                        System.out.println("Received answer msg: " + msg.toString());

                        return (V) msg.toString();
                    }
                });

                return msgConsumer.getMsgReply();
            }
        };
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Future<?> task = threadPool.submit(callable);
        threadPool.shutdown();

        System.out.println("result = " + task.get());


    }
}
