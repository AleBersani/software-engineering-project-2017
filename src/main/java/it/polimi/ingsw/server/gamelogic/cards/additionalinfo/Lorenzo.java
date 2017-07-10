package it.polimi.ingsw.server.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.server.gamelogic.cards.CardVisitor;

public class Lorenzo extends AdditionalCardInfo {
    public Lorenzo(String name) {
        super(name);
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitAdditionalCardInfo(this);
    }
}
