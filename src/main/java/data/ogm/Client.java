package data.ogm;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;

@Entity
public class Client {

    private Long id;
    private String name;
    private String inn;

    private Collection<Address> addresses;

    @Id
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInn() {
        return inn;
    }
}
