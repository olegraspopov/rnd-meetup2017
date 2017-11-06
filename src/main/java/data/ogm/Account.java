package data.ogm;

import data.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Date;

@Entity
public class Account {
    private Long id;
    private Long clientId;
    private String accountNumber;
    private String name;
    private Date openDate;
    private Date closeDate;
    private Collection<Document> docs;

    @Id
    public Long getId() {
        return id;
    }
}
