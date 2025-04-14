package Domain.Class;

import java.time.LocalDate;
import java.time.LocalTime;
import Domain.Enummeration.Status;

public class Webcast extends Content {
    private int id;
    private LocalTime duration;
    private String url;
    private String presenterName;
    private String presenterOrganisation;

    public Webcast(int id, String title, String description, Status status, LocalDate publicationDate, LocalTime duration, String url, String presenterName, String presenterOrganisation) {
        super(id, title, description, status, publicationDate);
        this.id = id;
        this.duration = duration;
        this.url = url;
        this.presenterName = presenterName;
        this.presenterOrganisation = presenterOrganisation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPresenterName() {
        return presenterName;
    }

    public void setPresenterName(String presenterName) {
        this.presenterName = presenterName;
    }

    public String getPresenterOrganisation() {
        return presenterOrganisation;
    }

    public void setPresenterOrganisation(String presenterOrganisation) {
        this.presenterOrganisation = presenterOrganisation;
    }
}
