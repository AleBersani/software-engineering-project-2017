package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.gui.notify.LeaderChoiceNotifier;
import it.polimi.ingsw.client.middleware.ClientSender;
import it.polimi.ingsw.client.middleware.ClientSenderHandler;
import it.polimi.ingsw.client.model.Card;
import it.polimi.ingsw.shared.requests.clientserver.ChosenLeader;
import it.polimi.ingsw.shared.requests.clientserver.Ready;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeaderChoiceController implements Observer {
    private final static Logger LOGGER = Logger.getLogger(LeaderChoiceController.class.getName());

    private int gameId;
    private String playerName;
    private List<ImageView> leaderCards;
    private List<String> ultimateLeaders;

    @FXML
    private ImageView led1;
    @FXML
    private ImageView led2;
    @FXML
    private ImageView led3;
    @FXML
    private ImageView led4;

    public void initialize() {
        LeaderChoiceNotifier.getInstance().addObserver(this);
        gameId = ClientInformation.getCurrentGameId();
        playerName = ClientInformation.getPlayerName();
        leaderCards = new ArrayList<>();
        ultimateLeaders = new ArrayList<>();
        setLeaderList();
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new Ready(ClientInformation.getCurrentGameId(), "leaderChoice"));
    }

    private void setLeaderList() {
        leaderCards.add(led1);
        leaderCards.add(led2);
        leaderCards.add(led3);
        leaderCards.add(led4);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {
            startPlayerTileChoice();
        } else {
            runUpdateLeaders((List<Card>)arg);
        }
    }

    private void startPlayerTileChoice() {
        Platform.runLater(() ->  {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/gui/tilechoice.fxml"));
                Parent root = fxmlLoader.load();
                Stage leadersChoiceStage = new Stage();
                leadersChoiceStage.setScene(new Scene(root));
                leadersChoiceStage.show();
                closeStage();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot launch player tiles board", e);
            }
        });
    }

    private void closeStage() {
        Stage stage = (Stage)led1.getScene().getWindow();
        stage.close();
    }

    private void runUpdateLeaders(List<Card> leaderCards) {
        clearLeaderList();
        List<String> leaderNames = new ArrayList<>();
        for (Card card : leaderCards) {
            leaderNames.add(card.getName());
        }
        setLeaderCards(leaderNames);
    }

    private void clearLeaderList() {
        for (ImageView imageView : leaderCards) {
            imageView.setImage(null);
        }
    }

    private void setLeaderCards(List<String> availableLeaders) {
        ultimateLeaders = new ArrayList<>(availableLeaders);
        for (int i = 0; i < ultimateLeaders.size(); i++) {
            Image newLeader = new Image("client/leader/" + availableLeaders.get(i) + ".jpg");
            leaderCards.get(i).setImage(newLeader);
        }
    }

    @FXML
    public void selectLeader1(){
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new ChosenLeader(gameId, playerName, ultimateLeaders.get(0)));
    }

    @FXML
    public void selectLeader2(){
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new ChosenLeader(gameId, playerName, ultimateLeaders.get(1)));
    }

    @FXML
    public void selectLeader3(){
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new ChosenLeader(gameId, playerName, ultimateLeaders.get(2)));
    }

    @FXML
    public void selectLeader4(){
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new ChosenLeader(gameId, playerName, ultimateLeaders.get(3)));
    }

    public String saveLeader1() {
        return ultimateLeaders.get(0);
    }

    public String saveLeader2() {
        return ultimateLeaders.get(1);
    }

    public String saveLeader3() {
        return ultimateLeaders.get(2);
    }

    public String saveLeader4() {
        return ultimateLeaders.get(3);
    }
}