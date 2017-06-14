package it.polimi.ingsw.client.lightmodel;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DevelopmentCardLight that = (DevelopmentCardLight) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCardCosts(), that.getCardCosts()) &&
                Objects.equals(getInstantEffectDescription(), that.getInstantEffectDescription()) &&
                Objects.equals(getPermanentEffectDescription(), that.getPermanentEffectDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCardCosts(), getInstantEffectDescription(), getPermanentEffectDescription());
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
