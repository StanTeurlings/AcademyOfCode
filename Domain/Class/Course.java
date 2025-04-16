package Domain.Class;

import java.util.List;
import Domain.Enummeration.Difficulty;

public class Course {
    private int id;
    private String name;
    private String subject;
    private String introductionText;
    private Difficulty difficulty;
    private List<Course> recommendedCourses;

    public Course(int id, String name, String subject, String introductionText, Difficulty difficulty,
            List<String> recommendedCourses) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.introductionText = introductionText;
        this.difficulty = difficulty;
    }

    public Course(String name) {
        this.name = name;
        this.subject = null;
        this.introductionText = null;
        this.difficulty = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Course> getRecommendedCourses() {
        return recommendedCourses;
    }

    public void setRecommendedCourses(List<Course> recommendedCourses) {
        this.recommendedCourses = recommendedCourses;
    }
    
    @Override
    public String toString() {
        return name; 
    }
    
}