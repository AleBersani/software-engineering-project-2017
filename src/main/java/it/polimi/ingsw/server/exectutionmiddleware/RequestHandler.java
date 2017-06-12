package it.polimi.ingsw.server.exectutionmiddleware;

import it.polimi.ingsw.shared.requests.clientserver.Choices;
import it.polimi.ingsw.shared.requests.clientserver.Connection;
import it.polimi.ingsw.shared.requests.clientserver.LeaderAction;
import it.polimi.ingsw.shared.requests.clientserver.PawnPlacement;

public class RequestHandler implements ClientServerRequestVisitor {
    @Override
    public void visitClientServerRequest(Choices choices) {
        System.out.println("Choice");
    }

    @Override
    public void visitClientServerRequest(Connection connection) {
        System.out.println("Connection");
    }

    @Override
    public void visitClientServerRequest(LeaderAction leaderAction) {
        System.out.println("LeaderAction");
    }

    @Override
    public void visitClientServerRequest(PawnPlacement pawnPlacement) {
        System.out.println("PawnPlacement");
    }
}
