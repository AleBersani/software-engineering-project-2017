package it.polimi.ingsw.client.gui;

import com.jfoenix.controls.JFXSpinner;
import com.sun.javafx.css.Size;
import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.shared.model.GeneralColor;
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

public class TileChoiceController {
    private static final String BACKGROUND_URL = "/client/backgrounds/";
    private List<ImageView> tileList;
    private List<String> ultimateTiles;
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
    private AnchorPane anchorPane;

    public void initialize() {
        tileList = new ArrayList<>();
        ultimateTiles = new ArrayList<>();
        initEnumMap();
        setBackground();
        setTileList();
    }

    private void initEnumMap() {
        backgrounds = new EnumMap<>(GeneralColor.class);
        backgrounds.put(GeneralColor.GREEN, BACKGROUND_URL + "green.jpg");
        backgrounds.put(GeneralColor.BLUE, BACKGROUND_URL + "blue.jpg");
        backgrounds.put(GeneralColor.YELLOW, BACKGROUND_URL + "yellow.jpg");
        backgrounds.put(GeneralColor.PURPLE, BACKGROUND_URL + "red.jpg");
    }

    public void setBackground() {
        anchorPane.setBackground(new Background(new BackgroundImage(
                new Image(backgrounds.get(ClientInformation.getPlayerColor())),
                null,null, null, new BackgroundSize(
                        BackgroundSize.AUTO, BackgroundSize.AUTO,
                false, false, true, true))));
    }

    private void setTileList() {
        tileList.add(tile1);
        tileList.add(tile2);
        tileList.add(tile3);
        tileList.add(tile4);
    }

    public void setLeaderCards(ArrayList<String> availableTiles) {
        ultimateTiles.clear();
        ultimateTiles = availableTiles;
        for (int i = 0; i < tileList.size(); i++) {
            Image newTile = new Image("client/leader/" + availableTiles.get(i) + ".jpg");
            tileList.get(i).setImage(newTile);
        }
    }

    @FXML
    public String selectTile1() {
        tile2.setDisable(true);
        tile3.setDisable(true);
        tile4.setDisable(true);
        return saveTile1();
    }

    @FXML
    public String selectTile2() {
        tile1.setDisable(true);
        tile3.setDisable(true);
        tile4.setDisable(true);
        return saveTile2();
    }

    @FXML
    public String selectTile3() {
        tile1.setDisable(true);
        tile2.setDisable(true);
        tile4.setDisable(true);
        return saveTile3();
    }

    @FXML
    public String selectTile4() {
        tile1.setDisable(true);
        tile2.setDisable(true);
        tile3.setDisable(true);
        return saveTile4();
    }

    public String saveTile1() {
        return ultimateTiles.get(0);
    }

    public String saveTile2() {
        return ultimateTiles.get(1);
    }

    public String saveTile3() {
        return ultimateTiles.get(2);
    }

    public String saveTile4() {
        return ultimateTiles.get(3);
    }

    public void hideWaiting() {
        waiting.setVisible(false);
        spinner.setVisible(false);
    }
}