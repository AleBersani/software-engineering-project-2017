package it.polimi.ingsw.server.gamelogic.actions.description;

import it.polimi.ingsw.server.gamelogic.actions.ActionVisitor;
import it.polimi.ingsw.shared.model.ActionType;

import java.util.Objects;

/**
 * Class that represents every action related to a Leader Card, in particular:
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LeaderAction that = (LeaderAction) o;
        return getActionType() == that.getActionType() &&
                Objects.equals(getLeaderName(), that.getLeaderName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActionType(), getLeaderName());
    }

    @Override
    public void acceptActionVisitor(ActionVisitor actionVisitor) {
        actionVisitor.visitActionDescription(this);
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
