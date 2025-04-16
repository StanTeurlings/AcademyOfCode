package Database;

import Domain.Class.Course;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseDAO {
    private DatabaseConnection databaseConnection;

    // database connection
    public CourseDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    // get all courses from the database
    public ObservableList<Course> getAllCourses() throws SQLException {
        databaseConnection.openConnection();
        ObservableList<Course> courses = FXCollections.observableArrayList();
        String selectStmt = "SELECT * FROM Course";
        ResultSet rs = databaseConnection.executeSQLSelectStatement(selectStmt);

        while (rs.next()) {
            String name = rs.getString("name").trim();

            Course course = new Course(name);
            course.setId(rs.getInt("id"));
            courses.add(course);
        }

        databaseConnection.closeConnection();
        return courses;
    }

    // get avarage watched content of modules in course
    public ObservableList<String> getModuleProgressForCourse(int courseId) throws SQLException {
        ObservableList<String> progress = FXCollections.observableArrayList();

        String selectStmt = """
                SELECT m.id AS moduleId, c.title AS contentTitle, AVG(wc.percentageWatched) AS avgWatched
                FROM Module m
                JOIN Content c ON m.contentId = c.id
                LEFT JOIN WatchedContent wc ON wc.contentId = c.id
                WHERE m.courseId = %d
                GROUP BY m.id, c.title
                """.formatted(courseId);

        ResultSet rs = databaseConnection.executeSQLSelectStatement(selectStmt);

        while (rs.next()) {
            int moduleId = rs.getInt("moduleId");
            String contentTitle = rs.getString("contentTitle").trim();
            double avgWatched = rs.getDouble("avgWatched");

            progress.add("Module " + moduleId + " (" + contentTitle + "): " + String.format("%.2f", avgWatched)
                    + "% watched");
        }

        return progress;
    }

    // get watched content of every module for a course on a specific student
    public ObservableList<String> getModuleProgressForStudent(int courseId, int studentId) throws SQLException {
        ObservableList<String> progress = FXCollections.observableArrayList();

        String selectStmt = """
                SELECT m.id AS moduleId, c.title AS contentTitle, AVG(wc.percentageWatched) AS avgWatched
                FROM Module m
                JOIN Content c ON m.contentId = c.id
                LEFT JOIN WatchedContent wc ON wc.contentId = c.id AND wc.studentId = %d
                WHERE m.courseId = %d
                GROUP BY m.id, c.title
                """.formatted(studentId, courseId);

        ResultSet rs = databaseConnection.executeSQLSelectStatement(selectStmt);

        while (rs.next()) {
            int moduleId = rs.getInt("moduleId");
            String contentTitle = rs.getString("contentTitle").trim();
            double avgWatched = rs.getDouble("avgWatched");

            progress.add("Module " + moduleId + " (" + contentTitle + "): " + String.format("%.2f", avgWatched)
                    + "% watched");
        }

        return progress;
    }

    // count students who have completed the course (100% watched all module
    // content)
    public int countStudentsInCourse(int courseId) throws SQLException {
        String selectStmt = """
                SELECT COUNT(*) AS completedStudentCount
                FROM Enrollment e
                JOIN Student s ON s.id = e.studentId
                WHERE e.courseId = %d
                AND NOT EXISTS (
                    SELECT *
                    FROM Module m
                    JOIN Content c ON m.contentId = c.id
                    LEFT JOIN WatchedContent wc
                        ON wc.contentId = c.id AND wc.studentId = s.id
                    WHERE m.courseId = %d
                    AND (wc.percentageWatched IS NULL OR wc.percentageWatched < 100)
                )
                """.formatted(courseId, courseId);

        ResultSet rs = databaseConnection.executeSQLSelectStatement(selectStmt);
        rs.next();
        return rs.getInt("completedStudentCount");
    }
}
