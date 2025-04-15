package Domain.Class;

public class WatchedContent {
    private int id;
    private Student student;
    private Content content;
    private int percentageWatched;

    public WatchedContent(int id, Student student, Content content, int percentageWatched) {
        this.id = id;
        this.student = student;
        this.content = content;
        this.percentageWatched = percentageWatched;
    }

    public int getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Content getContent() {
        return content;
    }

    public int getPercentageWatched() {
        return percentageWatched;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public void setPercentageWatched(int percentageWatched) {
        this.percentageWatched = percentageWatched;
    }
}
