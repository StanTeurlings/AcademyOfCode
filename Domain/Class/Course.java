package Domain.Class;
import java.util.List;
import Domain.Enummeration.Difficulty;

public class Course {
    private String name;
    private String subject;
    private String introductionText;
    private Difficulty difficulty;
    private List<String> recommendedCourses;

    public Course(String name, String subject, String introductionText, Difficulty difficulty, List<String> recommendedCourses) {
        this.name = name;
        this.subject = subject;
        this.introductionText = introductionText;
        this.difficulty = difficulty;
        this.recommendedCourses = recommendedCourses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIntroductionText() {
        return introductionText;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getRecommendedCourses() {
        return recommendedCourses;
    }

    public void setRecommendedCourses(List<String> recommendedCourses) {
        this.recommendedCourses = recommendedCourses;
    }
}