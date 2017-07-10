package it.polimi.ingsw.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExcommunicationMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/client/gui/excommunication.fxml"));
        primaryStage.setTitle("Excommunication");
        primaryStage.setScene(new Scene(root, 270, 300));
        primaryStage.show();
    }
}
