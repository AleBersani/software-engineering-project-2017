package it.polimi.ingsw.server.exectutionmiddleware;

import it.polimi.ingsw.shared.requests.clientserver.*;

public interface ClientServerRequestVisitor {
    void visitClientServerRequest(Choices choices);
    void visitClientServerRequest(Connection connection);
    void visitClientServerRequest(LeaderAction leaderAction);
    void visitClientServerRequest(PawnPlacement pawnPlacement);
    void visitClientServerRequest(PlayerLogin playerLogin);
}
