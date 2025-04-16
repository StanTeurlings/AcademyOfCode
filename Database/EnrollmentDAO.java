package Database;

public class EnrollmentDAO {
    private DatabaseConnection databaseConnection;

    // database connection
    public EnrollmentDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    // add enrollment to database
    public void addEnrollment(int studentId, int courseId) {
        databaseConnection.openConnection();

        StringBuilder insertStmt = new StringBuilder();
        insertStmt.append("INSERT INTO Enrollment (studentId, courseId, enrollmentDate) ");
        insertStmt.append("VALUES (");
        insertStmt.append(studentId);
        insertStmt.append(", ");
        insertStmt.append(courseId);
        insertStmt.append(", ");
        insertStmt.append("GETDATE()"); // Use GETDATE() for current date in SQL Server
        insertStmt.append(")");
        databaseConnection.executeSQLUpdateStatement(insertStmt.toString());

        databaseConnection.closeConnection();
    }
}
