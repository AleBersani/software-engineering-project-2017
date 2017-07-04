package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.gui.notify.LeaderChoiceNotifier;
import it.polimi.ingsw.client.middleware.ClientSender;
import it.polimi.ingsw.client.middleware.ClientSenderHandler;
import it.polimi.ingsw.client.model.Card;
import it.polimi.ingsw.shared.requests.clientserver.ChosenLeader;
import it.polimi.ingsw.shared.requests.clientserver.Ready;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.*;

public class LeaderChoiceController implements Observer {
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
        clearLeaderList();
        List<Card> leaderCards = (List<Card>)arg;
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