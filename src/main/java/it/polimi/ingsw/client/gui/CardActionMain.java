package it.polimi.ingsw.client.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CardActionMain extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/client/gui/cardAction.fxml"));
        primaryStage.setTitle("Card Action");
        primaryStage.setScene(new Scene(root, 200, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
