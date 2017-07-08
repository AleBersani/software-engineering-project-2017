package it.polimi.ingsw.client.middleware;

import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.gui.notify.*;
import it.polimi.ingsw.client.model.BoardLight;
import it.polimi.ingsw.client.model.Card;
import it.polimi.ingsw.client.model.Owner;
import it.polimi.ingsw.shared.requests.serverclient.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ClientReceiverHandler implements ClientReceiver {
    private static final Logger LOGGER = Logger.getLogger(ClientReceiverHandler.class.getName());

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
            ClientInformation.setPlayerColor(chosenGameResponse.getPlayerColor());
            ClientInformation.setGameStarted(true);
        } else {
            System.out.println("Chosen game mode unaccepted!");
        }
        GameChoiceNotifier guiNotifier = GameChoiceNotifier.getInstance();
        guiNotifier.updateGui(chosenGameResponse.isAccepted());
    }

    @Override
    public void visitServerClientRequest(CouncilPrivilegeChoice councilPrivilegeChoice) {
        System.out.printf("You have to chose &d council privilege", councilPrivilegeChoice.getNumberOfChoices());
        GameBoardNotifier guiNotifier = GameBoardNotifier.getInstance();
        guiNotifier.updateGui(councilPrivilegeChoice.getNumberOfChoices());
    }

    @Override
    public void visitServerClientRequest(EndLeadersChoicePhase endLeadersChoicePhase) {
        System.out.println("Leader choice phase ended!");
        ClientInformation.setLeaderChoiceEnded(true);
        LeaderChoiceNotifier guiNotifier = LeaderChoiceNotifier.getInstance();
        guiNotifier.updateGui();
    }

    @Override
    public void visitServerClientRequest(EndTileChoicePhase endTileChoicePhase) {
        System.out.println("Bonus tile choice phase ended!");
        ClientInformation.setBonusTilesChoiceEnded(true);
        BonusTileChoiceNotifier guiNotifier = BonusTileChoiceNotifier.getInstance();
        guiNotifier.updateGui();
    }

    @Override
    public void visitServerClientRequest(LeadersChoice leadersChoice) {
        List<Card> leaders = new ArrayList<>();
        for (String leaderName : leadersChoice.getLeaders()) {
            leaders.add(new Card(leaderName));
        }
        ClientInformation.setCurrentLeadersToChoice(leaders);
        LeaderChoiceNotifier guiNotifier = LeaderChoiceNotifier.getInstance();
        guiNotifier.updateGui(leaders);
    }

    @Override
    public void visitServerClientRequest(LoginResponse loginResponse) {
        if (loginResponse.isSuccessful()) {
            ClientInformation.setLoginSuccessful(true);
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
    public void visitServerClientRequest(TileChoice tileChoice) {
        ClientInformation.setCurrentBonusTilesToChoice(tileChoice.getBonusTiles());
        BonusTileChoiceNotifier guiNotifier = BonusTileChoiceNotifier.getInstance();
        guiNotifier.updateGui(tileChoice.getBonusTiles());
    }

    @Override
    public void visitServerClientRequest(UpdateGameBoard updateGameBoard) {
        Owner owner = Owner.getInstance();
        BoardLight boardLight = BoardLight.getInstance();
        boardLight.setGreenTower(updateGameBoard.getNewGreenTower());
        boardLight.setYellowTower(updateGameBoard.getNewYellowTower());
        boardLight.setBlueTower(updateGameBoard.getNewBlueTower());
        boardLight.setPurpleTower(updateGameBoard.getNewPurpleTower());
        boardLight.setProduction(updateGameBoard.getNewProduction());
        boardLight.setHarvest(updateGameBoard.getNewHarvest());
        boardLight.setMarket(updateGameBoard.getNewMarket());
        boardLight.setCouncilPalaceLight(updateGameBoard.getNewCouncilPalaceLight());
        boardLight.setPlayerLights(updateGameBoard.getNewPlayerLights());
        boardLight.setDiceLightList(updateGameBoard.getDiceLightList());
        boardLight.setExcommunicationTiles(updateGameBoard.getNewExcommunicationTiles());
        owner.setPawnLights(updateGameBoard.getPawnLightList());
        GameBoardNotifier guiNotifier = GameBoardNotifier.getInstance();
        guiNotifier.updateGui();
    }

    @Override
    public void visitServerClientRequest(UpdateGameId updateGameId) {
        ClientInformation.setCurrentGameId(updateGameId.getGameId());
    }

    @Override
    public void visitServerClientRequest(UpdatePlayerBoard updatePlayerBoard) {
        Owner owner = Owner.getInstance();
        owner.setBonusTileIdentifier(updatePlayerBoard.getNewBonusTileIdentifier());
        owner.setDeckLight(updatePlayerBoard.getNewDeckLight());
        owner.setNumberOfResources(updatePlayerBoard.getNumberOfResources());
        owner.getPlayerLight().setActivatedLeaders(updatePlayerBoard.getNewActivatedLeaders());
        owner.getPlayerLight().setNumberOfPoints(updatePlayerBoard.getNewNumberOfPoints());
        PlayerBoardNotifier guiNotifier = PlayerBoardNotifier.getInstance();
        guiNotifier.updateGui();
    }

    @Override
    public void visitServerClientRequest(YourTurn yourTurn) {
        ClientInformation.setCanPlay(yourTurn.isCanPlay());
        if (yourTurn.isCanPlay()) {
            System.out.println("It's your turn!");
        }
    }
}
