package com.sbt.rnd.meetup2017.config;

import com.sbt.rnd.meetup2017.api.account.AccountApi;
import com.sbt.rnd.meetup2017.api.client.ClientApi;
import com.sbt.rnd.meetup2017.api.document.DocumentApi;
import com.sbt.rnd.meetup2017.api.impl.AccountApiImpl;
import com.sbt.rnd.meetup2017.api.impl.ClientApiImpl;
import com.sbt.rnd.meetup2017.api.impl.DocumentApiImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    ClientApi clientApi(){
        return new ClientApiImpl();
    }

    @Bean
    AccountApi accountApi(){
        return new AccountApiImpl();
    }

    @Bean
    DocumentApi documentApi(){
        return new DocumentApiImpl();
    }
}
