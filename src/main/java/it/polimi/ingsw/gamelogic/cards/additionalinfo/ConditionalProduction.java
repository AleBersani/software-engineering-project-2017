package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;

/**
 * Class that represent when the player receives some Goods based on the number of cards of a certain color
 */
public class ConditionalProduction extends AdditionalCardInfo {
    private GeneralColor cardColor;

    public ConditionalProduction(String name, GeneralColor cardColor) {
        super(name);
        this.cardColor = cardColor;
    }

    /**
     * Visitor method
     * @param cardVisitor concrete visitor
     */
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
