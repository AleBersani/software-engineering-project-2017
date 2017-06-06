package it.polimi.ingsw.client;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class LoginController {
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private ScheduledFuture futureScheduled;
    private MediaPlayer introSong;

    @FXML
    private ImageView littleLolloJunior;

    @FXML
    public ImageView littleLolloSenior;

    public void initialize() {
        futureScheduled = executorService.scheduleAtFixedRate
                (this::rotateImage, 0, 45, TimeUnit.MILLISECONDS);

        Media song = new Media(new File("resources/client/intro-song.mp3").toURI().toString());
        introSong = new MediaPlayer(song);
        introSong.play();
    }

    public void rotateImage() {
        littleLolloJunior.setRotate(littleLolloJunior.getRotate() + 5);
        littleLolloSenior.setRotate(littleLolloSenior.getRotate() - 5);
    }

    public void stopRotate() {
        futureScheduled.cancel(true);
    }
}
