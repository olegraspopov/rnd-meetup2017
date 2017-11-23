package com.sbt.rnd.meetup2017.transport;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.StreamSupport;

public class MsgConsumer<T> implements Runnable {
    private final KafkaConsumer<String, byte[]> consumer;
    private final List<String> topics;
    private final int id;
    private MsgHandler<T> msgHandler;

    public MsgConsumer(int id, String groupId, String bootstrapServer, List<String> topics, MsgHandler<T> msgHandler) {
        this.id = id;
        this.topics = topics;
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServer);
        props.put("group.id", groupId);
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", ByteArrayDeserializer.class.getName());
        this.consumer = new KafkaConsumer<>(props);
        this.msgHandler = msgHandler;
    }

    @Override
    public void run() {
        try {
            consumer.subscribe(topics);

            while (true) {
                ConsumerRecords<String, byte[]> records = consumer.poll(Long.MAX_VALUE);
                StreamSupport.stream(records.spliterator(), false).forEach(System.out::println);
                records.forEach(value -> msgHandler.handle((T) value));

            }
        } catch (WakeupException e) {
            // ignore for shutdown
        } finally {
            consumer.close();
        }
    }

    public <V> V getMsgReply() {
        V result = null;
        try {
            consumer.subscribe(topics);

            ConsumerRecords<String, byte[]> records = consumer.poll(Long.MAX_VALUE);
            StreamSupport.stream(records.spliterator(), false).forEach(System.out::println);
            Iterator<ConsumerRecord<String, byte[]>> iterator = records.iterator();
            while (iterator.hasNext()) {
                result = msgHandler.handle((T) iterator.next());
            }

        } catch (WakeupException e) {
            // ignore for shutdown
        } finally {
            consumer.close();
        }
        return result;
    }

    public void shutdown() {
        consumer.wakeup();
    }
}
