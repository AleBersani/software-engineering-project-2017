package it.polimi.ingsw.shared.requests.serverclient;

import it.polimi.ingsw.client.middleware.ClientReceiver;
import it.polimi.ingsw.shared.model.ActionType;

import java.io.Serializable;
import java.util.List;

public class ConsumableAction implements ServerClientRequest, Serializable {
    private List<ActionType> actionTypes;
    private String nameOfCardWithCardAction;

    public ConsumableAction(List<ActionType> actionTypes, String nameOfCardWithCardAction) {
        this.actionTypes = actionTypes;
        this.nameOfCardWithCardAction = nameOfCardWithCardAction;
    }

    @Override
    public void acceptServerClientRequestVisitor(ClientReceiver clientReceiver) {
        clientReceiver.visitServerClientRequest(this);
    }

    public List<ActionType> getActionTypes() {
        return actionTypes;
    }

    public void setActionTypes(List<ActionType> actionTypes) {
        this.actionTypes = actionTypes;
    }

    public String getNameOfCardWithCardAction() {
        return nameOfCardWithCardAction;
    }

    public void setNameOfCardWithCardAction(String nameOfCardWithCardAction) {
        this.nameOfCardWithCardAction = nameOfCardWithCardAction;
    }
}
