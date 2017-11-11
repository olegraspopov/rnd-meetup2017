package com.sbt.rnd.meetup2017.data.ogm;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Document {
    private Long id;
    private Account debetAccount;
    private Account creditAccount;
    private Long sumDoc;
    private Date dateDoc;
    private String aim;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Document(Account debetAccount, Account creditAccount, Long sumDoc, Date dateDoc, String aim) {
        this.debetAccount = debetAccount;
        this.creditAccount = creditAccount;
        this.sumDoc = sumDoc;
        this.dateDoc = dateDoc;
        this.aim = aim;
    }
    @ManyToOne(targetEntity=Account.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Account getDebetAccount() {
        return debetAccount;
    }

    public void setDebetAccount(Account debetAccount) {
        this.debetAccount = debetAccount;
    }

    @ManyToOne(targetEntity=Account.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    public Long getSumDoc() {
        return sumDoc;
    }

    public void setSumDoc(Long sumDoc) {
        this.sumDoc = sumDoc;
    }

    public Date getDateDoc() {
        return dateDoc;
    }

    public void setDateDoc(Date dateDoc) {
        this.dateDoc = dateDoc;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }
}
