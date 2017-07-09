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
import javafx.stage.Stage;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

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

    @FXML
    private JFXButton convert;

    public CouncilConvertController() {
        choices = new ArrayList<>();
    }

    public void initialize() {
        setRadioButtons();
        getRadioButtons();
        convert.setMouseTransparent(true);
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
        System.out.println(ClientInformation.getNumberOfCouncilPrivilegeToChoose());
        for (JFXRadioButton radio : radioButtons) {
            radio.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) {
                        radio.setMouseTransparent(true);
                        count[0]++;
                        if (count[0] == ClientInformation.getNumberOfCouncilPrivilegeToChoose().get()) {
                            System.out.println(count[0]);
                            radioButtons.forEach(jfxRadioButton -> jfxRadioButton.setMouseTransparent(true));
                            convert.setMouseTransparent(false);
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
                        System.out.println(0);
                        break;
                    case "choice1":
                        choices.add(1);
                        System.out.println(1);
                        break;
                    case "choice2":
                        choices.add(2);
                        System.out.println(2);
                        break;
                    case "choice3":
                        choices.add(3);
                        System.out.println(3);
                        break;
                    case "choice4":
                        choices.add(4);
                        System.out.println(4);
                        break;
                }
            }
        }
        System.out.println(choices.toString());
        System.out.println(ClientInformation.getLastBoardAction());
        ClientSender clientSender = new ClientSenderHandler();
        ChosenCouncilPrivilege chosenCouncilPrivilege = new ChosenCouncilPrivilege(
                new BaseInformation(ClientInformation.getCurrentGameId(), ClientInformation.getPlayerName()),
                choices, ClientInformation.getLastBoardAction());

        clientSender.sendToServer(chosenCouncilPrivilege);
        if (convert.getScene().getWindow().isShowing()) {
            Stage stage = (Stage) convert.getScene().getWindow();
            stage.close();
        }

    }
}
