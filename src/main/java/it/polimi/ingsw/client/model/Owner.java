package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.model.enums.ResourcesLight;

import java.util.List;
import java.util.Map;

public class Owner {
    private PlayerLight playerLight;

    private String bonusTileIdentifier;
    private List<Card> territories;
    private List<Card> buildings;
    private List<Card> characters;
    private List<Card> ventures;
    private List<Card> leader;
    private Map<ResourcesLight, Integer> numberOfResources;

    public Owner(PlayerLight playerLight) {
        this.playerLight = playerLight;
        // TODO
    }
}
