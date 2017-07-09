package it.polimi.ingsw.client.gui;

import com.jfoenix.controls.JFXRadioButton;
import it.polimi.ingsw.client.model.BoardLight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ActivatedLeadersController {
    final ToggleGroup group = new ToggleGroup();
    private BoardLight boardLight;

    @FXML
    private VBox list1;
    @FXML
    private VBox list2;

    public void initialize() {
        boardLight = BoardLight.getInstance();
        setButtons();
    }

    public void setButtons() {
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
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    System.out.println("ciao");
                    System.out.println(((JFXRadioButton)group.getSelectedToggle()).getId());
                    for (int i = 0; i < list1.getChildren().size(); i++) {
                        for (int j = 0; j < boardLight.getPlayerLights().get(i).getActivatedLeaders().size(); j++) {
                            if (((JFXRadioButton)group.getSelectedToggle()).getText().equalsIgnoreCase(
                                    boardLight.getPlayerLights().get(i).getActivatedLeaders().get(j).getName())) {
                                System.out.println( boardLight.getPlayerLights().get(i).getActivatedLeaders().get(j).getName());
                            }
                        }
                    }
                    for (int i = 0; i < list2.getChildren().size(); i++) {
                        for (int j = 0; j < boardLight.getPlayerLights().get(i).getActivatedLeaders().size(); j++) {
                            if (((JFXRadioButton)group.getSelectedToggle()).getText().equalsIgnoreCase(
                                    boardLight.getPlayerLights().get(i).getActivatedLeaders().get(j).getName())) {
                                System.out.println( boardLight.getPlayerLights().get(i).getActivatedLeaders().get(j).getName());
                            }
                        }
                    }
                }
            }
        });
    }
}

