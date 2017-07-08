package it.polimi.ingsw.client.gui;

import com.jfoenix.controls.JFXButton;
import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.gui.notify.PlayerBoardNotifier;
import it.polimi.ingsw.client.middleware.ClientSender;
import it.polimi.ingsw.client.middleware.ClientSenderHandler;
import it.polimi.ingsw.client.model.Owner;
import it.polimi.ingsw.client.model.enums.ResourcesLight;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.requests.clientserver.Ready;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;

public class PlayerboardController implements Observer {
    private static final String BACKGROUND_URL = "/client/backgrounds/";

    private List<ImageView> greenCards;
    private List<ImageView> yellowCards;
    private List<ImageView> purpleCards;
    private List<ImageView> blueCards;
    private List<ImageView> leaders;
    private Map<GeneralColor, String> leaderBackgrounds;

    private Owner owner;

    @FXML
    private ImageView chosenBonusTile;
    @FXML
    private ImageView chosenBonusTile1;
    @FXML
    private ImageView yellow1;
    @FXML
    private ImageView yellow2;
    @FXML
    private ImageView yellow3;
    @FXML
    private ImageView yellow4;
    @FXML
    private ImageView yellow5;
    @FXML
    private ImageView yellow6;
    @FXML
    private ImageView green1;
    @FXML
    private ImageView green2;
    @FXML
    private ImageView green3;
    @FXML
    private ImageView green4;
    @FXML
    private ImageView green5;
    @FXML
    private ImageView green6;
    @FXML
    private ImageView purple1;
    @FXML
    private ImageView purple2;
    @FXML
    private ImageView purple3;
    @FXML
    private ImageView purple4;
    @FXML
    private ImageView purple5;
    @FXML
    private ImageView purple6;
    @FXML
    private ImageView blue1;
    @FXML
    private ImageView blue2;
    @FXML
    private ImageView blue3;
    @FXML
    private ImageView blue4;
    @FXML
    private ImageView blue5;
    @FXML
    private ImageView blue6;
    @FXML
    private ImageView led1;
    @FXML
    private ImageView led2;
    @FXML
    private ImageView led3;
    @FXML
    private ImageView led4;
    @FXML
    private ImageView backgroundLeaders;

    @FXML
    private Label coins;
    @FXML
    private Label coins1;
    @FXML
    private Label woods;
    @FXML
    private Label woods1;
    @FXML
    private Label stones;
    @FXML
    private Label stones1;
    @FXML
    private Label servants;
    @FXML
    private Label servants1;

    @FXML
    private JFXButton place1;
    @FXML
    private JFXButton active1;
    @FXML
    private JFXButton place2;
    @FXML
    private JFXButton active2;
    @FXML
    private JFXButton place3;
    @FXML
    private JFXButton active3;
    @FXML
    private JFXButton place4;
    @FXML
    private JFXButton active4;

    public void initialize() {
        PlayerBoardNotifier.getInstance().addObserver(this);
        owner = Owner.getInstance();
        initEnumMap();
        setBackground();
        setCards();
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new Ready(ClientInformation.getCurrentGameId(), "game"));
    }

    private void initEnumMap() {
        leaderBackgrounds = new EnumMap<>(GeneralColor.class);
        leaderBackgrounds.put(GeneralColor.BLUE, BACKGROUND_URL + "blue.jpg");
        leaderBackgrounds.put(GeneralColor.GREEN, BACKGROUND_URL + "green.jpg");
        leaderBackgrounds.put(GeneralColor.YELLOW, BACKGROUND_URL + "yellow.jpg");
        leaderBackgrounds.put(GeneralColor.PURPLE, BACKGROUND_URL + "red.jpg");
    }

    private void setBackground() {
        Image newBackground = new Image(leaderBackgrounds.get(ClientInformation.getPlayerColor()));
        backgroundLeaders.setImage(newBackground);
    }

    private void setCards() {
        greenCards = new ArrayList<>();
        greenCards.add(green1);
        greenCards.add(green2);
        greenCards.add(green3);
        greenCards.add(green4);
        greenCards.add(green5);
        greenCards.add(green6);

        yellowCards = new ArrayList<>();
        yellowCards.add(yellow1);
        yellowCards.add(yellow2);
        yellowCards.add(yellow3);
        yellowCards.add(yellow4);
        yellowCards.add(yellow5);
        yellowCards.add(yellow6);

        purpleCards = new ArrayList<>();
        purpleCards.add(purple1);
        purpleCards.add(purple2);
        purpleCards.add(purple3);
        purpleCards.add(purple4);
        purpleCards.add(purple5);
        purpleCards.add(purple6);

        blueCards = new ArrayList<>();
        blueCards.add(blue1);
        blueCards.add(blue2);
        blueCards.add(blue3);
        blueCards.add(blue4);
        blueCards.add(blue5);
        blueCards.add(blue6);

        leaders = new ArrayList<>();
        leaders.add(led1);
        leaders.add(led2);
        leaders.add(led3);
        leaders.add(led4);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            initGreenCards();
            initYellowCards();
            initBlueCards();
            initPurpleCards();
            initLeaders();
            setChosenBonusTile();
            setResources();
        });
    }

    private void initGreenCards() {
        for (int i = 0; i < owner.getDeckLight().getTerritories().size(); i++) {
            Image newGreenCard = new Image("client/devcards/" + owner.getTerritories().get(i).getName() + ".png");
            greenCards.get(i).setImage(newGreenCard);
        }
    }

    private void initYellowCards() {
        for (int i = 0; i < owner.getDeckLight().getBuildings().size(); i++) {
            Image newYellowCard = new Image("client/devcards/" + owner.getBuildings().get(i).getName() + ".png");
            yellowCards.get(i).setImage(newYellowCard);
        }
    }

    private void initBlueCards() {
        for (int i = 0; i < owner.getDeckLight().getCharacters().size(); i++) {
            Image newBlueCard = new Image("client/devcards/" + owner.getCharacters().get(i).getName() + ".png");
            blueCards.get(i).setImage(newBlueCard);
        }
    }

    private void initPurpleCards() {
        for (int i = 0; i < owner.getDeckLight().getVentures().size(); i++) {
            Image newPurpleCard = new Image("client/devcards/" + owner.getVentures().get(i).getName() + ".png");
            purpleCards.get(i).setImage(newPurpleCard);
        }
    }

    private void initLeaders() {
        for (int i = 0; i < owner.getDeckLight().getLeaders().size(); i++) {
            Image newLeader = new Image("client/leader/" + owner.getLeaders().get(i).getName() + ".jpg");
            leaders.get(i).setImage(newLeader);
        }
    }

    private void setChosenBonusTile() {
        Image bonusTile = new Image("/client/bonustiles/" + owner.getBonusTileIdentifier() + ".png");
        chosenBonusTile.setImage(bonusTile);
        chosenBonusTile1.setImage(bonusTile);
    }

    public void setResources() {
        coins.setText(owner.getNumberOfResources().get(ResourcesLight.COINS).toString());
        coins1.setText(owner.getNumberOfResources().get(ResourcesLight.COINS).toString());
        woods.setText(owner.getNumberOfResources().get(ResourcesLight.WOODS).toString());
        woods1.setText(owner.getNumberOfResources().get(ResourcesLight.WOODS).toString());
        stones.setText(owner.getNumberOfResources().get(ResourcesLight.STONES).toString());
        stones1.setText(owner.getNumberOfResources().get(ResourcesLight.STONES).toString());
        servants.setText(owner.getNumberOfResources().get(ResourcesLight.SERVANTS).toString());
        servants1.setText(owner.getNumberOfResources().get(ResourcesLight.SERVANTS).toString());
    }

    @FXML
    public void activateLeader() {

    }

    @FXML
    public void placeLeader() {

    }
}