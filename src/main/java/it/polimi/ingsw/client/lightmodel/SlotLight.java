package it.polimi.ingsw.client.lightmodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SlotLight {
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

    public SlotLight(Map<GoodsLight, Integer> bonus, int cost, int malus) {
        this.bonus = bonus;
        this.cost = cost;
        this.malus = malus;
        presentPawn = new HashMap<>();
        developmentCard = new DevelopmentCardLight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SlotLight that = (SlotLight) o;
        return  Objects.equals(getBonus(), that.getBonus()) &&
                Objects.equals(getCost(), that.getCost()) &&
                Objects.equals(getDevelopmentCard(), that.getDevelopmentCard()) &&
                Objects.equals(getPresentPawn(), that.getPresentPawn()) &&
                Objects.equals(getMalus(), that.getMalus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBonus(), getCost(), getDevelopmentCard(), getPresentPawn(), getMalus());
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
