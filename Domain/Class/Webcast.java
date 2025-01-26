package Domain.Class;
import java.time.LocalDate;
import java.time.LocalTime;
import Domain.Enummeration.Status;
import Domain.Interface.Content;

public class Webcast implements Content {
    private int id;
    private LocalDate publicationDate;
    private Status status;
    private String title;
    private String description;
    private LocalTime duration;
    private String url;
    private String presenterName;
    private String presenterOrganisation;

    public Webcast(int id, LocalDate publicationDate, Status status, String title, String description, LocalTime duration, String url, String presenterName, String presenterOrganisation) {
        this.id = id;
        this.publicationDate = publicationDate;
        this.status = status;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.url = url;
        this.presenterName = presenterName;
        this.presenterOrganisation = presenterOrganisation;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public String getUrl() {
        return url;
    }

    public String getPresenterName() {
        return presenterName;
    }

    public String getPresenterOrganisation() {
        return presenterOrganisation;
    }
}
