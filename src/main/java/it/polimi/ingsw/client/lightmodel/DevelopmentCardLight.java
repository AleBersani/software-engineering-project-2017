package it.polimi.ingsw.client.lightmodel;

import java.util.List;

public class DevelopmentCardLight {
    private String name;
    private List<String> cardCosts;
    private String instantEffectDescription;
    private List<String> permanentEffectDescription;

    public DevelopmentCardLight(String name,
                                List<String> cardCosts,
                                String instantEffectDescription,
                                List<String> permanentEffectDescription) {
        this.cardCosts = cardCosts;
        this.name = name;
        this.instantEffectDescription = instantEffectDescription;
        this.permanentEffectDescription = permanentEffectDescription;
    }

    public List<String> getCardCosts() {
        return cardCosts;
    }

    public void setCardCosts(List<String> cardCosts) {
        this.cardCosts = cardCosts;
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

    public List<String> getPermanentEffectDescription() {
        return permanentEffectDescription;
    }

    public void setPermanentEffectDescription(List<String> permanentEffectDescription) {
        this.permanentEffectDescription = permanentEffectDescription;
    }
}
