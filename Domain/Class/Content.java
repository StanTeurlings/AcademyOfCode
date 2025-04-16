package Domain.Class;

import java.time.LocalDate;
import Domain.Enummeration.Status;

public abstract class Content {
    private int id;
    private String title;
    private String description;
    private Status status;
    private LocalDate publicationDate;

    public Content(int id, String title, String description, Status status, LocalDate publicationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.publicationDate = publicationDate;
    }

    public Content(String title, String description, Status status, LocalDate publicationDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.publicationDate = publicationDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
