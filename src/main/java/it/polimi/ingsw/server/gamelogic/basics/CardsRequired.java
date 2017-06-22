package it.polimi.ingsw.server.gamelogic.basics;

import it.polimi.ingsw.shared.model.GeneralColor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CardsRequired that = (CardsRequired) o;
        return getNumberOfCardsRequired() == that.getNumberOfCardsRequired() &&
                getCardColorRequired() == that.getCardColorRequired();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumberOfCardsRequired(), getCardColorRequired());
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
