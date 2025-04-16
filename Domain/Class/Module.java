package Domain.Class;

import java.time.LocalDate;
import Domain.Enummeration.Status;

public class Module extends Content {
    private int id;
    private Course course;
    private String version;
    private int contactPersonId;
    private String contactPersonName;
    private String contactPersonEmail;
    private int sequenceNumber;

    public Module(int id, Course course, String title, String description, Status status, LocalDate publicationDate, String version, int contactPersonId, String contactPersonName, String contactPersonEmail, int sequenceNumber) {
        super(id, title, description, status, publicationDate);
        this.id = id;
        this.version = version;
        this.course = course;
        this.contactPersonId = contactPersonId;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(int contactPersonId) {
        this.contactPersonId = contactPersonId;
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
