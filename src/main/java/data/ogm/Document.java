package data.ogm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Document {
    private Long id;
    private Long debetAccount;
    private Long creditAccount;
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

    public Document(Long debetAccount, Long creditAccount, Long sumDoc, Date dateDoc, String aim) {
        this.debetAccount = debetAccount;
        this.creditAccount = creditAccount;
        this.sumDoc = sumDoc;
        this.dateDoc = dateDoc;
        this.aim = aim;
    }

    public Long getDebetAccount() {
        return debetAccount;
    }

    public void setDebetAccount(Long debetAccount) {
        this.debetAccount = debetAccount;
    }

    public Long getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Long creditAccount) {
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
