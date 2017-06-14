package it.polimi.ingsw.server.exectutionmiddleware;

import it.polimi.ingsw.server.rmi.Communicator;
import it.polimi.ingsw.shared.Client;
import it.polimi.ingsw.shared.requests.clientserver.*;

import java.rmi.RemoteException;

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

    @Override
    public void visitClientServerRequest(PlayerLogin playerLogin) {
        System.out.println("PlayerLogin");
        // Verify data
        System.out.println(playerLogin.getPlayerDetails().getPlayerName());
        try {
            Communicator.doCallbacks("Ciao!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
