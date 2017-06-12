package it.polimi.ingsw.client.lightmodel;

import it.polimi.ingsw.gamelogic.cards.leader.LeaderCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerLight {
    private String name;
    private Map<GoodsLight, Integer> myGoods;
    private List<LeaderCard> myLeaders;
    private Map<GoodsLight, Integer> myBonusTile;
    private Map<PawnColorLight, Integer> myPawns;
    private List<DevelopmentCardLight> myTerritories;
    private List<DevelopmentCardLight> myBuildings;
    private List<DevelopmentCardLight> myCharacters;
    private List<DevelopmentCardLight> myVentures;

    public PlayerLight(String name,
                       Map<GoodsLight, Integer> myGoods,
                       List<LeaderCard> myLeaders,
                       Map<GoodsLight, Integer> myBonusTile,
                       Map<PawnColorLight, Integer> myPawns) {
        this.name = name;
        this.myGoods = myGoods;
        this.myLeaders = myLeaders;
        this.myBonusTile = myBonusTile;
        this.myPawns = myPawns;
        myTerritories = new ArrayList<>();
        myBuildings = new ArrayList<>();
        myCharacters = new ArrayList<>();
        myVentures = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<GoodsLight, Integer> getMyGoods() {
        return myGoods;
    }

    public void setMyGoods(Map<GoodsLight, Integer> myGoods) {
        this.myGoods = myGoods;
    }

    public List<LeaderCard> getMyLeaders() {
        return myLeaders;
    }

    public void setMyLeaders(List<LeaderCard> myLeaders) {
        this.myLeaders = myLeaders;
    }

    public Map<GoodsLight, Integer> getMyBonusTile() {
        return myBonusTile;
    }

    public void setMyBonusTile(Map<GoodsLight, Integer> myBonusTile) {
        this.myBonusTile = myBonusTile;
    }

    public Map<PawnColorLight, Integer> getMyPawns() {
        return myPawns;
    }

    public void setMyPawns(Map<PawnColorLight, Integer> myPawns) {
        this.myPawns = myPawns;
    }

    public List<DevelopmentCardLight> getMyTerritories() {
        return myTerritories;
    }

    public void setMyTerritories(List<DevelopmentCardLight> myTerritories) {
        this.myTerritories = myTerritories;
    }

    public List<DevelopmentCardLight> getMyBuildings() {
        return myBuildings;
    }

    public void setMyBuildings(List<DevelopmentCardLight> myBuildings) {
        this.myBuildings = myBuildings;
    }

    public List<DevelopmentCardLight> getMyCharacters() {
        return myCharacters;
    }

    public void setMyCharacters(List<DevelopmentCardLight> myCharacters) {
        this.myCharacters = myCharacters;
    }

    public List<DevelopmentCardLight> getMyVentures() {
        return myVentures;
    }

    public void setMyVentures(List<DevelopmentCardLight> myVentures) {
        this.myVentures = myVentures;
    }
}
