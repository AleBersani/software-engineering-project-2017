package it.polimi.ingsw.client.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class YellowCardsController {
    @FXML
    private ImageView card;
    @FXML
    private ToggleGroup choiceToggleGroup;

    public void initialize() {


    }

    private void setImage() {
        Image img = new Image("");
        card.setImage(img);
    }

    @FXML
    private void getToggle() {
        choiceToggleGroup.getSelectedToggle();
        System.out.println(choiceToggleGroup.getSelectedToggle().toString());
    }

}
