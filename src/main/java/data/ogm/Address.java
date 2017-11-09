package data.ogm;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Address {

    private Long id;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String street;
    private int zip;

    public Address(String street, int zip) {
        this.street = street;
        this.zip = zip;
    }

}
