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
        // TODO
    }
}
