package it.polimi.ingsw.client.model;

import java.io.Serializable;

public class TowerSlotLight implements Serializable {
    private SlotLight slotLight;

    private Card card;

    public TowerSlotLight(SlotLight slotLight, Card card) {
        this.slotLight = slotLight;
        this.card = card;
    }

    @Override
    public String toString() {
        return "TowerSlotLight{" +
                "slotLight=" + slotLight +
                ", card=" + card +
                '}';
    }

    public SlotLight getSlotLight() {
        return slotLight;
    }

    public void setSlotLight(SlotLight slotLight) {
        this.slotLight = slotLight;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
