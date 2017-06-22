package it.polimi.ingsw.client.lightmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerLight {
    private String name;
    private Map<GoodsLight, Integer> myGoods;
    private List<LeaderCardLight> myLeaders;
    private Map<GoodsLight, Integer> myProductionBonusTile;
    private Map<GoodsLight, Integer> myHarvestBonusTile;
    private Map<PawnColorLight, Integer> myPawns;
    private List<DevelopmentCardLight> myTerritories;
    private List<DevelopmentCardLight> myBuildings;
    private List<DevelopmentCardLight> myCharacters;
    private List<DevelopmentCardLight> myVentures;

    public PlayerLight(String name,
                       Map<GoodsLight, Integer> myGoods,
                       List<LeaderCardLight> myLeaders,
                       Map<GoodsLight, Integer> myProductionBonusTile,
                       Map<GoodsLight, Integer> myHarvestBonusTile,
                       Map<PawnColorLight, Integer> myPawns) {
        this.name = name;
        this.myGoods = myGoods;
        this.myLeaders = myLeaders;
        this.myProductionBonusTile = myProductionBonusTile;
        this.myHarvestBonusTile = myHarvestBonusTile;
        this.myPawns = myPawns;
        myTerritories = new ArrayList<>();
        myBuildings = new ArrayList<>();
        myCharacters = new ArrayList<>();
        myVentures = new ArrayList<>();
    }

    @Override
    public String toString() {
        return  "Goods: \n" + showAllMyGoods() +
                ", Leaders: \n" + showMyLeaders() +
                ", Production Bonus Tile: \n" + showExistingGoods(myProductionBonusTile) +
                ", Harvest Bonus Tile: \n" + showExistingGoods(myHarvestBonusTile) +
                ", Pawns: \n" + showMyPawns() +
                ", Territories: " + showCards(myTerritories) +
                ", Buildings: " + showCards(myBuildings) +
                ", Characters: " + showCards(myCharacters) +
                ", Ventures: " + showCards(myVentures);
    }

    private String showCards(List<DevelopmentCardLight> myCards) {
        String cardsToPrint = "";
        for (DevelopmentCardLight card : myCards) {
            cardsToPrint = cardsToPrint + card.toPlayerString();
        }
        return cardsToPrint;
    }

    private String showMyPawns() {
        String pawnsToPrint = "";
        if (myPawns.containsKey(PawnColorLight.BLACK))
            pawnsToPrint = pawnsToPrint + "Black : " + myPawns.get(PawnColorLight.BLACK) + ", ";
        if (myPawns.containsKey(PawnColorLight.WHITE))
            pawnsToPrint = pawnsToPrint + "White : " + myPawns.get(PawnColorLight.WHITE) + ", ";
        if (myPawns.containsKey(PawnColorLight.ORANGE))
            pawnsToPrint = pawnsToPrint + "Orange : " + myPawns.get(PawnColorLight.ORANGE) + ", ";
        if (myPawns.containsKey(PawnColorLight.NEUTRAL))
            pawnsToPrint = pawnsToPrint + "Neutral : " + myPawns.get(PawnColorLight.NEUTRAL);

        return pawnsToPrint + "\n";
    }

    private String showExistingGoods(Map<GoodsLight, Integer> myProductionBonusTile) {
        String goodsToPrint = "";
        if (myProductionBonusTile.get(GoodsLight.WOODS)!=0 || myProductionBonusTile.containsKey(GoodsLight.WOODS))
            goodsToPrint = goodsToPrint + "W " + myProductionBonusTile.get(GoodsLight.WOODS);
        if (myProductionBonusTile.get(GoodsLight.STONES)!=0 || myProductionBonusTile.containsKey(GoodsLight.STONES))
            goodsToPrint = goodsToPrint + ", S " + myProductionBonusTile.get(GoodsLight.STONES);
        if (myProductionBonusTile.get(GoodsLight.SERVANTS)!=0 || myProductionBonusTile.containsKey(GoodsLight.SERVANTS))
            goodsToPrint = goodsToPrint + ", SE " + myProductionBonusTile.get(GoodsLight.SERVANTS);
        if (myProductionBonusTile.get(GoodsLight.COINS)!=0 || myProductionBonusTile.containsKey(GoodsLight.COINS))
            goodsToPrint = goodsToPrint + ", C " + myProductionBonusTile.get(GoodsLight.COINS);
        if (myProductionBonusTile.get(GoodsLight.VICTORY_POINTS)!=0 ||
                                                myProductionBonusTile.containsKey(GoodsLight.VICTORY_POINTS))
            goodsToPrint = goodsToPrint + ", Vp " + myProductionBonusTile.get(GoodsLight.VICTORY_POINTS);
        if (myProductionBonusTile.get(GoodsLight.MILITARY_POINTS)!=0 ||
                                                        myProductionBonusTile.containsKey(GoodsLight.MILITARY_POINTS))
            goodsToPrint = goodsToPrint + ", Mp " + myProductionBonusTile.get(GoodsLight.MILITARY_POINTS);
        if (myProductionBonusTile.get(GoodsLight.FAITH_POINTS)!=0 ||
                                                            myProductionBonusTile.containsKey(GoodsLight.FAITH_POINTS))
            goodsToPrint = goodsToPrint + ", Fp " + myProductionBonusTile.get(GoodsLight.FAITH_POINTS);
        if (myProductionBonusTile.get(GoodsLight.COUNCIL_PRIVILEGE)!=0 ||
                                                        myProductionBonusTile.containsKey(GoodsLight.COUNCIL_PRIVILEGE))
            goodsToPrint = goodsToPrint + ", Cp " + myProductionBonusTile.get(GoodsLight.COUNCIL_PRIVILEGE);

        return goodsToPrint + "\n";
    }

    private String showMyLeaders() {
        String leadersToPrint = "";
        String singleLeader, str;
        LeaderCardLight leader;
        for (int i = 0; i < myLeaders.size(); i++) {
            leader = myLeaders.get(i);
            singleLeader = (i+1) + leader.getName() +"\n\tEffect:" + leader.getEffectDescription()
                    + "\n\tRequirements: {\n\t";
            for (int j=0; j < leader.getRequirements().size(); j++) {
                str = leader.getRequirements().get(j);
                singleLeader = singleLeader + str;
                if (j + 1 != leader.getRequirements().size())
                    singleLeader = singleLeader + ", ";
                else if ((j+1)%2 == 0)
                    singleLeader = singleLeader + ", \n\t";
            }
            leadersToPrint = leadersToPrint + singleLeader + "}\n\t";
        }
        return null;
    }

    private String showAllMyGoods() {
        String myGoodsToPrint;
        myGoodsToPrint = "W " + myGoods.get(GoodsLight.WOODS)+
                         ", S " + myGoods.get(GoodsLight.STONES)+
                         ", SE " + myGoods.get(GoodsLight.SERVANTS)+
                         ", C " + myGoods.get(GoodsLight.COINS)+
                         "\n\t, Vp " + myGoods.get(GoodsLight.VICTORY_POINTS)+
                         ", Mp " + myGoods.get(GoodsLight.MILITARY_POINTS)+
                         ", Fp " + myGoods.get(GoodsLight.FAITH_POINTS) + ".\n";
        return myGoodsToPrint;
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

    public List<LeaderCardLight> getMyLeaders() {
        return myLeaders;
    }

    public void setMyLeaders(List<LeaderCardLight> myLeaders) {
        this.myLeaders = myLeaders;
    }

    public Map<GoodsLight, Integer> getMyProductionBonusTile() {
        return myProductionBonusTile;
    }

    public void setMyProductionBonusTile(Map<GoodsLight, Integer> myHarvestBonusTile) {
        this.myHarvestBonusTile = myHarvestBonusTile;
    }

    public Map<GoodsLight, Integer> getMyHarvestBonusTile() {
        return myProductionBonusTile;
    }

    public void setMyHarvestBonusTile(Map<GoodsLight, Integer> myHarvestBonusTile) {
        this.myHarvestBonusTile = myHarvestBonusTile;
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
