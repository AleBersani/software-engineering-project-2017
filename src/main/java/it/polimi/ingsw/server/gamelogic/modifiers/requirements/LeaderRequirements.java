package it.polimi.ingsw.server.gamelogic.modifiers.requirements;

import it.polimi.ingsw.server.gamelogic.basics.CardsRequired;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.ActionType;

import java.util.List;
import java.util.Objects;

/**
 * Class that describes the requirements of a Leader Action
 */
public class LeaderRequirements implements Requirements {
    private ActionType actionType;
    private String leaderName;
    private LeaderCost leaderCost;

    public LeaderRequirements(ActionType actionType, String leaderName, LeaderCost leaderCost) {
        this.actionType = actionType;
        this.leaderName = leaderName;
        this.leaderCost = leaderCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LeaderRequirements that = (LeaderRequirements) o;
        return getActionType() == that.getActionType() &&
                Objects.equals(getLeaderName(), that.getLeaderName()) &&
                Objects.equals(getLeaderCost(), that.getLeaderCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActionType(), getLeaderName(), getLeaderCost());
    }

    /**
     * Checks:
     * - if the action is different from Leader placement
     * - if the player has a Leader
     * - if the player has enough goods to fulfill the leader cost
     * - if the player has the needed number of required cards
     * @param player player whose turn it is
     * @return true if the player passes all the checks
     */
    @Override
    public boolean hasRequirements(Player player) {
        if (!player.hasLeader(leaderName))
            return false;

        if (actionType != ActionType.LEADER_PLACEMENT)
            return true;

        if (!leaderCost.getRequiredGoods().isLessThan(player.getPlayerGoods()))
            return false;

        boolean hasRequiredCards = true;
        if (!leaderCost.getCardsRequiredList().isEmpty()) {
            hasRequiredCards = false;
            List<CardsRequired> cardsRequiredList = leaderCost.getCardsRequiredList();
            for (CardsRequired cardsRequired : cardsRequiredList) {
                if (player.countGivenIdentifier(cardsRequired.toString()) >= cardsRequired.getNumberOfCardsRequired())
                    hasRequiredCards = true;
            }
        }

        return hasRequiredCards;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public LeaderCost getLeaderCost() {
        return leaderCost;
    }

    public void setLeaderCost(LeaderCost leaderCost) {
        this.leaderCost = leaderCost;
    }
}
