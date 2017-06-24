package it.polimi.ingsw.client.cli.model;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;

import java.util.Objects;

public class DevelopmentCardsLight {
    private String name;
    private String instantEffectDescription;
    private String permanentEffectDescription;
    private String cost;

    public DevelopmentCardsLight(String name, String instantEffectDescription, String permanentEffectDescription, String cost) {
        this.name = name;
        this.instantEffectDescription = instantEffectDescription;
        this.permanentEffectDescription = permanentEffectDescription;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DevelopmentCardsLight that = (DevelopmentCardsLight) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getInstantEffectDescription(), that.getInstantEffectDescription()) &&
                Objects.equals(getPermanentEffectDescription(), that.getPermanentEffectDescription()) &&
                Objects.equals(getCost(), that.getCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getInstantEffectDescription(), getPermanentEffectDescription(), getCost());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstantEffectDescription() {
        return instantEffectDescription;
    }

    public void setInstantEffectDescription(String instantEffectDescription) {
        this.instantEffectDescription = instantEffectDescription;
    }

    public String getPermanentEffectDescription() {
        return permanentEffectDescription;
    }

    public void setPermanentEffectDescription(String permanentEffectDescription) {
        this.permanentEffectDescription = permanentEffectDescription;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
