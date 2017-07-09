package it.polimi.ingsw.client.gui;

import com.jfoenix.controls.JFXRadioButton;
import it.polimi.ingsw.client.model.BoardLight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class CardActionController {
    private List<JFXRadioButton> greenCards;
    private List<JFXRadioButton> yellowCards;
    private List<JFXRadioButton> blueCards;
    private List<JFXRadioButton> purpleCards;
    private BoardLight boardLight;

    @FXML
    private JFXRadioButton t_g_4;
    @FXML
    private JFXRadioButton t_g_3;
    @FXML
    private JFXRadioButton t_g_2;
    @FXML
    private JFXRadioButton t_g_1;
    @FXML
    private JFXRadioButton t_y_4;
    @FXML
    private JFXRadioButton t_y_3;
    @FXML
    private JFXRadioButton t_y_2;
    @FXML
    private JFXRadioButton t_y_1;
    @FXML
    private JFXRadioButton t_b_4;
    @FXML
    private JFXRadioButton t_b_3;
    @FXML
    private JFXRadioButton t_b_2;
    @FXML
    private JFXRadioButton t_b_1;
    @FXML
    private JFXRadioButton t_p_4;
    @FXML
    private JFXRadioButton t_p_3;
    @FXML
    private JFXRadioButton t_p_2;
    @FXML
    private JFXRadioButton t_p_1;

    @FXML
    private ToggleGroup cardsToggleGroup;

    public void initialize() {
        boardLight = BoardLight.getInstance();
        setCardLists();
        setGreenCards();
        setYellowCards();
        setBlueCards();
        setPurpleCards();
        getCard();
    }

    private void setCards(List<JFXRadioButton> buttonList, JFXRadioButton button1, JFXRadioButton button2,
                          JFXRadioButton button3, JFXRadioButton button4) {
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);

    }

    private void setCardLists() {
        greenCards = new ArrayList<>();
        setCards(greenCards, t_g_4, t_g_3, t_g_2, t_g_1);

        yellowCards = new ArrayList<>();
        setCards(yellowCards, t_y_4, t_y_3, t_y_2, t_y_1);

        blueCards = new ArrayList<>();
        setCards(blueCards, t_b_4, t_b_3, t_b_2, t_b_1);

        purpleCards = new ArrayList<>();
        setCards(purpleCards, t_p_4, t_p_3, t_p_2, t_p_1);
    }

    public void setGreenCards() {
        for (int i = 0; i < boardLight.getGreenTower().size(); i++) {
            if (!boardLight.getGreenTower().get(i).getCard().getName().isEmpty()) {
                greenCards.get(i).setText(boardLight.getGreenTower().get(i).getCard().getName());
            }
        }
    }

    public void setYellowCards() {
        for (int i = 0; i < boardLight.getYellowTower().size(); i++) {
            if (!boardLight.getYellowTower().get(i).getCard().getName().isEmpty()) {
                yellowCards.get(i).setText(boardLight.getYellowTower().get(i).getCard().getName());
            }
        }
    }

    public void setBlueCards() {
        for (int i = 0; i < boardLight.getBlueTower().size(); i++) {
            if (!boardLight.getBlueTower().get(i).getCard().getName().isEmpty()) {
                blueCards.get(i).setText(boardLight.getBlueTower().get(i).getCard().getName());
            }
        }
    }

    public void setPurpleCards() {
        for (int i = 0; i < boardLight.getPurpleTower().size(); i++) {
            if (!boardLight.getPurpleTower().get(i).getCard().getName().isEmpty()) {
                purpleCards.get(i).setText(boardLight.getPurpleTower().get(i).getCard().getName());
            }
        }
    }


    public void getCard() {
        cardsToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (cardsToggleGroup.getSelectedToggle() != null) {
                    System.out.println("ciao");
                    System.out.println(((JFXRadioButton)cardsToggleGroup.getSelectedToggle()).getId());
                    for (int i = 0; i < boardLight.getGreenTower().size(); i++) {
                        if (boardLight.getGreenTower().get(i).getSlotLight().getBoardIdentifier().toString().equalsIgnoreCase(
                                ((JFXRadioButton)cardsToggleGroup.getSelectedToggle()).getId())) {
                            System.out.println(boardLight.getGreenTower().get(i).getSlotLight().getBoardIdentifier());
                        }
                    }
                    for (int i = 0; i < boardLight.getYellowTower().size(); i++) {
                        if (boardLight.getYellowTower().get(i).getSlotLight().getBoardIdentifier().toString()
                                .equalsIgnoreCase(((JFXRadioButton)cardsToggleGroup.getSelectedToggle()).getId())) {
                            System.out.println(boardLight.getYellowTower().get(i).getSlotLight().getBoardIdentifier());
                        }
                    }
                    for (int i = 0; i < boardLight.getBlueTower().size(); i++) {
                        if (boardLight.getBlueTower().get(i).getSlotLight().getBoardIdentifier().toString()
                                .equalsIgnoreCase(((JFXRadioButton)cardsToggleGroup.getSelectedToggle()).getId())) {
                            System.out.println(boardLight.getBlueTower().get(i).getSlotLight().getBoardIdentifier());
                        }
                    }
                    for (int i = 0; i < boardLight.getPurpleTower().size(); i++) {
                        if (boardLight.getPurpleTower().get(i).getSlotLight().getBoardIdentifier().toString().equalsIgnoreCase(((JFXRadioButton)cardsToggleGroup.getSelectedToggle()).getId())) {
                            System.out.println(boardLight.getPurpleTower().get(i).getSlotLight().getBoardIdentifier());
                        }
                    }
                }
            }
        });
    }




}
