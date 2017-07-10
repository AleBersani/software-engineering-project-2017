package it.polimi.ingsw.client.gui;

import com.jfoenix.controls.JFXRadioButton;
import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.middleware.ClientSender;
import it.polimi.ingsw.client.middleware.ClientSenderHandler;
import it.polimi.ingsw.client.model.BoardLight;
import it.polimi.ingsw.shared.requests.clientserver.BaseInformation;
import it.polimi.ingsw.shared.requests.clientserver.ChosenLorenzo;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ActivatedLeadersController {
    private final ToggleGroup group = new ToggleGroup();
    private BoardLight boardLight;
    private String chosenLeaderName;

    @FXML
    private VBox list1;
    @FXML
    private VBox list2;

    public void initialize() {
        boardLight = BoardLight.getInstance();
        setButtons();
    }

    private void setButtons() {
        for (int i = 0; i < boardLight.getPlayerLights().size(); i++) {
           for (int j = 0; j < boardLight.getPlayerLights().get(i).getActivatedLeaders().size(); j++) {
                if (!boardLight.getPlayerLights().get(i).getActivatedLeaders().isEmpty()) {
                    JFXRadioButton newRadio = new JFXRadioButton(
                           boardLight.getPlayerLights().get(i).getActivatedLeaders().get(j).getName());
                    newRadio.setToggleGroup(group);
                    newRadio.setFocusTraversable(false);
                    newRadio.setSelectedColor(Color.INDIANRED);
                    list1.getChildren().add(newRadio);
                    if (list1.getChildren().size() > 8) {
                        list2.getChildren().add(newRadio);
                        list1.getChildren().remove(newRadio);
                    }
                }
            }
        }
    }

    public void getSelectedLeader() {
        group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (group.getSelectedToggle() != null) {
                System.out.println("ciao");
                System.out.println(((JFXRadioButton)group.getSelectedToggle()).getId());
                for (int i = 0; i < list1.getChildren().size(); i++) {
                    for (int j = 0; j < boardLight.getPlayerLights().get(i).getActivatedLeaders().size(); j++) {
                        if (((JFXRadioButton)group.getSelectedToggle()).getText().equalsIgnoreCase(
                                boardLight.getPlayerLights().get(i).getActivatedLeaders().get(j).getName())) {
                            chosenLeaderName = boardLight.getPlayerLights().get(i).getActivatedLeaders().get(j).getName();
                        }
                    }
                }
                for (int i = 0; i < list2.getChildren().size(); i++) {
                    for (int j = 0; j < boardLight.getPlayerLights().get(i).getActivatedLeaders().size(); j++) {
                        if (((JFXRadioButton)group.getSelectedToggle()).getText().equalsIgnoreCase(
                                boardLight.getPlayerLights().get(i).getActivatedLeaders().get(j).getName())) {
                            chosenLeaderName = boardLight.getPlayerLights().get(i).getActivatedLeaders().get(j).getName();
                        }
                    }
                }
            }
        });
    }

    @FXML
    public void sendLeaderChosen() {
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new ChosenLorenzo(
                new BaseInformation(ClientInformation.getCurrentGameId(), ClientInformation.getPlayerName()),
                chosenLeaderName));
        Stage stage = (Stage)list1.getScene().getWindow();
        stage.close();
    }
}

