package Domain.Class;

import java.time.LocalDate;
import Domain.Enummeration.Status;

public class Module extends Content {
    private int id;
    private String version;
    private String contactPersonName;
    private String contactPersonEmail;
    private int sequenceNumber;

    public Module(int id, String title, String description, Status status, LocalDate publicationDate, String version, String contactPersonName, String contactPersonEmail, int sequenceNumber) {
        super(id, title, description, status, publicationDate);
        this.id = id;
        this.version = version;
        this.contactPersonName = contactPersonName;
        this.contactPersonEmail = contactPersonEmail;
        this.sequenceNumber = sequenceNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
