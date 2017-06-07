package it.polimi.ingsw.server.database;

import java.sql.*;

public class DBHandler {
    private final static String URL = "jdbc:mysql://localhost:3306/" +
            "lollothemagnificent?useLegacyDatetimeCode=false&serverTimezone=Europe/Rome";
    private final static String USERNAME = "root";

    private static Connection connection;
    private static Statement statement;

    public static void main(String argv[]) {
        DBHandler.connect();
        try {
            ResultSet resultSet = DBHandler.executeQuery("SELECT * FROM Users WHERE usersID = 1;");
            resultSet.last();
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("password"));
            resultSet.close();
            DBHandler.closeStatement();
        } catch (SQLException e) {
            System.err.println("Query Error");
        }
    }

    public static void connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, "");
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public static void closeStatement() {
        try {
            statement.close();
        } catch (SQLException e) {
            System.err.println("Cannot close statement");
        }
    }
}
