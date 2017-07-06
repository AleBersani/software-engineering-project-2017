package it.polimi.ingsw.client.gui;

import com.jfoenix.controls.JFXSpinner;
import com.sun.javafx.css.Size;
import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.gui.notify.BonusTileChoiceNotifier;
import it.polimi.ingsw.client.middleware.ClientSender;
import it.polimi.ingsw.client.middleware.ClientSenderHandler;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.requests.clientserver.Ready;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class TileChoiceController implements Observer {
    private static final String BACKGROUND_URL = "/client/backgrounds/";

    private List<ImageView> tileList;
    private Map<GeneralColor, String> backgrounds;

    @FXML
    private ImageView tile1;
    @FXML
    private ImageView tile2;
    @FXML
    private ImageView tile3;
    @FXML
    private ImageView tile4;

    @FXML
    private JFXSpinner spinner;

    @FXML
    private Label waiting;

    @FXML
    private AnchorPane root;

    public void initialize() {
        BonusTileChoiceNotifier.getInstance().addObserver(this);
        tileList = new ArrayList<>();
        initEnumMap();
        setTileList();
        setBackground();
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new Ready(ClientInformation.getCurrentGameId(), "tileChoice"));
    }

    private void initEnumMap() {
        backgrounds = new EnumMap<>(GeneralColor.class);
        backgrounds.put(GeneralColor.GREEN, BACKGROUND_URL + "green.jpg");
        backgrounds.put(GeneralColor.BLUE, BACKGROUND_URL + "blue.jpg");
        backgrounds.put(GeneralColor.YELLOW, BACKGROUND_URL + "yellow.jpg");
        backgrounds.put(GeneralColor.PURPLE, BACKGROUND_URL + "red.jpg");
    }

    private void setTileList() {
        tileList.add(tile1);
        tileList.add(tile2);
        tileList.add(tile3);
        tileList.add(tile4);
    }

    private void setBackground() {
        root.setBackground(new Background(new BackgroundImage(
                new Image(backgrounds.get(ClientInformation.getPlayerColor())),
                null,null, null, new BackgroundSize(
                        BackgroundSize.AUTO, BackgroundSize.AUTO,
                false, false, true, true))));
    }

    @Override
    public void update(Observable o, Object arg) {
        hideWaiting();
        setTiles((List<String>)arg);
    }

    private void hideWaiting() {
        waiting.setVisible(false);
        spinner.setVisible(false);
    }

    private void setTiles(List<String> availableTiles) {
        for (int i = 0; i < availableTiles.size(); i++) {
            System.out.println(availableTiles.get(i));
            Image newTile = new Image("client/bonustiles/" + availableTiles.get(i) + ".png");
            tileList.get(i).setImage(newTile);
        }
    }

    @FXML
    public void selectTile1() {
    }

    @FXML
    public void selectTile2() {
    }

    @FXML
    public void selectTile3() {
    }

    @FXML
    public void selectTile4() {
    }
}