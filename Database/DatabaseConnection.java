package Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private Connection connection;
    private Statement statement;

    // database connection
    public DatabaseConnection() {
        connection = null;
        statement = null;
    }

    // Open connection with the database
    public boolean openConnection() {
        boolean result = false;

        if (connection == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionUrl = "jdbc:sqlserver://localhost;databaseName=AcademyOfCode;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
                connection = DriverManager.getConnection(connectionUrl);

                if (connection != null) {
                    statement = connection.createStatement();
                }
                System.out.println("database open");
                result = true;
            } catch (SQLException e) {
                System.out.println(e);
                result = false;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            result = true;
        }
        return result;
    }

    // the connection is open
    public boolean connectionIsOpen() {
        boolean open = false;

        if (connection != null && statement != null) {
            try {
                open = !connection.isClosed() && !statement.isClosed();
            } catch (SQLException e) {
                System.out.println(e);
                open = false;
            }
        }

        return open;
    }

    // close the connection
    public void closeConnection() {
        try {
            statement.close();
            connection.close();
            System.out.println("connection = closed");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // sql statement to select data
    public ResultSet executeSQLSelectStatement(String query) {
        ResultSet resultset = null;
        if (query != null && connectionIsOpen()) {
            try {
                resultset = statement.executeQuery(query);
            } catch (SQLException e) {
                System.out.println(e);
                resultset = null;
            }
        }
        return resultset;
    }

    // sql statement to update data
    public boolean executeSQLUpdateStatement(String query) {
        boolean result = false;

        if (query != null && connectionIsOpen()) {
            try {
                statement.executeUpdate(query);
                result = true;
            } catch (SQLException e) {
                System.out.println(e);
                result = false;
            }
        }
        return result;
    }
}