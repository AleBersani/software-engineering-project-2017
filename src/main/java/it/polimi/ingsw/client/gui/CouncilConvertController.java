package it.polimi.ingsw.client.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class CouncilConvertController {
    @FXML
    private JFXButton convert;

    @FXML
    private ToggleGroup conversionToggleGroup;

    public void initialize() {

    }

    @FXML
    public int getConversion() {
        int index = 0;
        JFXRadioButton selectedToggle = (JFXRadioButton) conversionToggleGroup.getSelectedToggle();
        System.out.println(selectedToggle.getId());
        switch (selectedToggle.getId()) {
            case "choice0" : index = 0;
                break;
            case "choice1" : index = 1;
                break;
            case "choice2" : index = 2;
                break;
            case "choice3" : index = 3;
                break;
            case "choice4" : index = 4;
                break;
        }
        return index;

    }
}
