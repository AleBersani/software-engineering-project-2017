package it.polimi.ingsw.client.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.util.Observable;
import java.util.Observer;

public class PlayerboardController implements Observer {
    @FXML
    public ImageView card1;

    @Override
    public void update(Observable o, Object arg) {
    }
}