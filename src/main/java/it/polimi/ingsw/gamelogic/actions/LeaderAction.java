package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamecontroller.Action;
import it.polimi.ingsw.gamelogic.enums.ActionType;

/**
 * Class that represent every action related to a Leader Card, in particular:
 *      - placement of a Leader
 *      - activation of a Leader
 *      - discard of a Leader
 */
public class LeaderAction implements ActionDescription {
    private ActionType actionType;
    private String leaderName;

    public LeaderAction(ActionType actionType, String leaderName) {
        this.actionType = actionType;
        this.leaderName = leaderName;
    }

    @Override
    public void callAction(Action action) {
        action.readAction(this);
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
}
