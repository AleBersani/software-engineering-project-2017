package it.polimi.ingsw.client;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import it.polimi.ingsw.client.connection.ConnectionStarter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ClientStarterController {
    private final static Logger LOGGER = Logger.getLogger(ClientStarterController.class.getName());

    private final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    private ScheduledFuture futureScheduled;
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
    private Label IP;
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
        MediaPlayer introSong = new MediaPlayer(song);
        introSong.play();
    }

    public void rotateImage() {
        littleLolloJunior.setRotate(littleLolloJunior.getRotate() + 5);
        littleLolloSenior.setRotate(littleLolloSenior.getRotate() - 5);
    }

    public void stopRotate() {
        futureScheduled.cancel(true);
    }

    public void createConnection() {
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

    private String selectedConnection() {
        JFXToggleButton selectedRadioButton = (JFXToggleButton)connectionToggleGroup.getSelectedToggle();
        String selected = selectedRadioButton.getText();
        if ("Socket".equals(selected)) {
            selected = selected.concat("_").concat(address.getText());
        }
        return selected;
    }

    public void showIP(MouseEvent event) {
        IP.setDisable(false);
        address.setDisable(false);
    }

    public void hideIP(MouseEvent event) {
        IP.setDisable(true);
        address.setDisable(true);
    }

    public void changeColors() {

    }
}
