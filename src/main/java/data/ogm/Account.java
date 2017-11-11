package data.ogm;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Account {
    private Long id;
    private Client client;
    private String accountNumber;
    private String name;
    private Date openDate;
    private Date closeDate;
    @OneToMany(targetEntity=Document.class)
    private Collection<Document> docs;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account(Client client, String accountNumber, String name) {
        this.client = client;
        this.accountNumber = accountNumber;
        this.name = name;
        this.openDate = new Date();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    @ManyToOne(targetEntity=Client.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    //    public Collection<Document> getDocs() {
//        return docs;
//    }
}
