package it.polimi.ingsw.client;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class LoginController {
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture futureScheduled;

    @FXML
    private ImageView lollo;

    public void initialize() {
        futureScheduled = executorService.scheduleAtFixedRate
                (this::rotateImage, 0, 45, TimeUnit.MILLISECONDS);
    }

    public void rotateImage() {
        lollo.setRotate(lollo.getRotate() + 5);
    }

    public void stopRotate() {
        futureScheduled.cancel(true);
    }
}
