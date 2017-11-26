package com.sbt.rnd.meetup2017.transport.config;

import com.sbt.rnd.meetup2017.transport.api.TransportProxyFactory;
import com.sbt.rnd.meetup2017.transport.impl.TransportProxyFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransportConfig {

    @Bean
    TransportProxyFactory transportProxyFactory(){
        return new TransportProxyFactoryImpl();
    }
}
