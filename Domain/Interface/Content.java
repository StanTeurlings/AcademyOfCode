package Domain.Interface;
import java.time.LocalDate;
import Domain.Enummeration.Status;

public interface Content {
    int getId();
    LocalDate getPublicationDate();
    Status getStatus();
    String getTitle();
    String getDescription();
}
