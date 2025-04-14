package Domain.Class;
import java.time.LocalDate;

public class Enrollment {
    private int id;
    private Student student;
    private Course course;
    private LocalDate enrollmentDate;

    public Enrollment(int id, Student student, Course course, LocalDate enrollmentDate) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
}
