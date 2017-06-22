package it.polimi.ingsw.client.cli.model;

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
