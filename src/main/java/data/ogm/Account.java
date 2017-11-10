package data.ogm;

import data.ogm.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @OneToMany(targetEntity=Document.class)
    private Collection<Document> docs;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account(Long clientId, String accountNumber, String name) {
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.name = name;
        this.openDate = new Date();
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

//    public Collection<Document> getDocs() {
//        return docs;
//    }
}
