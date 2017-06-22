package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.server.gamelogic.enums.GeneralColor;

import java.util.Objects;

/**
 * Class that represent when the player receives some Goods based on the number of cards of a certain color
 */
public class ConditionalProduction extends AdditionalCardInfo {
    private GeneralColor cardColor;

    public ConditionalProduction(String name, GeneralColor cardColor) {
        super(name);
        this.cardColor = cardColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        ConditionalProduction that = (ConditionalProduction) o;
        return getCardColor() == that.getCardColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCardColor());
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitAdditionalCardInfo(this);
    }

    public GeneralColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(GeneralColor cardColor) {
        this.cardColor = cardColor;
    }
}
