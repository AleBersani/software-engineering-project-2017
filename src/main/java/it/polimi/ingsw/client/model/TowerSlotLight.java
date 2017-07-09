package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.cli.gameinformation.BoardOwnerInformation;
import it.polimi.ingsw.client.cli.model.BoardSpaceDescriptionLight;

import java.io.Serializable;
import java.util.Optional;

public class TowerSlotLight implements Serializable {
    private SlotLight slotLight;

    private Card card;

    public TowerSlotLight(SlotLight slotLight, Card card) {
        this.slotLight = slotLight;
        this.card = card;
    }

    @Override
    public String toString() {
        String toString = slotLight.toString();
        Optional<BoardSpaceDescriptionLight> boardSpaceDescriptionLightOptional;
        if (card != null)
            toString = toString + "Card " + card.getName() + "\n";
        boardSpaceDescriptionLightOptional = BoardOwnerInformation
                .searchForBoardSpaceLight(slotLight.getBoardIdentifier());
        if (boardSpaceDescriptionLightOptional.isPresent())
            toString = toString + "Bonus: " + boardSpaceDescriptionLightOptional.get().getBonus() + "\n";
        return toString;
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
