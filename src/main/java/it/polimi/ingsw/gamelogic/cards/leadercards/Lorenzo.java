package it.polimi.ingsw.gamelogic.cards.leadercards;

public class Lorenzo<T extends LeadersBehaviour> {
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
