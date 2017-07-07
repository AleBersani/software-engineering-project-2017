package it.polimi.ingsw.client.gui;


import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.gui.notify.GameBoardNotifier;
import it.polimi.ingsw.client.middleware.ClientSender;
import it.polimi.ingsw.client.middleware.ClientSenderHandler;
import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.client.model.enums.PointsLight;
import it.polimi.ingsw.server.gamelogic.actionsdescription.ActionDescription;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.DiceColor;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.model.PawnColor;
import it.polimi.ingsw.shared.model.actionsdescription.BasicAction;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;
import it.polimi.ingsw.shared.requests.clientserver.BaseInformation;
import it.polimi.ingsw.shared.requests.clientserver.PawnPlacement;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import javax.script.Bindings;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameBoardController extends Observable implements Observer {
    private static final Logger LOGGER = Logger.getLogger(TileChoiceController.class.getName());
    private static final int FAITH_OFFSET = 35;
    private static final int ADDED_OFFSET = 15;
    private static final int CRITICAL_FAITH_1 = 3;
    private static final int CRITICAL_FAITH_2 = 4;
    private static final int CRITICAL_FAITH_3 = 5;

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
    private Map<GeneralColor, String> pawnColors;
    private Map<BoardIdentifier, StackPane> boardPositions;

    private BaseInformation baseInformation;
    private BoardLight boardLight;
    private ActionDescription actionDescription;

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
    private ImageView blackDice;
    @FXML
    private ImageView whiteDice;
    @FXML
    private ImageView orangeDice;
    @FXML
    private ImageView excom1;
    @FXML
    private ImageView excom2;
    @FXML
    private ImageView excom3;

    @FXML
    private Label infoplayer1;
    @FXML
    private Label infoplayer2;
    @FXML
    private Label infoplayer3;
    @FXML
    private Label playerName;

    @FXML
    private Spinner spinner;

    public GameBoardController() {
        baseInformation = new BaseInformation(ClientInformation.getCurrentGameId(), ClientInformation.getPlayerName());
        boardLight = BoardLight.getInstance();
    }

    public void initialize() {
        GameBoardNotifier.getInstance().addObserver(this);
       // showPlayerBoard();
        setTowers();
        setStackPaneList();
        setPawnList();
        initPawnColors();
        setPositions();
      //  setOwnerPawns();
        for (Circle c : pawnList) {
            if (!c.isDisabled()) checkList();
        }
    }

    private void showPlayerBoard() {
        Platform.runLater(() ->  {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/gui/playerboard.fxml"));
                Parent root = fxmlLoader.load();
                Stage playerBoardStage = new Stage();
                playerBoardStage.setScene(new Scene(root));
                playerBoardStage.show();
                playerBoardStage.toFront();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot launch player board", e);
            }
        });
    }

    private void setTowers() {
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

    private void setPawnList() {
        pawnList = new ArrayList<>();
        pawnList.add(orangePawn);
        pawnList.add(blackPawn);
        pawnList.add(whitePawn);
        pawnList.add(neutralPawn);
    }

    private void initPawnColors() {
        pawnColors = new EnumMap<>(GeneralColor.class);
        pawnColors.put(GeneralColor.PURPLE, "#d00000");
        pawnColors.put(GeneralColor.BLUE, "#1300cf");
        pawnColors.put(GeneralColor.YELLOW, "#fff200");
        pawnColors.put(GeneralColor.GREEN, "09dd02");
    }

    private void setPositions() {
        boardPositions = new EnumMap<>(BoardIdentifier.class);
        boardPositions.put(BoardIdentifier.T_G_1, g1);
        boardPositions.put(BoardIdentifier.T_G_2, g2);
        boardPositions.put(BoardIdentifier.T_G_3, g3);
        boardPositions.put(BoardIdentifier.T_G_4, g4);
        boardPositions.put(BoardIdentifier.T_Y_1, y1);
        boardPositions.put(BoardIdentifier.T_Y_2, y2);
        boardPositions.put(BoardIdentifier.T_Y_3, y3);
        boardPositions.put(BoardIdentifier.T_Y_4, y4);
        boardPositions.put(BoardIdentifier.T_B_1, b1);
        boardPositions.put(BoardIdentifier.T_B_2, b2);
        boardPositions.put(BoardIdentifier.T_B_3, b3);
        boardPositions.put(BoardIdentifier.T_B_4, b4);
        boardPositions.put(BoardIdentifier.T_P_1, p1);
        boardPositions.put(BoardIdentifier.T_P_2, p2);
        boardPositions.put(BoardIdentifier.T_P_3, p3);
        boardPositions.put(BoardIdentifier.T_P_4, p4);
        boardPositions.put(BoardIdentifier.HARVEST_1, harvest1);
        boardPositions.put(BoardIdentifier.PRODUCTION_1, production1);
        boardPositions.put(BoardIdentifier.M_1, m1);
        boardPositions.put(BoardIdentifier.M_2, m2);
        boardPositions.put(BoardIdentifier.M_3, m3);
        boardPositions.put(BoardIdentifier.M_4, m4);
    }

    private void setOwnerPawns() {
        for (Circle pawn : pawnList ) {
            pawn.setStroke(Paint.valueOf(pawnColors.get(ClientInformation.getPlayerColor())));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            clearBoard();
            initGreenTower();
            initYellowTower();
            initBlueTower();
            initPurpleTower();
            otherPlayersPawns();
            setDices();
            setOtherPlayersInfo();
        });
    }

    private void clearBoard() {
        for (ImageView slot : greenTower) {
            slot.setImage(null);
        }
        for (ImageView slot : yellowTower) {
            slot.setImage(null);
        }
        for (ImageView slot : blueTower) {
            slot.setImage(null);
        }
        for (ImageView slot : purpleTower) {
            slot.setImage(null);
        }
        for (StackPane stackPane : stackPaneList) {
            stackPane.getChildren().clear();
        }
        council_palace.getChildren().clear();
        harvest2.getChildren().clear();
        production2.getChildren().clear();
    }

    private void setOtherPlayersInfo() {
        List<Label> labels = new ArrayList<>();
        labels.add(infoplayer1);
        labels.add(infoplayer2);
        labels.add(infoplayer3);
        int labelIndex = 0;
        StringBuilder text = new StringBuilder();
        for (PlayerLight player : boardLight.getPlayerLights()) {
            if (!player.getPlayerName().equals(ClientInformation.getPlayerName())) {
                text.append(player.getPlayerName() + "\n");
              //  if (!player.getActivatedLeaders().isEmpty()) {
              //      player.getActivatedLeaders().forEach(leader -> text.append(leader.getName() + "\n"));
             //   }
              //  text.append("Victory points: " + player.getNumberOfPoints().get(PointsLight.VICTORY_POINTS));
                labels.get(labelIndex).setText(text.toString());
                labels.get(labelIndex).setTextFill(Paint.valueOf(pawnColors.get(player.getPlayerColor())));
                labelIndex++;
            }
        }
        playerName.setText(ClientInformation.getPlayerName());
        playerName.setMinWidth(Region.USE_PREF_SIZE);
    }

    private void initGreenTower() {
        for (int i = 0; i < greenTower.size(); i++) {
            Image newGreenCard = new Image("client/devcards/" +
                    boardLight.getGreenTower().get(i).getCard().getName() + ".png");
            greenTower.get(i).setImage(newGreenCard);
        }
    }

    private void initYellowTower() {
        for (int i = 0; i < yellowTower.size(); i++) {
            Image newYellowCard = new Image("client/devcards/" +
                    boardLight.getYellowTower().get(i).getCard().getName() + ".png");
            yellowTower.get(i).setImage(newYellowCard);
        }
    }

    private void initBlueTower() {
        for (int i = 0; i < blueTower.size(); i++) {
            Image newBlueCard = new Image("client/devcards/" +
                    boardLight.getBlueTower().get(i).getCard().getName() + ".png");
            blueTower.get(i).setImage(newBlueCard);
        }
    }

    private void initPurpleTower() {
        for (int i = 0; i < purpleTower.size(); i++) {
            Image newPurpleCard = new Image("client/devcards/" +
                    boardLight.getPurpleTower().get(i).getCard().getName() + ".png");
            purpleTower.get(i).setImage(newPurpleCard);
        }
    }

    private void checkList() {
        for (Circle pawn : pawnList) {
            pawn.setOnMouseClicked(event -> moveCircle(pawn));
        }
    }

    private void moveCircle(Circle circle) {
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
                    circle.setCenterX(newX.getCenterX());
                    circle.setCenterY(newX.getCenterY());
                    for (StackPane stackPane : stackPaneList) {
                        if (circle.getBoundsInParent().intersects(stackPane.getBoundsInParent())
                                && stackPane.getChildren().isEmpty()) {
                            stackPane.getChildren().add(circle);
                            circle.relocate(stackPane.getHeight() / 2, stackPane.getWidth() / 2);
                            circle.setDisable(true);
                            for (Map.Entry<BoardIdentifier, StackPane> entry : boardPositions.entrySet()) {
                                if (entry.getValue().equals(stackPane)) {
                                    BoardIdentifier boardIdentifier = entry.getKey();
                                    PawnColor pawnColor = PawnColor.UNCOLORED;
                                    switch (circle.getFill().toString()) {
                                        case "0x000000ff":
                                            pawnColor = PawnColor.BLACK;
                                            break;
                                        case "0xffffffff":
                                            pawnColor = PawnColor.WHITE;
                                            break;
                                        case "0xff8706ff":
                                            pawnColor = PawnColor.ORANGE;
                                            break;
                                        case "0xc99463ff":
                                            pawnColor = PawnColor.NEUTRAL;
                                            break;
                                    }
                                    BasicAction basicAction =
                                            new BasicAction(Utils.getActionTypeByBoardIdentifier(boardIdentifier),
                                                    boardIdentifier, Utils.pawnValueGivenPawnColor(pawnColor));
                                    sendBoardAction(basicAction, pawnColor, (int)spinner.getValue());
                                    break;
                                }
                            }
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

    private void sendBoardAction(BasicAction basicAction, PawnColor pawnColor, int numberOfServant) {
        actionDescription = new BoardAction(basicAction, pawnColor, numberOfServant);
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new PawnPlacement(baseInformation, actionDescription));
    }

    private void otherPlayersPawns() {
        GeneralColor playerColor;
        String playerName;
        for (PlayerLight player : boardLight.getPlayerLights() ) {
            playerColor = player.getPlayerColor();
            playerName = player.getPlayerName();
            checkOtherPlayersPawnsTowers(boardLight.getGreenTower(), playerColor, playerName);
            checkOtherPlayersPawnsTowers(boardLight.getYellowTower(), playerColor, playerName);
            checkOtherPlayersPawnsTowers(boardLight.getBlueTower(), playerColor, playerName);
            checkOtherPlayersPawnsTowers(boardLight.getPurpleTower(), playerColor, playerName);
            checkOtherPlayersPawnsMarket(boardLight.getHarvest(), playerColor, playerName);
            checkOtherPlayersPawnsMarket(boardLight.getMarket(), playerColor, playerName);
            checkOtherPlayersPawnsMarket(boardLight.getProduction(), playerColor, playerName);
            checkOtherPlayersCouncilPalace(playerColor, playerName);
        }
    }

    private void checkOtherPlayersPawnsTowers(List<TowerSlotLight> towerToCheck, GeneralColor playerColor,
                                              String playerName) {
        Optional<PawnLight> pawnLightOptional;
        for (int i = 0; i < towerToCheck.size(); i++) {
            if ((pawnLightOptional = towerToCheck.get(i).getSlotLight().getPawnLight()).isPresent()) {
                if (pawnLightOptional.get().isPlaced() && pawnLightOptional.get().getPlayerName().equals(playerName)) {
                    PawnLight placedPawn = pawnLightOptional.get();
                    BoardIdentifier pawnIdentifier = towerToCheck.get(i).getSlotLight().getBoardIdentifier();
                    addPawn(pawnIdentifier, placedPawn, playerColor);
                }
            }
        }
    }

    private void checkOtherPlayersPawnsMarket(List<SlotLight> slotsToCheck, GeneralColor playerColor,
                                              String playerName) {
        Optional<PawnLight> pawnLightOptional;
        for (int i = 0; i < slotsToCheck.size(); i++) {
            if ((pawnLightOptional = slotsToCheck.get(i).getPawnLight()).isPresent()) {
                if (pawnLightOptional.get().isPlaced() && pawnLightOptional.get().getPlayerName().equals(playerName)) {
                    PawnLight placedPawn = pawnLightOptional.get();
                    BoardIdentifier pawnIdentifier = slotsToCheck.get(i).getBoardIdentifier();
                    addPawn(pawnIdentifier, placedPawn, playerColor);
                }
            }
        }
    }

    private void checkOtherPlayersCouncilPalace(GeneralColor playerColor, String playerName) {
        for (PawnLight pawnLight : boardLight.getCouncilPalaceLight().getPawnLightList()){
            if (pawnLight.getPlayerName().equals(playerName) && pawnLight.isPlaced()) {
                addPawn(BoardIdentifier.COUNCIL_PALACE, pawnLight, playerColor);
            }
        }

    }

    private void addPawn(BoardIdentifier pawnIdentifier, PawnLight placedPawn, GeneralColor playerColor) {
        Circle c1 = new Circle();
        if (boardPositions.containsKey(pawnIdentifier)) {
            StackPane pawnStack = boardPositions.get(pawnIdentifier);
            c1 = new Circle(pawnStack.getWidth() / 2, pawnStack.getHeight() / 2, 15.0);
            pawnStack.getChildren().add(c1);
        } else {
            switch (pawnIdentifier) {
                case HARVEST_2: c1.setRadius(15.0);
                    harvest2.getChildren().add(c1);
                    harvest2.setAlignment(Pos.CENTER_LEFT);
                    c1.setTranslateX(7.0*(harvest2.getChildren().size()-1));
                    break;
                case PRODUCTION_2: c1.setRadius(15.0);
                    production2.getChildren().add(c1);
                    production2.setAlignment(Pos.CENTER_LEFT);
                    c1.setTranslateX(7.0*(production2.getChildren().size()-1));
                    break;
                case COUNCIL_PALACE: c1.setRadius(15.0);
                    council_palace.getChildren().add(c1);
                    council_palace.setAlignment(c1, Pos.CENTER_LEFT);
                    c1.setTranslateX(7.5*(council_palace.getChildren().size()-1));
                    break;
            }
        }
        c1.setStroke(Paint.valueOf(pawnColors.get(playerColor)));
        c1.setFill(Paint.valueOf(placedPawn.getPawnColor().toString().toLowerCase()));
        c1.setStrokeType(StrokeType.INSIDE);
        c1.setStrokeWidth(4.0);
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
    public void hidePlayerBoard() {
        if (playerBoard.getScene().getWindow().isShowing()) {
            playerBoard.hide();
        }
    }

    @FXML
    public void reOpenPlayerBoard() {
        if (!playerBoard.getScene().getWindow().isShowing()) {
            playerBoard.show();
        }
    }

    public void setDices() {
        DiceColor color;
        int value;
        boardLight.getDiceLightList().forEach(diceLight -> System.out.println(diceLight.getDiceColor().toString() + " " + diceLight.getValue()));
        for (int i = 0; i < boardLight.getDiceLightList().size(); i++) {
            color = boardLight.getDiceLightList().get(i).getDiceColor();
            value = boardLight.getDiceLightList().get(i).getValue();
            switch (color) {
                case BLACK: blackDice.setImage(new Image(
                        "client/dices/" + color.toString() + "_" + value + ".png"));
                    System.out.println("client/dices/" + color.toString() + "_" + value + ".png");
                        break;
                case WHITE: whiteDice.setImage(new Image(
                        "client/dices/" + color.toString() + "_" + value + ".png"));
                    System.out.println("client/dices/" + color.toString() + "_" + value + ".png");
                    break;
                case ORANGE: orangeDice.setImage(new Image(
                        "client/dices/" + color.toString() + "_" + value + ".png"));
                    System.out.println("client/dices/" + color.toString() + "_" + value + ".png");
                    break;
            }
        }
    }
}