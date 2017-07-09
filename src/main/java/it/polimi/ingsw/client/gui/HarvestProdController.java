package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.client.ClientInformation;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.util.ArrayList;
import java.util.List;

public class HarvestProdController {
    private List<ImageView> cards;
    private List<CheckBox> buttons;

    @FXML
    private ImageView card1;
    @FXML
    private ImageView card2;
    @FXML
    private ImageView card3;
    @FXML
    private ImageView card4;
    @FXML
    private ImageView card5;
    @FXML
    private ImageView card6;

    @FXML
    private CheckBox num1;
    @FXML
    private CheckBox num2;
    @FXML
    private CheckBox num3;
    @FXML
    private CheckBox num4;
    @FXML
    private CheckBox num5;
    @FXML
    private CheckBox num6;



    public void initialize() {
        setUp();

    }

    private void setUp() {
        cards = new ArrayList<>();
        buttons = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);

        buttons.add(num1);
        buttons.add(num2);
        buttons.add(num3);
        buttons.add(num4);
        buttons.add(num5);
        buttons.add(num6);
    }

    @FXML
    private void getCards() {
        for (int i = 0; i < cards.size(); i++) {
            if (buttons.get(i).isSelected()) {
                System.out.println(cards.get(i).getId());
            }
        }
    }

    private void setImage() {
        for (ImageView imgview :
                cards) {
            Image img = new Image("");
            imgview.setImage(img);
        }
    }
}
