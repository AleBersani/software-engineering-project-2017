package it.polimi.ingsw.server.database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class QueryHandler {
    private final static Logger LOGGER = Logger.getLogger(QueryHandler.class.getName());

    private final static String ID_GAME = "idGame";

    private final static String PLAYER_NAME = "playerName";
    private final static String PSW = "password";
    private final static String SCORE = "score";
    private final static String VICTORIES_NUMBER = "victoriesNumber";
    private final static String DEFEATS_NUMBER = "defeatsNumber";
    private final static String PLAY_TIME = "playTime";

    private DBConnector dbConnector;

    public QueryHandler() {
        dbConnector = new DBConnector();
    }

    public boolean authenticate(String playerName, String psw) {
        boolean isAuthenticated = false;
        String query = "SELECT COUNT(playerName) AS players " +
                "FROM player " +
                "WHERE " + PLAYER_NAME + " = '" + playerName + "' AND " + PSW + "= '" + psw + "';";
        dbConnector.connect();
        try (Statement statement = dbConnector.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    if (resultSet.getInt("players") == 1) {
                        isAuthenticated = true;
                    }
                }
                resultSet.close();
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Cannot authenticate player", e);
        }
        dbConnector.closeConnection();
        return isAuthenticated;
    }

    public boolean addPlayer(String newPlayerName, String newPassword) {
        String query = "INSERT INTO player (" + PLAYER_NAME + "," + PSW + ") " +
                "VALUES('" + newPlayerName + "', '" + newPassword +"')";
        dbConnector.connect();
        try (Statement statement = dbConnector.getConnection().createStatement()) {
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Cannot add player", e);
            return false;
        }
        dbConnector.closeConnection();
        return true;
    }

    public boolean addGame() {
        String query = "INSERT INTO game DEFAULT VALUES;";
        dbConnector.connect();
        try (Statement statement = dbConnector.getConnection().createStatement()) {
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Cannot add game", e);
            return false;
        }
        dbConnector.closeConnection();
        return true;
    }

    public int getLastGameID() {
        int idGame = 0;
        String query = "SELECT * FROM game ORDER BY " + ID_GAME + " DESC LIMIT 1";
        dbConnector.connect();
        try (Statement statement = dbConnector.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    idGame = resultSet.getInt(ID_GAME);
                }
                resultSet.close();
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Cannot get last game ID", e);
        }
        dbConnector.closeConnection();
        return idGame;
    }

    public boolean addPlayerToGame(String playerName, int gameId) {
        String query = "INSERT INTO game_player (gameID, playerID) VALUES('" + gameId + "', '" + playerName + "');";
        dbConnector.connect();
        try (Statement statement = dbConnector.getConnection().createStatement()) {
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Cannot add player to game", e);
            return false;
        }
        dbConnector.closeConnection();
        return true;
    }

    public PlayerTable getPlayerByName(String playerToSearch) {
        PlayerTable playerTable = new PlayerTable();
        String query = "SELECT * FROM player WHERE " + PLAYER_NAME + " = '" + playerToSearch + "';";
        dbConnector.connect();
        try (Statement statement = dbConnector.getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    playerTable.setPlayerName(resultSet.getString(PLAYER_NAME));
                    playerTable.setScore(resultSet.getInt(SCORE));
                    playerTable.setVictoriesNumber(resultSet.getInt(VICTORIES_NUMBER));
                    playerTable.setDefeatsNumber(resultSet.getInt(DEFEATS_NUMBER));
                    playerTable.setPlayTime(resultSet.getInt(PLAY_TIME));
                }
                resultSet.close();
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Cannot get player", e);
        }
        dbConnector.closeConnection();
        return playerTable;
    }
}
