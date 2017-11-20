package com.sbt.rnd.meetup2017.data.ogm;

import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Indexed
public class Client {

    private Long id;
    private String name;
    @QuerySqlField(index = true)
    private String inn;
    private Integer version;

    private Collection<Address> addresses;

    public Client(String name, String inn) {
        this.name = name;
        this.inn = inn;
    }

    public Client() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Field(analyze = Analyze.YES)
    public String getName() {
        return name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ElementCollection
    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
