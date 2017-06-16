package it.polimi.ingsw.client;

import it.polimi.ingsw.client.connection.middleware.ClientSender;
import it.polimi.ingsw.client.connection.middleware.ClientSenderHandler;
import it.polimi.ingsw.client.connection.rmi.RMIClient;
import it.polimi.ingsw.client.connection.socket.SocketClient;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    private final static Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    private final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    private ScheduledFuture futureScheduled;

    @FXML
    private ImageView littleLolloJunior;
    @FXML
    private ImageView littleLolloSenior;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ToggleGroup connectionToggleGroup;

    public void initialize() {
        futureScheduled = EXECUTOR_SERVICE.scheduleAtFixedRate
                (this::rotateImage, 0, 45, TimeUnit.MILLISECONDS);

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

    @FXML
    public void onConnect() {
        RadioButton selectedRadioButton = (RadioButton)connectionToggleGroup.getSelectedToggle();
        String selected = selectedRadioButton.getText();
        ClientSender clientSender = new ClientSenderHandler();
        if ("Socket".equals(selected)) {
            try {
                SocketClient.startSocketClient("127.0.0.1");
                clientSender.login(usernameField.getText(), passwordField.getText());
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot connect via socket", e);
            }
        } else if ("RMI".equals(selected)) {
            ClientSenderHandler.setSocket(false);
            try {
                RMIClient.startRMIClient();
                clientSender.login(usernameField.getText(), passwordField.getText());
            } catch (RemoteException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot connect via RMI", e);
            }
        }
    }
}
