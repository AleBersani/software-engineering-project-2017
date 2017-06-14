package it.polimi.ingsw.client;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class LoginController {
    private final static Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    private final ScheduledExecutorService EXECUTORSERVICE = Executors.newSingleThreadScheduledExecutor();

    private ScheduledFuture futureScheduled;

    @FXML
    private ImageView littleLolloJunior;
    @FXML
    private ImageView littleLolloSenior;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public void initialize() {
        futureScheduled = EXECUTORSERVICE.scheduleAtFixedRate
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
        /*try {
            PlayerDetails playerDetails = new PlayerDetails();
            playerDetails.setPlayerName(usernameField.getText());
            Client client = new Client(playerDetails, passwordField.getText());
            RMIClient rmiClient = new RMIClient();
            rmiClient.getReceiver().recordClient(client);
        } catch (RemoteException | NotBoundException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown: ", e);
        }*/
    }
}
