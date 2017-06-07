package it.polimi.ingsw.gamelogic.modifiers.requirements;

import it.polimi.ingsw.gamelogic.basics.CardsRequired;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.player.Player;

import java.util.List;

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

    /**
     * TODO: JavaDoc
     * @param player
     * @return
     */
    @Override
    public boolean hasRequirements(Player player) {
        if (actionType != ActionType.LEADER_PLACEMENT)
            return true;

        if (!player.hasLeader(leaderName))
            return false;

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
