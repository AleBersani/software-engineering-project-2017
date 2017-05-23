package it.polimi.ingsw.gamelogic.actions;

import it.polimi.ingsw.gamecontroller.Action;
import it.polimi.ingsw.gamelogic.enums.AvailableActions;

/**
 * Class that represent every action related to a Leader Card, in particular:
 *      - placement of a Leader
 *      - activation of a Leader
 *      - discard of a Leader
 */
public class LeaderAction implements ActionDescription {
    private AvailableActions actionType;
    private String leaderName;

    public LeaderAction(AvailableActions actionType, String leaderName) {
        this.actionType = actionType;
        this.leaderName = leaderName;
    }

    @Override
    public void callAction(Action action) {
        action.readAction(this);
    }

    public AvailableActions getActionType() {
        return actionType;
    }

    public void setActionType(AvailableActions actionType) {
        this.actionType = actionType;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
}
