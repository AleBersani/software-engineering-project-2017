package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.shared.requests.clientserver.*;

/**
 * Interface that defines methods that are implemented by the classes that want to call the Visitors
 */

public interface ServerReceiver {
    void visitClientServerRequest(ChosenBonusTile bonusTile);
    void visitClientServerRequest(ChosenConsumableAction chosenConsumableAction);
    void visitClientServerRequest(ChosenCouncilPrivilege chosenCouncilPrivilege);
    void visitClientServerRequest(ChosenLeader chosenLeader);
    void visitClientServerRequest(ChosenLorenzo chosenLorenzo);
    void visitClientServerRequest(EndTurn endTurn);
    void visitClientServerRequest(GameStartChoice gameStartChoice);
    void visitClientServerRequest(GameStartChoiceRMI gameStartChoiceRMI);
    void visitClientServerRequest(PawnPlacement pawnPlacement);
    void visitClientServerRequest(PlayerLogin playerLogin);
    void visitClientServerRequest(PlayerLoginRMI playerLoginRMI);
    void visitClientServerRequest(Ready ready);
}
