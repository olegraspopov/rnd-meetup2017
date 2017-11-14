package com.sbt.rnd.meetup2017;

import com.sbt.rnd.meetup2017.transport.Consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {

    public static void main( String[] args ){

        int numConsumers = 3;
        String groupId = "octopus";
        List<String> topics = Arrays.asList( "test-topic" );

        ExecutorService executor = Executors.newFixedThreadPool( numConsumers );
        final List<Consumer> consumers = new ArrayList<>();

        for( int i = 0; i < numConsumers; i++ ){
            Consumer consumer = new Consumer( i, groupId, topics );
            consumers.add( consumer );
            executor.submit( consumer );
        }

        Runtime.getRuntime().addShutdownHook( new Thread(){
            @Override
            public void run(){
                for( Consumer consumer : consumers ){
                    consumer.shutdown();
                }
                executor.shutdown();
                try{
                    executor.awaitTermination( 5000, TimeUnit.MILLISECONDS );
                }catch( InterruptedException e ){
                    e.printStackTrace();
                }
            }
        } );
    }
}
