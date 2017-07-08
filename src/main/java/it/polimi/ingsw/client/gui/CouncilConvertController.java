package it.polimi.ingsw.client.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.middleware.ClientSender;
import it.polimi.ingsw.client.middleware.ClientSenderHandler;
import it.polimi.ingsw.shared.requests.clientserver.BaseInformation;
import it.polimi.ingsw.shared.requests.clientserver.ChosenCouncilPrivilege;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CouncilConvertController {
    private List<Integer> choices;
    private List<JFXRadioButton> radioButtons;

    @FXML
    private JFXRadioButton choice0;
    @FXML
    private JFXRadioButton choice1;
    @FXML
    private JFXRadioButton choice2;
    @FXML
    private JFXRadioButton choice3;
    @FXML
    private JFXRadioButton choice4;

    public CouncilConvertController() {
        choices = new ArrayList<>();
    }

    public void initialize() {
        setRadioButtons();
        getRadioButtons();
    }

    public void setRadioButtons() {
        radioButtons = new ArrayList<>();
        radioButtons.add(choice0);
        radioButtons.add(choice1);
        radioButtons.add(choice2);
        radioButtons.add(choice3);
        radioButtons.add(choice4);
    }

    public void getRadioButtons() {
        final int[] count = {0};
        for (JFXRadioButton radio : radioButtons) {
            radio.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue == true) {
                        radio.setMouseTransparent(true);
                        count[0]++;
                        if (count[0] == 2) {
                            radioButtons.forEach(jfxRadioButton -> jfxRadioButton.setMouseTransparent(true));
                        }
                    }
                }
            });
        }
    }

    @FXML
    public void getConversion() {
        for (JFXRadioButton radio : radioButtons) {
            if (radio.isSelected()) {
                switch (radio.getId()) {
                    case "choice0":
                        choices.add(0);
                        break;
                    case "choice1":
                        choices.add(1);
                        break;
                    case "choice2":
                        choices.add(2);
                        break;
                    case "choice3":
                        choices.add(3);
                        break;
                    case "choice4":
                        choices.add(4);
                        break;
                }
            }
        }
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new ChosenCouncilPrivilege(
                new BaseInformation(ClientInformation.getCurrentGameId(), ClientInformation.getPlayerName()),
                choices, ClientInformation.getLastBoardAction()));
    }







}
