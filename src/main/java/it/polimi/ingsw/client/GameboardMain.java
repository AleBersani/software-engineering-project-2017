package it.polimi.ingsw.client;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.script.Bindings;

public class GameboardMain extends Application {

    @FXML
    public StackPane a;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/client/gameboard.fxml"));
        primaryStage.setTitle("Lorenzo il Magnifico - Gameboard");
        primaryStage.setScene(new Scene(root, 1132, 685));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
