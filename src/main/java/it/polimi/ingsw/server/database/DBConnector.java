package it.polimi.ingsw.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DBConnector {
    private static final Logger LOGGER = Logger.getLogger(DBConnector.class.getName());
    private static final String URL = "jdbc:sqlite:./resources/server/lollolm02.db";
    private static final String USERNAME = "root";

    private Connection connection;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL, USERNAME, "");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.SEVERE, "An exception while connecting to the database was thrown", e);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Cannot close the connection", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
