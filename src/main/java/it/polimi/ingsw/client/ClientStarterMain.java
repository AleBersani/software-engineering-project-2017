package it.polimi.ingsw.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientStarterMain extends Application {
    private static Stage stage;
    private ClientStarterController clientStarterController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        FXMLLoader loaderClientStarter = new FXMLLoader(getClass().getResource("/client/clientstarter.fxml"));
        Parent root = loaderClientStarter.load();
        clientStarterController = loaderClientStarter.getController();
        primaryStage.setTitle("Lorenzo il Magnifico");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.show();
    }

    @Override
    public void stop() {
        clientStarterController.closeScene();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }
}
