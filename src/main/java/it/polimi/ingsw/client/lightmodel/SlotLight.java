package it.polimi.ingsw.client.lightmodel;

import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SlotLight {
    private BoardIdentifier boardIdentifier;
    private Map<GoodsLight, Integer> bonus;
    private int cost;
    private DevelopmentCardLight developmentCard;
    private Map<String, PawnColorLight> presentPawn;
    private int malus;

    public SlotLight(Map<GoodsLight, Integer> bonus, int cost, DevelopmentCardLight developmentCard,
                     Map<String, PawnColorLight> presentPawn, int malus) {
        this.bonus = bonus;
        this.cost = cost;
        this.developmentCard = developmentCard;
        this.presentPawn = presentPawn;
        this.malus = malus;
    }

    public SlotLight(Map<GoodsLight, Integer> bonus, int cost, DevelopmentCardLight developmentCard,
                     Map<String, PawnColorLight> presentPawn) {
        this.bonus = bonus;
        this.cost = cost;
        this.developmentCard = developmentCard;
        this.presentPawn = presentPawn;
        malus = 0;
    }

    public SlotLight(BoardIdentifier boardIdentifier, Map<GoodsLight, Integer> bonus, int cost, int malus) {
        this.boardIdentifier = boardIdentifier;
        this.bonus = bonus;
        this.cost = cost;
        this.malus = malus;
        presentPawn = new HashMap<>();
        developmentCard = new DevelopmentCardLight();
    }

    @Override
    public String toString() {
        return  "Board Identifier: " + boardIdentifier.toString() +
                "\nBonus: " + showBonus() +
                ",\nCost: " + cost +
                ",\ndevelopmentCard: " + developmentCard.toBoardString() +
                ",\npresentPawn=" + presentPawn.toString() +
                ",\nmalus=" + malus;
    }

    private String showBonus() {
        String goodsToPrint = "";
        if (bonus.get(GoodsLight.WOODS)!=0 || bonus.containsKey(GoodsLight.WOODS))
            goodsToPrint = goodsToPrint + "W " + bonus.get(GoodsLight.WOODS);
        if (bonus.get(GoodsLight.STONES)!=0 || bonus.containsKey(GoodsLight.STONES))
            goodsToPrint = goodsToPrint + ", S " + bonus.get(GoodsLight.STONES);
        if (bonus.get(GoodsLight.SERVANTS)!=0 || bonus.containsKey(GoodsLight.SERVANTS))
            goodsToPrint = goodsToPrint + ", SE " + bonus.get(GoodsLight.SERVANTS);
        if (bonus.get(GoodsLight.COINS)!=0 || bonus.containsKey(GoodsLight.COINS))
            goodsToPrint = goodsToPrint + ", C " + bonus.get(GoodsLight.COINS);
        if (bonus.get(GoodsLight.MILITARY_POINTS)!=0 ||
                bonus.containsKey(GoodsLight.MILITARY_POINTS))
            goodsToPrint = goodsToPrint + ", Mp " + bonus.get(GoodsLight.MILITARY_POINTS);
        if (bonus.get(GoodsLight.VICTORY_POINTS)!=0 ||
                bonus.containsKey(GoodsLight.VICTORY_POINTS))
            goodsToPrint = goodsToPrint + ", Vp " + bonus.get(GoodsLight.VICTORY_POINTS);
        if (bonus.get(GoodsLight.FAITH_POINTS)!=0 ||
                bonus.containsKey(GoodsLight.FAITH_POINTS))
            goodsToPrint = goodsToPrint + ", Fp " + bonus.get(GoodsLight.FAITH_POINTS);
        if (bonus.get(GoodsLight.COUNCIL_PRIVILEGE)!=0 ||
                bonus.containsKey(GoodsLight.COUNCIL_PRIVILEGE))
            goodsToPrint = goodsToPrint + ", Cp " + bonus.get(GoodsLight.COUNCIL_PRIVILEGE);

        return goodsToPrint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SlotLight that = (SlotLight) o;
        return  Objects.equals(getBoardIdentifier(), that.getBoardIdentifier()) &&
                Objects.equals(getBonus(), that.getBonus()) &&
                Objects.equals(getCost(), that.getCost()) &&
                Objects.equals(getDevelopmentCard(), that.getDevelopmentCard()) &&
                Objects.equals(getPresentPawn(), that.getPresentPawn()) &&
                Objects.equals(getMalus(), that.getMalus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoardIdentifier(), getBonus(), getCost(), getDevelopmentCard(), getPresentPawn(), getMalus());
    }

    public BoardIdentifier getBoardIdentifier() {
        return boardIdentifier;
    }

    public void setBoardIdentifier(BoardIdentifier boardIdentifier) {
        this.boardIdentifier = boardIdentifier;
    }

    public Map<GoodsLight, Integer> getBonus() {
        return bonus;
    }

    public void setBonus(Map<GoodsLight, Integer> bonus) {
        this.bonus = bonus;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public DevelopmentCardLight getDevelopmentCard() {
        return developmentCard;
    }

    public void setDevelopmentCard(DevelopmentCardLight developmentCard) {
        this.developmentCard = developmentCard;
    }

    public Map<String, PawnColorLight> getPresentPawn() {
        return presentPawn;
    }

    public void setPresentPawn(Map<String, PawnColorLight> presentPawn) {
        this.presentPawn = presentPawn;
    }

    public int getMalus() {
        return malus;
    }

    public void setMalus(int malus) {
        this.malus = malus;
    }
}
