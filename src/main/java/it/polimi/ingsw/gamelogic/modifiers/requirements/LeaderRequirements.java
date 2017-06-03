package it.polimi.ingsw.gamelogic.modifiers.requirements;

import it.polimi.ingsw.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.gamelogic.enums.ActionType;
import it.polimi.ingsw.gamelogic.player.Player;

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
    public boolean hasRequirements(Player player) {
        /*
        TODO
         */
        return false;
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
