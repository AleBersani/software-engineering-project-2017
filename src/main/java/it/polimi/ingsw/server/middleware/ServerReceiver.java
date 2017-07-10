package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.shared.requests.clientserver.*;

public interface ServerReceiver {
    void visitClientServerRequest(ChosenBonusTile bonusTile);
    void visitClientServerRequest(ChosenConsumableAction chosenConsumableAction);
    void visitClientServerRequest(ChosenCouncilPrivilege chosenCouncilPrivilege);
    void visitClientServerRequest(ChosenLeader chosenLeader);
    void visitClientServerRequest(ChosenLorenzo chosenLorenzo);
    void visitClientServerRequest(GameStartChoice gameStartChoice);
    void visitClientServerRequest(GameStartChoiceRMI gameStartChoiceRMI);
    void visitClientServerRequest(LeaderAction leaderAction);
    void visitClientServerRequest(PawnPlacement pawnPlacement);
    void visitClientServerRequest(PlayerLogin playerLogin);
    void visitClientServerRequest(PlayerLoginRMI playerLoginRMI);
    void visitClientServerRequest(Ready ready);
}
