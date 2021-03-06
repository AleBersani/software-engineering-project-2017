package it.polimi.ingsw.client.middleware;

import it.polimi.ingsw.shared.requests.serverclient.*;

public interface ClientReceiver {
    void visitServerClientRequest(ActionResponse actionResponse);
    void visitServerClientRequest(ChosenGameResponse chosenGameResponse);
    void visitServerClientRequest(ConsumableAction consumableAction);
    void visitServerClientRequest(CouncilPrivilegeChoice councilPrivilegeChoice);
    void visitServerClientRequest(EndGame endGame);
    void visitServerClientRequest(EndLeadersChoicePhase endLeadersChoicePhase);
    void visitServerClientRequest(EndTileChoicePhase endTileChoicePhase);
    void visitServerClientRequest(LeadersChoice leadersChoice);
    void visitServerClientRequest(LoginResponse loginResponse);
    void visitServerClientRequest(LorenzoRequest lorenzoRequest);
    void visitServerClientRequest(SimpleMessage simpleMessage);
    void visitServerClientRequest(TileChoice tileChoice);
    void visitServerClientRequest(UpdateGameBoard updateGameBoard);
    void visitServerClientRequest(UpdateGameId updateGameId);
    void visitServerClientRequest(UpdatePlayerBoard updatePlayerBoard);
    void visitServerClientRequest(YourTurn yourTurn);
}
