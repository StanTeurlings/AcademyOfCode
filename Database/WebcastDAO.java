package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

import Domain.Class.Webcast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WebcastDAO {

    DatabaseConnection databaseConnection;

    // database connection
    public WebcastDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    // get all webcasts
    public ObservableList<Webcast> getAllWebcasts() throws SQLException {
        databaseConnection.openConnection();
        ObservableList<Webcast> webcasts = FXCollections.observableArrayList();
        String selectStmt = "SELECT * FROM Webcast JOIN Content ON Webcast.contentId = Content.id";
        ResultSet rs = databaseConnection.executeSQLSelectStatement(selectStmt);

        while (rs.next()) {
            String title = rs.getString("title").trim();

            Webcast webcast = new Webcast(title);
            webcast.setId(rs.getInt("id"));
            webcasts.add(webcast);
        }
        databaseConnection.closeConnection();
        return webcasts;
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