package it.polimi.ingsw.client.gui;


import it.polimi.ingsw.client.ClientInformation;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.GridPane;
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
    private double startX, startY, originX, originY;
    private Stage playerBoard;
    private static final int FAITH_OFFSET = 35;
    private static final int ADDED_OFFSET = 15;
    private static final int CRITICAL_FAITH_1 = 3;
    private static final int CRITICAL_FAITH_2 = 4;
    private static final int CRITICAL_FAITH_3 = 5;

    @FXML
    private Circle whitePawn, orangePawn, blackPawn, neutralPawn, player1, player2, player3, player4;
    @FXML
    private StackPane G4, G3, G2, G1, B4, B3, B2, B1, Y4, Y3, Y2, Y1, P1, P2, P3, P4, M1, M2, M3, PRODUCTION_1, HARVEST_1;
    @FXML
    private StackPane PRODUCTION_2, COUNCIL_PALACE, HARVEST_2, faithPath;

    @FXML
    private ImageView T_G_4, T_G_3, T_G_2, T_G_1, T_Y_4, T_Y_3, T_Y_2, T_Y_1, T_B_4, T_B_3, T_B_2, T_B_1,
            T_P_4, T_P_3, T_P_2, T_P_1;
    @FXML
    private Label infoplayer1, infoplayer2, infoplayer3, playerName;

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
        greenTower.add(T_G_1);
        greenTower.add(T_G_2);
        greenTower.add(T_G_3);
        greenTower.add(T_G_4);

        yellowTower = new ArrayList<>();
        yellowTower.add(T_Y_1);
        yellowTower.add(T_Y_2);
        yellowTower.add(T_Y_3);
        yellowTower.add(T_Y_4);

        blueTower = new ArrayList<>();
        blueTower.add(T_B_1);
        blueTower.add(T_B_2);
        blueTower.add(T_B_3);
        blueTower.add(T_B_4);

        purpleTower = new ArrayList<>();
        purpleTower.add(T_P_1);
        purpleTower.add(T_P_2);
        purpleTower.add(T_P_3);
        purpleTower.add(T_P_4);
    }

    public void initGreenTower(ArrayList<Image> greenCards) {
        for (int i = 0; i < greenTower.size(); i++) {
            greenTower.get(i).setImage(greenCards.get(i));
        }
    }

    public void initYellowTower(ArrayList<Image> yellowCards) {
        for (int i = 0; i < yellowTower.size(); i++) {
            yellowTower.get(i).setImage(yellowCards.get(i));
        }
    }

    public void initBlueTower(ArrayList<Image> blueCards) {
        for (int i = 0; i < blueTower.size(); i++) {
            blueTower.get(i).setImage(blueCards.get(i));
        }
    }

    public void initPurpleTower(ArrayList<Image> purpleCards) {
        for (int i = 0; i < purpleTower.size(); i++) {
            purpleTower.get(i).setImage(purpleCards.get(i));
        }
    }

    private void setStackPaneList() {
        stackPaneList = new ArrayList<>();
        stackPaneList.add(Y1);
        stackPaneList.add(Y2);
        stackPaneList.add(Y3);
        stackPaneList.add(Y4);
        stackPaneList.add(G1);
        stackPaneList.add(G2);
        stackPaneList.add(G3);
        stackPaneList.add(G4);
        stackPaneList.add(B1);
        stackPaneList.add(B2);
        stackPaneList.add(B3);
        stackPaneList.add(B4);
        stackPaneList.add(P1);
        stackPaneList.add(P2);
        stackPaneList.add(P3);
        stackPaneList.add(P4);
        stackPaneList.add(M1);
        stackPaneList.add(M2);
        stackPaneList.add(M3);
        stackPaneList.add(PRODUCTION_1);
        stackPaneList.add(HARVEST_1);
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
            pawn.setOnMouseClicked((event) -> {
                moveCircle(pawn);
            });
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
                    if ((circle.getBoundsInParent().intersects(COUNCIL_PALACE.getBoundsInParent()))) {
                        COUNCIL_PALACE.getChildren().add(circle);
                        COUNCIL_PALACE.setAlignment(circle, Pos.CENTER_LEFT);
                        circle.setTranslateX(7.5*(COUNCIL_PALACE.getChildren().size()-1));
                    }
                    if ((circle.getBoundsInParent().intersects(PRODUCTION_2.getBoundsInParent()))) {
                        PRODUCTION_2.getChildren().add(circle);
                        PRODUCTION_2.setAlignment(circle, Pos.CENTER_LEFT);
                        circle.setTranslateX(7.0*(PRODUCTION_2.getChildren().size()-1));
                    }
                    if ((circle.getBoundsInParent().intersects(HARVEST_2.getBoundsInParent()))) {
                        HARVEST_2.getChildren().add(circle);
                        HARVEST_2.setAlignment(circle, Pos.CENTER_LEFT);
                        circle.setTranslateX(7.0*(HARVEST_2.getChildren().size()-1));
                    }
                });
            });
        });
    }

    public void undoAction(Circle pawn) {
        pawn.setCenterY(originX);
        pawn.setCenterY(originY);
    }

    public void setFaith(int faithPoints, String player) {
        double newLayoutX;
        for (int i = 0; i < faithPath.getChildren().size(); i++) {
            if (faithPath.getChildren().get(i).getId() == player) {
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
