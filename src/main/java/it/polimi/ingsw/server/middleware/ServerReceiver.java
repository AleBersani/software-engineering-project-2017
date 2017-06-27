package it.polimi.ingsw.server.middleware;

import it.polimi.ingsw.shared.requests.clientserver.*;

public interface ServerReceiver {
    void visitClientServerRequest(Choices choices);
    void visitClientServerRequest(GameStartChoice gameStartChoice);
    void visitClientServerRequest(GameStartChoiceRMI gameStartChoiceRMI);
    void visitClientServerRequest(LeaderAction leaderAction);
    void visitClientServerRequest(PawnPlacement pawnPlacement);
    void visitClientServerRequest(PlayerLogin playerLogin);
    void visitClientServerRequest(PlayerLoginRMI playerLoginRMI);
}
