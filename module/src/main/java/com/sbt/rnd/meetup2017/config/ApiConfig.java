package com.sbt.rnd.meetup2017.config;

import com.sbt.rnd.meetup2017.api.ClientApi;
import com.sbt.rnd.meetup2017.api.impl.ClientApiImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class ApiConfig {

    @Bean
    ClientApi clientApi(){
        return new ClientApiImpl();
    }
}
