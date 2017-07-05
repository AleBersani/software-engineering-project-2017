package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.model.enums.PointsLight;
import it.polimi.ingsw.shared.model.GeneralColor;

import java.util.List;
import java.util.Map;

public class PlayerLight {
    private String playerName;
    private GeneralColor playerColor;

    private List<Card> activatedLeaders;
    private Map<PointsLight, Integer> numberOfPoints;

    public PlayerLight(String playerName, GeneralColor playerColor) {
        this.playerName = playerName;
        this.playerColor = playerColor;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public GeneralColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(GeneralColor playerColor) {
        this.playerColor = playerColor;
    }

    public List<Card> getActivatedLeaders() {
        return activatedLeaders;
    }

    public void setActivatedLeaders(List<Card> activatedLeaders) {
        this.activatedLeaders = activatedLeaders;
    }

    public Map<PointsLight, Integer> getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Map<PointsLight, Integer> numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }
}
