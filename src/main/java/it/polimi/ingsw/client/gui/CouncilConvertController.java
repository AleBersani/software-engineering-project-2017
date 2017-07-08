package it.polimi.ingsw.client.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.middleware.ClientSender;
import it.polimi.ingsw.client.middleware.ClientSenderHandler;
import it.polimi.ingsw.shared.requests.clientserver.BaseInformation;
import it.polimi.ingsw.shared.requests.clientserver.ChosenCouncilPrivilege;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CouncilConvertController {
    private List<Integer> choices;

    @FXML
    private JFXButton convert;

    @FXML
    private ToggleGroup conversionToggleGroup;

    public CouncilConvertController() {
        choices = new ArrayList<>();
    }

    public void initialize() {}

    @FXML
    public void getConversion() {
        JFXRadioButton selectedToggle = (JFXRadioButton) conversionToggleGroup.getSelectedToggle();
        System.out.println(selectedToggle.getId());
        switch (selectedToggle.getId()) {
            case "choice0" :
                choices.add(0);
            case "choice1" :
                choices.add(1);
            case "choice2" :
                choices.add(2);
            case "choice3" :
                choices.add(3);
            case "choice4" :
                choices.add(4);
        }

        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new ChosenCouncilPrivilege(
                new BaseInformation(ClientInformation.getCurrentGameId(), ClientInformation.getPlayerName()),
                choices, ClientInformation.getLastBoardAction()));
    }
}
