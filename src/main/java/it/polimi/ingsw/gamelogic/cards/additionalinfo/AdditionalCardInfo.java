package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.cards.CardVisitor;

/**
 * TODO: JavaDoc
 */
public abstract class AdditionalCardInfo {
    private String name;

    public AdditionalCardInfo(String name) {
        this.name = name;
    }

    public abstract void acceptCardVisitor(CardVisitor cardVisitor);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
