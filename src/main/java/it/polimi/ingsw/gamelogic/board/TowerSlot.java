package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.cards.developmentcards.Collectible;

/**
 * TODO: JavaDoc
 */
public class TowerSlot {
    private Space space;
    private Collectible developmentCard;

    public TowerSlot(Space space, Collectible developmentCard) {
        this.space = space;
        this.developmentCard = developmentCard;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Collectible getDevelopmentCard() {
        return developmentCard;
    }

    public void setDevelopmentCard(Collectible developmentCard) {
        this.developmentCard = developmentCard;
    }
}
