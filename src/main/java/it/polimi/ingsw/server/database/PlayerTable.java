package it.polimi.ingsw.server.database;

public class PlayerTable {
    private String playerName;
    private int score;
    private int victoriesNumber;
    private int defeatsNumber;
    private int playTime;

    public PlayerTable() {
        playerName = "";
        score = 0;
        victoriesNumber = 0;
        defeatsNumber = 0;
        playTime = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getVictoriesNumber() {
        return victoriesNumber;
    }

    public void setVictoriesNumber(int victoriesNumber) {
        this.victoriesNumber = victoriesNumber;
    }

    public int getDefeatsNumber() {
        return defeatsNumber;
    }

    public void setDefeatsNumber(int defeatsNumber) {
        this.defeatsNumber = defeatsNumber;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }
}
