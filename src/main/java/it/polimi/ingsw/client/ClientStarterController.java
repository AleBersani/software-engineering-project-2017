package it.polimi.ingsw.client;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import it.polimi.ingsw.client.connection.ConnectionStarter;
import it.polimi.ingsw.client.gui.notify.PlayerLoginNotifier;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientStarterController implements Observer {
    private static final Logger LOGGER = Logger.getLogger(ClientStarterController.class.getName());

    private final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(1);

    private ScheduledFuture futureScheduled;
    private MediaPlayer introSong;
    private ConnectionStarter connectionStarter;

    @FXML
    private ImageView littleLolloJunior;
    @FXML
    private ImageView littleLolloSenior;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private ToggleGroup connectionToggleGroup;
    @FXML
    private Label ip;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXToggleButton rmiRadio;
    @FXML
    private JFXToggleButton socketRadio;

    public void initialize() {
        futureScheduled = EXECUTOR_SERVICE.scheduleAtFixedRate
                (this::rotateImage, 0, 45, TimeUnit.MILLISECONDS);

        connectionStarter = null;

        Media song = new Media(new File("resources/client/intro-song.mp3").toURI().toString());
        introSong = new MediaPlayer(song);
        introSong.play();

        PlayerLoginNotifier.getInstance().addObserver(this);
    }

    private void rotateImage() {
        littleLolloJunior.setRotate(littleLolloJunior.getRotate() + 5);
        littleLolloSenior.setRotate(littleLolloSenior.getRotate() - 5);
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean successful = (boolean)arg;
        if (successful) {
            showGameChoice();
        } else {
            inputError();
        }
    }

    private void showGameChoice() {
        Platform.runLater(() ->  {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/gui/gamechoice.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                closeScene();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot launch game choice", e);
            }
        });
    }

    public void closeScene() {
        stopRotate();
        introSong.stop();
        introSong.dispose();
        PlayerLoginNotifier.getInstance().deleteObserver(this);
        ClientStarterMain.getStage().close();
    }

    private void stopRotate() {
        futureScheduled.cancel(true);
        EXECUTOR_SERVICE.shutdownNow();
    }

    private void inputError() {
        usernameField.setUnFocusColor(new Color(1.0, 0,0,1));
        passwordField.setUnFocusColor(new Color(1.0, 0,0,1));
    }

    @FXML
    public void onConnect() {
        createConnection();
        connectionStarter.authenticate(usernameField.getText(), passwordField.getText(), false);

    }

    @FXML
    public void onRegister() {
        createConnection();
        connectionStarter.authenticate(usernameField.getText(), passwordField.getText(), true);
    }

    private void createConnection() {
        if (connectionStarter == null) {
            String selected = selectedConnection();
            connectionStarter = new ConnectionStarter(selected);
            connectionStarter.startConnection();
            disableRadioButton();
        }
    }

    private void disableRadioButton() {
        rmiRadio.setDisable(true);
        socketRadio.setDisable(true);
    }

    private String selectedConnection() {
        JFXToggleButton selectedRadioButton = (JFXToggleButton)connectionToggleGroup.getSelectedToggle();
        String selected = selectedRadioButton.getText();
        if ("Socket".equals(selected)) {
            selected = selected.concat("_").concat(address.getText());
        }
        return selected;
    }

    @FXML
    public void showIP() {
        ip.setDisable(false);
        address.setDisable(false);
    }

    @FXML
    public void hideIP() {
        ip.setDisable(true);
        address.setDisable(true);
    }
}
