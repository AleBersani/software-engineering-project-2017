package it.polimi.ingsw.client.model;

public class TowerSlotLight {
    private SlotLight slotLight;

    private Card card; //TODO

    public TowerSlotLight(SlotLight slotLight) {
        this.slotLight = slotLight;
        card = null;
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
