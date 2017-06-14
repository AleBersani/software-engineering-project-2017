package it.polimi.ingsw.server.database;

import java.sql.*;

public class DBConnector {
    private final static String URL = "jdbc:sqlite:./resources/server/lollolm02.db";
    private final static String USERNAME = "root";

    private static Connection connection;

    public static void main(String argv[]) {
        DBConnector.connect();
        /*try {
            ResultSet resultSet = DBHandler.executeQuery("SELECT * FROM player WHERE playerName = 'Dennis';");
            resultSet.last();
            System.out.println(resultSet.getString("playerName"));
            resultSet.close();
            DBHandler.closeStatement();
        } catch (SQLException e) {
            System.err.println("Query Error");
        }*/
    }

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL, USERNAME, "");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Cannot close connection");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
