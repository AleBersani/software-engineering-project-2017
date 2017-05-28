package it.polimi.ingsw.gamelogic.cards.leadercards;

import it.polimi.ingsw.gamelogic.cards.leadercards.common.LeaderCard;

/**
 * TODO: JavaDoc
 * @param <T>
 */
public class Lorenzo<T> {
    private LeaderCard leaderCard;
    private T copiedBehaviour;

    public Lorenzo(LeaderCard leaderCard, T copiedBehaviour) {
        this.leaderCard = leaderCard;
        this.copiedBehaviour = copiedBehaviour;
    }

    public LeaderCard getLeaderCard() {
        return leaderCard;
    }

    public void setLeaderCard(LeaderCard leaderCard) {
        this.leaderCard = leaderCard;
    }

    public T getCopiedBehaviour() {
        return copiedBehaviour;
    }

    public void setCopiedBehaviour(T copiedBehaviour) {
        this.copiedBehaviour = copiedBehaviour;
    }
}
