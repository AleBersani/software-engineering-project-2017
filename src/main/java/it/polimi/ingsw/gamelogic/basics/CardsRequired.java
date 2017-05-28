package it.polimi.ingsw.gamelogic.basics;

import it.polimi.ingsw.gamelogic.enums.GeneralColor;

/**
 * TODO: JavaDoc
 */
public class CardsRequired {
    private int numberOfCardsRequired;
    private GeneralColor cardColorRequired;

    public CardsRequired(int numberOfCardsRequired, GeneralColor cardColorRequired) {
        this.numberOfCardsRequired = numberOfCardsRequired;
        this.cardColorRequired = cardColorRequired;
    }

    public int getNumberOfCardsRequired() {
        return numberOfCardsRequired;
    }

    public void setNumberOfCardsRequired(int numberOfCardsRequired) {
        this.numberOfCardsRequired = numberOfCardsRequired;
    }

    public GeneralColor getCardColorRequired() {
        return cardColorRequired;
    }

    public void setCardColorRequired(GeneralColor cardColorRequired) {
        this.cardColorRequired = cardColorRequired;
    }
}
