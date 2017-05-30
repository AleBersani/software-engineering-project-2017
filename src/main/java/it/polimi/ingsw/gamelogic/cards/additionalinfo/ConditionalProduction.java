package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;

/**
 * TODO: JavaDoc
 */
public class ConditionalProduction extends AdditionalCardInfo {
    private GeneralColor cardColor;

    public ConditionalProduction(String name, GeneralColor cardColor) {
        super(name);
        this.cardColor = cardColor;
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        /*
        TODO
         */
    }

    public GeneralColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(GeneralColor cardColor) {
        this.cardColor = cardColor;
    }
}
