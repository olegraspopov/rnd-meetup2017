package data.ogm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

@Entity
public class Client {

    private Long id;
    private String name;
    private String inn;

    private Collection<Address> addresses;

    public Client(String name, String inn) {
        this.name = name;
        this.inn = inn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
