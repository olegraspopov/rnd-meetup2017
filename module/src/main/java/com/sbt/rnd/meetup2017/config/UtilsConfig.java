package com.sbt.rnd.meetup2017.config;

import com.sbt.rnd.meetup2017.api.impl.CurrencyUtils;
import com.sbt.rnd.meetup2017.dao.Dao;
import com.sbt.rnd.meetup2017.dao.IDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.persistence.EntityManager;

@Configuration
public class UtilsConfig {

    @Bean
    CurrencyUtils currencyUtils(IDao dao){
        return CurrencyUtils.getInstance(dao);
    }
}
