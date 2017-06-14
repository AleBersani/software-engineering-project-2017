package it.polimi.ingsw.server.database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class QueryHandler {
    private final static Logger LOGGER = Logger.getLogger(QueryHandler.class.getName());
    private final static String PLAYER_NAME = "playerName";
    private final static String PSW = "password";
    private final static String SCORE = "score";
    private final static String VICTORIES_NUMBER = "victoriesNumber";
    private final static String DEFEATS_NUMBER = "defeatsNumber";
    private final static String PLAY_TIME = "playTime";

    public static void addPlayer(String newPlayerName, String newPassword) {
        String query = "INSERT INTO player (" + PLAYER_NAME + "," + PSW + ") " +
                "VALUES('" + newPlayerName + "', '" + newPassword +"')";
        DBConnector.connect();
        try (Statement statement = DBConnector.getConnection().createStatement()) {
            statement.execute(query);
            statement.close();
            DBConnector.getConnection().close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Cannot add player", e);
        }
    }

    public static boolean isRegistered(String playerToCheck) {
        boolean isPresent = false;
        String query = "SELECT COUNT(playerName) AS players " +
                "FROM player " +
                "WHERE " + PLAYER_NAME + " = '" + playerToCheck + "';";
        DBConnector.connect();
        try (Statement statement = DBConnector.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    isPresent = true;
                }
                resultSet.close();
                statement.close();
                DBConnector.getConnection().close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Cannot check if player is registered", e);
        }
        return isPresent;
    }

    public static PlayerTable getPlayerByName(String playerToSearch) {
        PlayerTable playerTable = new PlayerTable();
        String query = "SELECT * FROM player WHERE playerName = '" + playerToSearch + "';";
        DBConnector.connect();
        try (Statement statement = DBConnector.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    playerTable.setPlayerName(PLAYER_NAME);
                    playerTable.setScore(resultSet.getInt(SCORE));
                    playerTable.setVictoriesNumber(resultSet.getInt(VICTORIES_NUMBER));
                    playerTable.setDefeatsNumber(resultSet.getInt(DEFEATS_NUMBER));
                    playerTable.setPlayTime(resultSet.getInt(PLAY_TIME));
                }
                resultSet.close();
                statement.close();
                DBConnector.getConnection().close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Cannot get player", e);
        }
        return playerTable;
    }
}
