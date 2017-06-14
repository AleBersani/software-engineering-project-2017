package it.polimi.ingsw.server.database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public final class QueryHandler {
    private final static Logger LOGGER = Logger.getLogger(QueryHandler.class.getName());
    private final static String PLAYER_NAME = "playerName";
    private final static String PASSWORD = "password";
    private final static String SCORE = "score";
    private final static String VICTORIES_NUMBER = "victoriesNumber";
    private final static String DEFEATS_NUMBER = "defeatsNumber";
    private final static String PLAY_TIME = "playTime";

    public static void addPlayer(String newPlayerName, String newPassword) throws SQLException {
        DBConnector.connect();
        Statement statement = DBConnector.getConnection().createStatement();
        statement.execute("INSERT INTO player (" + PLAYER_NAME + "," + PASSWORD + ") " +
                "VALUES('" + newPlayerName + "', '" + newPassword +"')");
        statement.close();
        DBConnector.closeConnection();
    }

    public static boolean isRegistered(String playerToCheck) throws SQLException {
        boolean isPresent = false;
        DBConnector.connect();
        Statement statement = DBConnector.getConnection().createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT COUNT(playerName) AS players " +
                        "FROM player " +
                        "WHERE " + PLAYER_NAME + " = '" + playerToCheck + "';");
        if (resultSet.next()) {
            isPresent = true;
        }
        resultSet.close();
        statement.close();
        DBConnector.closeConnection();
        return isPresent;
    }

    public static PlayerTable getPlayerByName(String playerToSearch) throws SQLException {
        PlayerTable playerTable = new PlayerTable();
        DBConnector.connect();
        Statement statement = DBConnector.getConnection().createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT * FROM player WHERE playerName = '" + playerToSearch + "';");
        if (resultSet.next()) {
            playerTable.setPlayerName(PLAYER_NAME);
            playerTable.setScore(resultSet.getInt(SCORE));
            playerTable.setVictoriesNumber(resultSet.getInt(VICTORIES_NUMBER));
            playerTable.setDefeatsNumber(resultSet.getInt(DEFEATS_NUMBER));
            playerTable.setPlayTime(resultSet.getInt(PLAY_TIME));
        }
        resultSet.close();
        statement.close();
        DBConnector.closeConnection();
        return playerTable;
    }
}
