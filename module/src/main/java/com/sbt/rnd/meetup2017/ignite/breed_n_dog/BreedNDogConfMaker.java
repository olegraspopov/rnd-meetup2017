package com.sbt.rnd.meetup2017.ignite.breed_n_dog;

import com.sbt.rnd.meetup2017.data.ogm.breed_n_dog.Breed;
import com.sbt.rnd.meetup2017.ignite.ConfigurationMaker;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;

import java.util.List;

public class BreedNDogConfMaker extends ConfigurationMaker {
    @Override
    public void addCacheConfigs(List<CacheConfiguration<?, ?>> config) {
        config.add(
                createCacheConfig("Dog", CacheMode.REPLICATED)
                        .withKeyType(Long.class)
                        .appendIndex("id", Long.class)
                        .appendField("name", String.class)
                        .appendField("breed", Breed.class)
                        .build()
        );
    }
}
