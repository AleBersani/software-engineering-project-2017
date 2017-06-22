package it.polimi.ingsw.client.lightmodel;

import java.util.List;
import java.util.Objects;

public class DevelopmentCardLight {
    private int idCard;
    private String name;
    private List<String> cardCosts;
    private String instantEffectDescription;
    private List<String> permanentEffectDescription;

    public DevelopmentCardLight() {
    }

    public DevelopmentCardLight(int idCard, String name,
                                List<String> cardCosts,
                                String instantEffectDescription,
                                List<String> permanentEffectDescription) {
        this.idCard = idCard;
        this.cardCosts = cardCosts;
        this.name = name;
        this.instantEffectDescription = instantEffectDescription;
        this.permanentEffectDescription = permanentEffectDescription;
    }

    public String toBoardString() {
        return "IdCard: " + idCard +
                ", Name: '" + name + '\'' +
                ",\nCost: " + showCardCosts();
    }

    public String toPlayerString() {
        return "idCard: " + idCard +
                ", name='" + name + '\'' +
                ",\ninstantEffectDescription: " + instantEffectDescription +
                ",\npermanentEffectDescription: " + showPermanentEffect();
    }

    private String showPermanentEffect() {
        String permanentEffectToPrint = "";
        String singleEffect;
        for (int i=0; i < permanentEffectDescription.size(); i++){
            singleEffect = permanentEffectDescription.get(i);
            permanentEffectToPrint = permanentEffectToPrint + singleEffect + "\n";
        }
        return permanentEffectToPrint;
    }

    private String showCardCosts() {
        String costsToPrint = "";
        String str;
        for (int i = 0; i < cardCosts.size(); i++) {
            str = cardCosts.get(i);
            costsToPrint = costsToPrint + str;
            if (i+1 != cardCosts.size())
                costsToPrint = costsToPrint + " or ";
        }
        return costsToPrint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DevelopmentCardLight that = (DevelopmentCardLight) o;
        return Objects.equals(getIdCard(), that.getIdCard()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getCardCosts(), that.getCardCosts()) &&
                Objects.equals(getInstantEffectDescription(), that.getInstantEffectDescription()) &&
                Objects.equals(getPermanentEffectDescription(), that.getPermanentEffectDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCard(), getName(), getCardCosts(), getInstantEffectDescription(), getPermanentEffectDescription());
    }


    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCardCosts() {
        return cardCosts;
    }

    public void setCardCosts(List<String> cardCosts) {
        this.cardCosts = cardCosts;
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
