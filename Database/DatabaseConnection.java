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

    try {
        if (connection == null || connection.isClosed()) {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=AcademyOfCode;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
            connection = DriverManager.getConnection(connectionUrl);
        }

        if (statement == null || statement.isClosed()) {
            statement = connection.createStatement();
        }

        System.out.println("database open");
        result = true;
    } catch (SQLException e) {
        System.out.println("[ERROR] SQL Fout in openConnection(): " + e.getMessage());
        result = false;
    } catch (ClassNotFoundException ex) {
        System.out.println("[ERROR] Driver niet gevonden: " + ex.getMessage());
    }

    return result;
}


    public boolean connectionIsOpen() {
        boolean open = false;

        if (connection != null && statement != null) {
            try {
                open = !connection.isClosed() && !statement.isClosed();
                System.out.println("[DEBUG] connectionIsOpen() = " + open);
            } catch (SQLException e) {
                System.out.println("[ERROR] Fout in connectionIsOpen(): " + e.getMessage());
                open = false;
            }
        } else {
            System.out.println("[ERROR] connection or statement is null!");
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

    public ResultSet executeSQLSelectStatement(String query) {
        ResultSet resultset = null;

        if (query != null && connectionIsOpen()) {
            try {
                resultset = statement.executeQuery(query);
            } catch (SQLException e) {
                System.out.println("[ERROR] Fout bij uitvoeren query: " + e.getMessage());
                resultset = null;
            }
        } else {
            System.out.println("[ERROR] Select statement niet uitgevoerd. connectionIsOpen() = false");
            System.out.println("[DEBUG] connection == null ? " + (connection == null));
            System.out.println("[DEBUG] statement == null ? " + (statement == null));
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