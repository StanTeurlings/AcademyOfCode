package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WebcastDAO {

    DatabaseConnection databaseConnection;

    // database connection
    public WebcastDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    
    
    // get top 3 webcasts
    public ObservableList<String> getTop3Webcasts() throws SQLException {
        databaseConnection.openConnection();
        ObservableList<String> webcasts = FXCollections.observableArrayList();


        String selectStmt = """
                SELECT TOP 3
                    w.id,
                    c.title,
                    COUNT(DISTINCT wc.studentId) AS studentCount
                FROM Webcast w
                JOIN Content c ON w.contentId = c.id
                LEFT JOIN WatchedContent wc ON wc.contentId = c.id
                GROUP BY w.id, c.title
                ORDER BY studentCount DESC
                """;

        ResultSet rs = databaseConnection.executeSQLSelectStatement(selectStmt);

        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title").trim();
            int studentCount = rs.getInt("studentCount");

            webcasts.add("Webcast " + id + " (" + title + "): " + studentCount + " students watched");
        }

        databaseConnection.closeConnection();
        return webcasts;
    }
}