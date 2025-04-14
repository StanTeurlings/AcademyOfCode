package Domain.Class;

public class WatchedContent {
    private Student student;
    private Content content;
    private int percentageWatched;

    public WatchedContent(Student student, Content content, int percentageWatched) {
        this.student = student;
        this.content = content;
        this.percentageWatched = percentageWatched;
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
