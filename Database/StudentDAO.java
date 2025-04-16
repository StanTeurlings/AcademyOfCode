package Database;

import Domain.Enummeration.Gender;
import Domain.Class.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDAO {

    private DatabaseConnection databaseConnection;

    // database connection
    public StudentDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    // get all students from the database
    public ObservableList<Student> getAllStudents() throws SQLException {
        ObservableList<Student> students = FXCollections.observableArrayList();
        String selectStmt = "SELECT * FROM Student";
        ResultSet rs = databaseConnection.executeSQLSelectStatement(selectStmt);

        while (rs.next()) {
            int id = rs.getInt("id");
            String email = rs.getString("email").trim();
            String name = rs.getString("name").trim();
            LocalDate birthDate = rs.getDate("birthDate").toLocalDate();
            Gender gender = Gender.valueOf(rs.getString("gender").trim().toUpperCase());
            String address = rs.getString("address").trim();
            String houseNumber = rs.getString("houseNumber").trim();
            String postalCode = rs.getString("postalCode").trim();
            String city = rs.getString("city").trim();
            String country = rs.getString("country").trim();

            Student student = new Student(email, name, birthDate, gender, houseNumber, address, postalCode, city,
                    country);
            student.setId(id);
            students.add(student);
        }
        return students;
    }

    // add a student to the database
    public void addStudent(Student student) {
        StringBuilder insertStmt = new StringBuilder();
        insertStmt.append(
                "INSERT INTO Student (email, name, birthDate, gender, adress, houseNumber, postalCode, city, country) ");
        insertStmt.append("VALUES ('");
        insertStmt.append(student.getEmail());
        insertStmt.append("', '");
        insertStmt.append(student.getName());
        insertStmt.append("', '");
        insertStmt.append(student.getBirthDate());
        insertStmt.append("', '");
        insertStmt.append(student.getGender().toString());
        insertStmt.append("', '");
        insertStmt.append(student.getAddress());
        insertStmt.append("', '");
        insertStmt.append(student.getHouseNumber());
        insertStmt.append("', '");
        insertStmt.append(student.getPostalCode());
        insertStmt.append("', '");
        insertStmt.append(student.getCity());
        insertStmt.append("', '");
        insertStmt.append(student.getCountry());

        insertStmt.append("')");

        databaseConnection.executeSQLUpdateStatement(insertStmt.toString());
    }

    // get progress of module for a student
    public ObservableList<String> getProgress(int studentId, int moduleId) throws SQLException {
        ObservableList<String> progress = FXCollections.observableArrayList();

        String selectStmt = """
                SELECT co.name, wc.percentageWatched
                FROM WatchedContent wc
                JOIN Content c ON wc.contentId = c.id
                JOIN Module m ON c.id = m.contentId
                JOIN Course co ON m.courseId = co.id
                WHERE wc.studentId = %d AND m.id = %d
                """.formatted(studentId, moduleId);

        ResultSet rs = databaseConnection.executeSQLSelectStatement(selectStmt);

        while (rs.next()) {
            String name = rs.getString("name").trim();
            double percentageWatched = rs.getDouble("percentageWatched");
            progress.add(name + ": " + String.format("%.2f", percentageWatched) + "% watched");
        }

        return progress;
    }

    // get progress of webcast for a student
    public ObservableList<String> getWebcastProgress(int studentId, int webcastId) throws SQLException {
        ObservableList<String> progress = FXCollections.observableArrayList();

        String selectStmt = """
                SELECT c.title, wc.percentageWatched
                FROM WatchedContent wc
                JOIN Content c ON wc.contentId = c.id
                JOIN Webcast w ON c.id = w.contentId
                WHERE wc.studentId = %d AND w.contentId = %d
                """.formatted(studentId, webcastId);

        ResultSet rs = databaseConnection.executeSQLSelectStatement(selectStmt);

        while (rs.next()) {
            String title = rs.getString("title").trim();
            double percentageWatched = rs.getDouble("percentageWatched");
            progress.add(title + ": " + String.format("%.2f", percentageWatched) + "% watched");
        }

        return progress;
    }
}
