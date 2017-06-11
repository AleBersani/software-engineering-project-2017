package it.polimi.ingsw.shared.requests.clientserver;

import it.polimi.ingsw.server.exectutionmiddleware.ClientServerRequestVisitor;
import it.polimi.ingsw.shared.model.ActionType;

import java.io.Serializable;

public class LeaderAction implements Serializable, ClientServerRequest {
    private ActionType actionType;
    private String leaderName;

    public LeaderAction(ActionType actionType, String leaderName) {
        this.actionType = actionType;
        this.leaderName = leaderName;
    }

    @Override
    public String toString() {
        return "LeaderAction{" +
                "actionType=" + actionType +
                ", leaderName='" + leaderName + '\'' +
                '}';
    }

    @Override
    public void acceptClientServerRequestVisitor(ClientServerRequestVisitor clientServerRequestVisitor) {
        clientServerRequestVisitor.visitClientServerRequest(this);
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
