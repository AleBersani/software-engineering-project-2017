package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.cards.CardVisitor;

/**
 * Class that is the father of the classes that describe the different permanent "effects" that the cards might have
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
