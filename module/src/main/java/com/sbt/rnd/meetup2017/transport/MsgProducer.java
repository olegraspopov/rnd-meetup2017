package com.sbt.rnd.meetup2017.transport;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MsgProducer<T> {

    private String topicName;
    private Msg msg;
    private Producer<String, byte[]> producer;

    public MsgProducer(String topicName, Msg msg,String groupId,String bootstrapServer) {
        this.topicName = topicName;
        this.msg = msg;
        Properties props = new Properties() {{
            put("bootstrap.servers", bootstrapServer);
            put("acks", "all");
            put("retries", 1);
            put("buffer.memory", 33554432);
            put("batch.size", 16384);
            put("linger.ms", 1);
            put("client.id", groupId);
            put("key.serializer", org.apache.kafka.common.serialization.StringSerializer.class.getName());
            put("value.serializer", org.apache.kafka.common.serialization.ByteArraySerializer.class.getName());
        }};
        producer = new KafkaProducer<String, byte[]>(props);
    }

    public void sendMsg() {

        sendMsg(null);
    }

    public void sendMsg(Callback callback) {

        producer.send(new ProducerRecord<String, byte[]>(topicName, Serializer.serialize(msg)),callback);
        producer.close();
    }
}
