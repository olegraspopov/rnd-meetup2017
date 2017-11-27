package com.sbt.rnd.meetup2017.transport.config;

import com.sbt.rnd.meetup2017.transport.api.account.AccountApiRequest;
import com.sbt.rnd.meetup2017.transport.api.client.ClientApiRequest;
import com.sbt.rnd.meetup2017.transport.api.TransportProxyFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TransportConfig.class})
public class ApiRequestConfig {

    @Bean
    ClientApiRequest clientApiRequest(@Qualifier("transportProxyFactory") TransportProxyFactory proxyFactory) {
        ClientApiRequest clientApiRequest = proxyFactory.createTransportProxy(ClientApiRequest.class);
        return clientApiRequest;
    }

    @Bean
    AccountApiRequest accountApiRequest(@Qualifier("transportProxyFactory") TransportProxyFactory proxyFactory) {
        AccountApiRequest accountApiRequest = proxyFactory.createTransportProxy(AccountApiRequest.class);
        return accountApiRequest;
    }

}
