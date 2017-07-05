package it.polimi.ingsw.client.gui;

import com.jfoenix.controls.JFXButton;
import it.polimi.ingsw.client.gui.notify.GameChoiceNotifier;
import it.polimi.ingsw.client.middleware.ClientSender;
import it.polimi.ingsw.client.middleware.ClientSenderHandler;
import it.polimi.ingsw.shared.support.GameStartType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameChoiceController implements Observer {
    private final static Logger LOGGER = Logger.getLogger(GameChoiceController.class.getName());

    @FXML
    private JFXButton newGame;
    @FXML
    private JFXButton resumeGame;

    public void initialize() {
        GameChoiceNotifier.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean accepted = (boolean)arg;
        if (accepted) {
            startLeaderChoice();
        } else {

        }
    }

    private void startLeaderChoice() {
        Platform.runLater(() ->  {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/gui/leaderchoice.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                closeStage();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot launch leader choice board", e);
            }
        });
    }

    private void closeStage() {
        Stage stage = (Stage)newGame.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void startNewGame() {
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.choseGameType(GameStartType.NEW);
    }

    @FXML
    public void resumeGame() {
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.choseGameType(GameStartType.RESUME);
    }
}
