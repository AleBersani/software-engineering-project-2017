package it.polimi.ingsw.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TileChoiceMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/client/gui/tileChoice.fxml"));
        primaryStage.setTitle("TileChoice");
        primaryStage.setScene(new Scene(root, 800, 555));
        primaryStage.show();
    }
}
