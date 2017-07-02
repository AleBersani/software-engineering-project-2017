package it.polimi.ingsw.client.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TileChoiceController implements Initializable {
    private ArrayList<ImageView> tileList;
    private ArrayList<String> ultimateTiles;

    @FXML
    private ImageView tile1, tile2, tile3, tile4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tileList = new ArrayList<>();
        ultimateTiles = new ArrayList<>();
        setTileList();
    }

    public void setTileList() {
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

    public void clearTileList() { tileList.clear(); }

    @FXML
    public String selectTile1() {
        String selectedTile1 = tile1.getImage().toString();
        return selectedTile1;
    }

    @FXML
    public String selectTile2() {
        String selectedTile2 = tile2.getImage().toString();
        return selectedTile2;
    }

    @FXML
    public String selectTile3() {
        String selectedTile3 = tile3.getImage().toString();
        return selectedTile3;
    }

    @FXML
    public String selectTile4() {
        String selectedTile4 = tile4.getImage().toString();
        return selectTile4();
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
}
