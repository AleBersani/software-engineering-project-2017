package it.polimi.ingsw.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ActivatedLeadersMain extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/client/gui/activatedLeaders.fxml"));
        primaryStage.setTitle("Activated Leaders");
        primaryStage.setScene(new Scene(root, 390, 450));
        primaryStage.show();
    }
}
