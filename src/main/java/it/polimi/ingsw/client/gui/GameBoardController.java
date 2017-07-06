package it.polimi.ingsw.client.gui;


import it.polimi.ingsw.client.ClientInformation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameBoardController extends Observable implements Initializable{
    private List<Circle> pawnList;
    private List<StackPane> stackPaneList;
    private List<ImageView> greenTower;
    private List<ImageView> yellowTower;
    private List<ImageView> blueTower;
    private List<ImageView> purpleTower;
    private double startX;
    private double startY;
    private double originX;
    private double originY;
    private Stage playerBoard;
    private static final int FAITH_OFFSET = 35;
    private static final int ADDED_OFFSET = 15;
    private static final int CRITICAL_FAITH_1 = 3;
    private static final int CRITICAL_FAITH_2 = 4;
    private static final int CRITICAL_FAITH_3 = 5;

    @FXML
    private Circle whitePawn;
    @FXML
    private Circle orangePawn;
    @FXML
    private Circle blackPawn;
    @FXML
    private Circle neutralPawn;
    @FXML
    private Circle player1;
    @FXML
    private Circle player2;
    @FXML
    private Circle player3;
    @FXML
    private Circle player4;

    @FXML
    private StackPane g4;
    @FXML
    private StackPane g3;
    @FXML
    private StackPane g2;
    @FXML
    private StackPane g1;
    @FXML
    private StackPane b4;
    @FXML
    private StackPane b3;
    @FXML
    private StackPane b2;
    @FXML
    private StackPane b1;
    @FXML
    private StackPane y4;
    @FXML
    private StackPane y3;
    @FXML
    private StackPane y2;
    @FXML
    private StackPane y1;
    @FXML
    private StackPane p1;
    @FXML
    private StackPane p2;
    @FXML
    private StackPane p3;
    @FXML
    private StackPane p4;
    @FXML
    private StackPane m1;
    @FXML
    private StackPane m2;
    @FXML
    private StackPane m3;
    @FXML
    private StackPane m4;
    @FXML
    private StackPane production1;
    @FXML
    private StackPane harvest1;

    @FXML
    private StackPane production2;
    @FXML
    private StackPane council_palace;
    @FXML
    private StackPane harvest2;
    @FXML
    private StackPane faithPath;

    @FXML
    private ImageView t_g_4;
    @FXML
    private ImageView t_g_3;
    @FXML
    private ImageView t_g_2;
    @FXML
    private ImageView t_g_1;
    @FXML
    private ImageView t_y_4;
    @FXML
    private ImageView t_y_3;
    @FXML
    private ImageView t_y_2;
    @FXML
    private ImageView t_y_1;
    @FXML
    private ImageView t_b_4;
    @FXML
    private ImageView t_b_3;
    @FXML
    private ImageView t_b_2;
    @FXML
    private ImageView t_b_1;
    @FXML
    private ImageView t_p_4;
    @FXML
    private ImageView t_p_3;
    @FXML
    private ImageView t_p_2;
    @FXML
    private ImageView t_p_1;

    @FXML
    private Label infoplayer1;
    @FXML
    private Label infoplayer2;
    @FXML
    private Label infoplayer3;
    @FXML
    private Label playerName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTowers();
        setStackPaneList();
        setPawnList();
        setOtherPlayersInfo();
        for (Circle c : pawnList) {
            if (!c.isDisabled()) checkList();
        }
    }

    public void setTowers() {
        greenTower = new ArrayList<>();
        greenTower.add(t_g_1);
        greenTower.add(t_g_2);
        greenTower.add(t_g_3);
        greenTower.add(t_g_4);

        yellowTower = new ArrayList<>();
        yellowTower.add(t_y_1);
        yellowTower.add(t_y_2);
        yellowTower.add(t_y_3);
        yellowTower.add(t_y_4);

        blueTower = new ArrayList<>();
        blueTower.add(t_b_1);
        blueTower.add(t_b_2);
        blueTower.add(t_b_3);
        blueTower.add(t_b_4);

        purpleTower = new ArrayList<>();
        purpleTower.add(t_p_1);
        purpleTower.add(t_p_2);
        purpleTower.add(t_p_3);
        purpleTower.add(t_p_4);
    }

    public void initGreenTower(ArrayList<String> greenCards) {
        for (int i = 0; i < greenTower.size(); i++) {
            Image newGreenCard = new Image("client/devcards/" + greenCards.get(i) + ".png");
            greenTower.get(i).setImage(newGreenCard);
        }
    }

    public void initYellowTower(ArrayList<String> yellowCards) {
        for (int i = 0; i < yellowTower.size(); i++) {
            Image newYellowCard = new Image("client/devcards/" + yellowCards.get(i) + ".png");
            yellowTower.get(i).setImage(newYellowCard);
        }
    }

    public void initBlueTower(ArrayList<String> blueCards) {
        for (int i = 0; i < blueTower.size(); i++) {
            Image newBlueCard = new Image("client/devcards/" + blueCards.get(i) + ".png");
            blueTower.get(i).setImage(newBlueCard);
        }
    }

    public void initPurpleTower(ArrayList<String> purpleCards) {
        for (int i = 0; i < purpleTower.size(); i++) {
            Image newPurpleCard = new Image("client/devcards/" + purpleCards.get(i) + ".png");
            purpleTower.get(i).setImage(newPurpleCard);
        }
    }

    private void setStackPaneList() {
        stackPaneList = new ArrayList<>();
        stackPaneList.add(y1);
        stackPaneList.add(y2);
        stackPaneList.add(y3);
        stackPaneList.add(y4);
        stackPaneList.add(g1);
        stackPaneList.add(g2);
        stackPaneList.add(g3);
        stackPaneList.add(g4);
        stackPaneList.add(b1);
        stackPaneList.add(b2);
        stackPaneList.add(b3);
        stackPaneList.add(b4);
        stackPaneList.add(p1);
        stackPaneList.add(p2);
        stackPaneList.add(p3);
        stackPaneList.add(p4);
        stackPaneList.add(m1);
        stackPaneList.add(m2);
        stackPaneList.add(m3);
        stackPaneList.add(m4);
        stackPaneList.add(production1);
        stackPaneList.add(harvest1);
    }

    public void setPawnList() {
        pawnList = new ArrayList<>();
        pawnList.add(orangePawn);
        pawnList.add(blackPawn);
        pawnList.add(whitePawn);
        pawnList.add(neutralPawn);
    }

    public void checkList() {
        for (Circle pawn : pawnList) {
            pawn.setOnMouseClicked(event -> moveCircle(pawn));
        }
    }

    public void moveCircle(Circle circle) {
        circle.setOnMousePressed((MouseEvent e) -> {
            Circle c = (Circle) (e.getSource());
            c.setCursor(Cursor.HAND);
            startX = e.getSceneX();
            startY = e.getSceneY();
            originX = startX;
            originY = startY;
            c.toFront();

            circle.setOnMouseDragged((MouseEvent t) -> {
                double offsetX = t.getSceneX() - startX;
                double offsetY = t.getSceneY() - startY;

                Circle d = (Circle) (t.getSource());
                d.setCenterX(d.getCenterX() + offsetX);
                d.setCenterY(d.getCenterY() + offsetY);

                startX = t.getSceneX();
                startY = t.getSceneY();

                circle.setOnMouseReleased((MouseEvent event) -> {
                    Circle newX = (Circle) event.getSource();
                    System.out.println(newX);
                    circle.setCenterX(newX.getCenterX());
                    circle.setCenterY(newX.getCenterY());
                    for (StackPane stackPane : stackPaneList) {
                        if (circle.getBoundsInParent().intersects(stackPane.getBoundsInParent())
                                && stackPane.getChildren().isEmpty()) {
                            stackPane.getChildren().add(circle);
                            circle.relocate(stackPane.getHeight() / 2, stackPane.getWidth() / 2);
                            circle.setDisable(true);
                            break;
                        }
                    }
                    if ((circle.getBoundsInParent().intersects(council_palace.getBoundsInParent()))) {
                        setCouncil_palace(circle);
                    }
                    if ((circle.getBoundsInParent().intersects(production2.getBoundsInParent()))) {
                        setProduction2(circle);
                    }
                    if ((circle.getBoundsInParent().intersects(harvest2.getBoundsInParent()))) {
                        setHarvest2(circle);
                    }
                });
            });
        });
    }

    private void setCouncil_palace(Circle circle) {
        council_palace.getChildren().add(circle);
        council_palace.setAlignment(circle, Pos.CENTER_LEFT);
        circle.setTranslateX(7.5*(council_palace.getChildren().size()-1));
    }

    private void setProduction2(Circle circle) {
        production2.getChildren().add(circle);
        production2.setAlignment(circle, Pos.CENTER_LEFT);
        circle.setTranslateX(7.0*(production2.getChildren().size()-1));
    }

    private void setHarvest2(Circle circle) {
        harvest2.getChildren().add(circle);
        harvest2.setAlignment(circle, Pos.CENTER_LEFT);
        circle.setTranslateX(7.0*(harvest2.getChildren().size()-1));
    }

    public void undoAction(Circle pawn) {
        pawn.setCenterY(originX);
        pawn.setCenterY(originY);
    }

    public void setFaith(int faithPoints, String player) {
        double newLayoutX;
        for (int i = 0; i < faithPath.getChildren().size(); i++) {
            if (faithPath.getChildren().get(i).getId().equals(player)) {
                faithPath.getChildren().get(i).setTranslateX(FAITH_OFFSET*faithPoints);
                newLayoutX = faithPath.getChildren().get(i).getLayoutX();
                if (faithPoints == CRITICAL_FAITH_1 || faithPoints == CRITICAL_FAITH_2 ||
                        faithPoints == CRITICAL_FAITH_3){
                    faithPath.getChildren().get(i).setTranslateX(newLayoutX + ADDED_OFFSET);
                }
            }
        }
    }

    @FXML
    public void showPlayerBoard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/gui/playerboard.fxml"));
        Parent playerBoard_parent = (Parent) loader.load();
        playerBoard = new Stage();
        playerBoard.setScene(new Scene(playerBoard_parent));
        playerBoard.show();
        playerBoard.toFront();
        playerBoard.setResizable(false);
        this.addObserver((Observer) loader.getController());
    }

    @FXML
    public void hidePlayerBoard() {
        if (playerBoard.getScene().getWindow().isShowing()) {
            playerBoard.hide();
        }
    }

    public void setOtherPlayersInfo() {
        infoplayer1.setText("qualcosa");
        infoplayer2.setText("bho");
        infoplayer3.setText("teso");
    }

    @FXML
    public void hideCard(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        Image img = imageView.getImage();
        System.out.println("img salvata");
        setChanged();
        notifyObservers(img);
        imageView.setImage(null);
    }

    public void setPlayerName() {
        playerName.setText(ClientInformation.getPlayerName());
    }

}
