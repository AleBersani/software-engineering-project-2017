package it.polimi.ingsw.gamelogic.basics;

import it.polimi.ingsw.gamelogic.enums.GeneralColor;

/**
 * Class that describes the situation where an Action has as a Requirement a number of cards of a certain color,
 * so there's the number of how many cards are required (numberOfCardsRequired) and the color is defined by the
 * GeneralColor enum.
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
