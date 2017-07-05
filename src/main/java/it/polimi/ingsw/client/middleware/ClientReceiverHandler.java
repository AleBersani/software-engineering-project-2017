package it.polimi.ingsw.client.middleware;

import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.gui.notify.GameChoiceNotifier;
import it.polimi.ingsw.client.gui.notify.LeaderChoiceNotifier;
import it.polimi.ingsw.client.gui.notify.PlayerLoginNotifier;
import it.polimi.ingsw.client.model.Card;
import it.polimi.ingsw.shared.requests.serverclient.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ClientReceiverHandler implements ClientReceiver {
    private final static Logger LOGGER = Logger.getLogger(ClientReceiverHandler.class.getName());

    private ClientReceiverHandler() {}

    private static class ClientReceiverHandlerHolder {
        private final static ClientReceiver INSTANCE = new ClientReceiverHandler();
    }

    public static ClientReceiver getInstance() {
        return ClientReceiverHandlerHolder.INSTANCE;
    }

    @Override
    public void visitServerClientRequest(ChosenGameResponse chosenGameResponse) {
        if (chosenGameResponse.isAccepted()) {
            System.out.println("Chosen game mode accepted!");
        } else {
            System.out.println("Chosen game mode unaccepted!");
        }
        GameChoiceNotifier guiNotifier = GameChoiceNotifier.getInstance();
        guiNotifier.updateGui(chosenGameResponse.isAccepted());
    }

    @Override
    public void visitServerClientRequest(EndLeadersChoicePhase endLeadersChoicePhase) {
        System.out.println("Leader choice phase ended!");
        LeaderChoiceNotifier guiNotifier = LeaderChoiceNotifier.getInstance();
        guiNotifier.updateGui();
    }

    @Override
    public void visitServerClientRequest(LeadersChoice leadersChoice) {
        List<Card> leaders = new ArrayList<>();
        for (String leaderName : leadersChoice.getLeaders()) {
            leaders.add(new Card(leaderName));
        }
        LeaderChoiceNotifier guiNotifier = LeaderChoiceNotifier.getInstance();
        guiNotifier.updateGui(leaders);
    }

    @Override
    public void visitServerClientRequest(LoginResponse loginResponse) {
        if (loginResponse.isSuccessful()) {
            System.out.println("Login successful: " + loginResponse.getPlayerName());
            ClientInformation.setPlayerName(loginResponse.getPlayerName());
        } else {
            System.out.println("Login unsuccessful");
        }
        PlayerLoginNotifier guiNotifier = PlayerLoginNotifier.getInstance();
        guiNotifier.updateGui(loginResponse.isSuccessful());
    }

    @Override
    public void visitServerClientRequest(SimpleMessage simpleMessage) {
        System.out.println(simpleMessage.getMessage());
    }

    @Override
    public void visitServerClientRequest(UpdateActionSpaces updateActionSpaces) {

    }

    @Override
    public void visitServerClientRequest(UpdateGameId updateGameId) {
        ClientInformation.setCurrentGameId(updateGameId.getGameId());
    }

    @Override
    public void visitServerClientRequest(UpdateTowers updateTowers) {

    }
}
