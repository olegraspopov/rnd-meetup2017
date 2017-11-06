package data.ogm;

import javax.persistence.Entity;
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
    public Long getId() {
        return id;
    }
}
