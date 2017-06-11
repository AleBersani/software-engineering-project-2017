package it.polimi.ingsw.server.exectutionmiddleware;

import it.polimi.ingsw.shared.requests.clientserver.Choices;
import it.polimi.ingsw.shared.requests.clientserver.Connection;
import it.polimi.ingsw.shared.requests.clientserver.LeaderAction;
import it.polimi.ingsw.shared.requests.clientserver.PawnPlacement;

public interface ClientServerRequestVisitor {
    void visitClientServerRequest(Choices choices);
    void visitClientServerRequest(Connection connection);
    void visitClientServerRequest(LeaderAction leaderAction);
    void visitClientServerRequest(PawnPlacement pawnPlacement);
}
