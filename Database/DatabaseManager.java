package Database;

import Domain.Class.Student;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// DatabaseManager class is a singleton that manages the connection to the database and provides methods to interact with various DAO classes.
public class DatabaseManager {

    private static DatabaseManager instance;
    private DatabaseConnection dbc;
    private StudentDAO sd;
    private EnrollmentDAO ed;
    private CourseDAO cd;
    private WebcastDAO wd;

    private DatabaseManager() {
        this.dbc = new DatabaseConnection();
        this.sd = new StudentDAO(this.dbc);
        this.ed = new EnrollmentDAO(this.dbc);
        this.cd = new CourseDAO(this.dbc);
        this.wd = new WebcastDAO(this.dbc);
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    // open the connection to the database
    public boolean openConnection() {
        if (this.dbc.openConnection()) {
            return true;
        } else {
            showErrorAlert("Error in openConnection", "Connection to the database failed.");
            return false;
        }
    }

    // close the connection to the database
    public void closeConnection() {
        dbc.closeConnection();
    }

    // show error alert
    public void showErrorAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // student methods

    // get all students
    public ObservableList<Student> getAllStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        try {
            students = sd.getAllStudents();
        } catch (SQLException e) {
            showErrorAlert("Error in getAllStudents", e.getMessage());
        }

        return students;
    }

    // add student
    public void addStudent(Student student) {
        sd.addStudent(student);
    }

    // get progress of module for a student
    public ObservableList<String> getModuleProgressForStudent(int studentId, int moduleId) {
        ObservableList<String> progress = FXCollections.observableArrayList();
        try {
            progress = cd.getModuleProgressForStudent(studentId, moduleId);
        } catch (SQLException e) {
            showErrorAlert("Error in getModuleProgressForStudent", e.getMessage());
        }
        return progress;
    }

    // get progress of webcast for a student
    public ObservableList<String> getWebcastProgressForStudent(int studentId, int webcastId) {
        ObservableList<String> progress = FXCollections.observableArrayList();
        try {
            progress = sd.getWebcastProgress(studentId, webcastId);
        } catch (SQLException e) {
            showErrorAlert("Error in getWebcastProgressForStudent", e.getMessage());
        }
        return progress;
    }

    // course methods
    // get avarage watched content of modules in course
    public ObservableList<String> getModuleProgressForCourse(int courseId) {
        ObservableList<String> progress = FXCollections.observableArrayList();
        try {
            progress = cd.getModuleProgressForCourse(courseId);
        } catch (SQLException e) {
            showErrorAlert("Error in getModuleProgressForCourse", e.getMessage());
        }
        return progress;
    }

    // get watched content of every module for a course on a specific student
    public ObservableList<String> getModuleProgressForCourseAndStudent(int courseId, int studentId) {
        ObservableList<String> progress = FXCollections.observableArrayList();
        try {
            progress = cd.getModuleProgressForStudent(courseId, studentId);
        } catch (SQLException e) {
            showErrorAlert("Error in getModuleProgressForCourseAndStudent", e.getMessage());
        }
        return progress;
    }

    // count student in course
    public int countStudentsInCourse(int courseId) {
        int count = 0;
        try {
            count = cd.countStudentsInCourse(courseId);
        } catch (SQLException e) {
            showErrorAlert("Error in countStudentsInCourse", e.getMessage());
        }
        return count;
    }

    // Enrollment methods
    // add enrollment to database
    public void addEnrollment(int studentId, int courseId) {
        ed.addEnrollment(studentId, courseId);
    }

    // webcast methods
    // get top 3 webcasts
    public ObservableList<String> getTop3Webcasts() {
        ObservableList<String> webcasts = FXCollections.observableArrayList();
        try {
            webcasts = wd.getTop3Webcasts();
        } catch (SQLException e) {
            showErrorAlert("Error in getTop3Webcasts", e.getMessage());
        }
        return webcasts;
    }
}
