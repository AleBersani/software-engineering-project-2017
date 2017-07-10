package it.polimi.ingsw.client.gui;


import com.jfoenix.controls.JFXButton;
import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.gui.notify.GameBoardNotifier;
import it.polimi.ingsw.client.gui.notify.gameboardresponses.*;
import it.polimi.ingsw.client.middleware.ClientSender;
import it.polimi.ingsw.client.middleware.ClientSenderHandler;
import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.client.model.enums.PointsLight;
import it.polimi.ingsw.server.gamelogic.actionsdescription.ActionDescription;
import it.polimi.ingsw.shared.model.*;
import it.polimi.ingsw.shared.model.actionsdescription.BasicAction;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;
import it.polimi.ingsw.shared.requests.clientserver.BaseInformation;
import it.polimi.ingsw.shared.requests.clientserver.EndTurn;
import it.polimi.ingsw.shared.requests.clientserver.PawnPlacement;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controls the game board with all the updates and the actions
 */

public class GameBoardController extends Observable implements Observer {
    private static final Logger LOGGER = Logger.getLogger(TileChoiceController.class.getName());
    private static final int FAITH_OFFSET = 35;
    private static final int ADDED_OFFSET = 15;
    private static final int CRITICAL_FAITH_1 = 3;
    private static final int CRITICAL_FAITH_2 = 4;
    private static final int CRITICAL_FAITH_3 = 5;

    private List<Circle> pawnList;
    private Map<GeneralColor, String> pawnColors;

    private List<StackPane> stackPaneList;
    private List<ImageView> greenTower;
    private List<ImageView> yellowTower;
    private List<ImageView> blueTower;
    private List<ImageView> purpleTower;
    private double startX;
    private double startY;
    private Stage playerBoard;
    private Map<BoardIdentifier, StackPane> boardPositions;
    private List<Circle> order;

    private BoardLight boardLight;
    private Owner owner;
    private BaseInformation baseInformation;
    private ActionDescription actionDescription;

    private Stage playerBoardStage;

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
    private Circle first;
    @FXML
    private Circle second;
    @FXML
    private Circle third;
    @FXML
    private Circle fourth;

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
    private ImageView cover1;
    @FXML
    private ImageView cover2;
    @FXML
    private ImageView coverProd;
    @FXML
    private ImageView coverHarv;

    @FXML
    private Label infoplayer1;
    @FXML
    private Label infoplayer2;
    @FXML
    private Label infoplayer3;
    @FXML
    private Label playerName;
    @FXML
    private Label infoOwner;

    @FXML
    private Pane root;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private JFXButton pass;
    @FXML
    private JFXButton leave;
    @FXML
    private JFXButton interrupt;

    public GameBoardController() {
        pawnList = new ArrayList<>();
        pawnColors = new EnumMap<>(GeneralColor.class);
        stackPaneList = new ArrayList<>();
        greenTower = new ArrayList<>();
        yellowTower = new ArrayList<>();
        blueTower = new ArrayList<>();
        purpleTower = new ArrayList<>();
        boardPositions = new EnumMap<>(BoardIdentifier.class);
        order = new ArrayList<>();
    }

    public void initialize() {
        GameBoardNotifier.getInstance().addObserver(this);
        boardLight = BoardLight.getInstance();
        owner = Owner.getInstance();
        baseInformation = new BaseInformation(ClientInformation.getCurrentGameId(), ClientInformation.getPlayerName());
        showPlayerBoard();
        setPositions();
        initPawnColors();
        setTowers();
        setStackPaneList();
        coverActionSpaces();
        leave.setMouseTransparent(true);
        interrupt.setMouseTransparent(true);
    }

    private void showPlayerBoard() {
        Platform.runLater(() ->  {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/gui/playerboard.fxml"));
                Parent root = fxmlLoader.load();
                playerBoardStage = new Stage();
                playerBoardStage.setScene(new Scene(root));
                playerBoardStage.show();
                playerBoardStage.toFront();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot launch player board", e);
            }
        });
    }

    private void setPositions() {
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

    private void initPawnColors() {
        pawnColors.put(GeneralColor.PURPLE, "#d00000");
        pawnColors.put(GeneralColor.BLUE, "#1300cf");
        pawnColors.put(GeneralColor.YELLOW, "#fff200");
        pawnColors.put(GeneralColor.GREEN, "09dd02");
    }

    private void setTowers() {
        greenTower.add(t_g_1);
        greenTower.add(t_g_2);
        greenTower.add(t_g_3);
        greenTower.add(t_g_4);

        yellowTower.add(t_y_1);
        yellowTower.add(t_y_2);
        yellowTower.add(t_y_3);
        yellowTower.add(t_y_4);

        blueTower.add(t_b_1);
        blueTower.add(t_b_2);
        blueTower.add(t_b_3);
        blueTower.add(t_b_4);

        purpleTower.add(t_p_1);
        purpleTower.add(t_p_2);
        purpleTower.add(t_p_3);
        purpleTower.add(t_p_4);
    }

    private void setStackPaneList() {
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
        stackPaneList.add(production1);
        stackPaneList.add(harvest1);
        if (boardLight.getPlayerLights().size() >= 3) {
            stackPaneList.add(m3);
            stackPaneList.add(m4);
        }
        if (boardLight.getPlayerLights().size() < 3) {
            harvest2.setDisable(true);
            production2.setDisable(true);
            m3.setDisable(true);
            m4.setDisable(true);
        }
    }

    public void coverActionSpaces() {
        if (boardLight.getPlayerLights().size() >= 3){
            coverProd.setImage(null);
            coverHarv.setImage(null);
        }
        if (boardLight.getPlayerLights().size() == 4) {
            cover1.setImage(null);
            cover2.setImage(null);
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        GameBoardResponse gameBoardResponse = (GameBoardResponse)arg;
        GameBoardVisitor gameBoardVisitor = new GameBoardVisitor() {
            @Override
            public void visitGameBoardResponse(ActionResult actionResult) {
                if (actionResult.isResult()) {
                    for (Circle circle : pawnList) {
                        circle.setDisable(true);
                    }
                }
            }

            @Override
            public void visitGameBoardResponse(ConsumableActionChoice consumableActionChoice) {
                Platform.runLater(() ->  {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/gui/cardAction.fxml"));
                        Parent root = fxmlLoader.load();
                        Stage bonusAction = new Stage();
                        bonusAction.setScene(new Scene(root, 200, 300));
                        bonusAction.show();
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "An exception was thrown: cannot launch bonus action choice", e);
                    }
                });
            }

            @Override
            public void visitGameBoardResponse(CouncilPrivilegeEvent councilPrivilegeEvent) {
                startCouncilPrivilegeChoice();
            }

            @Override
            public void visitGameBoardResponse(EndGameResponse endGameResponse) {
                //playerBoard.close();
                Stage stage = (Stage) pass.getScene().getWindow();
                //stage.close();
            }

            @Override
            public void visitGameBoardResponse(LorenzoChoice lorenzoChoice) {
                Platform.runLater(() ->  {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/gui/activatedLeaders.fxml"));
                        Parent root = fxmlLoader.load();
                        Stage leadersChoice = new Stage();
                        leadersChoice.setScene(new Scene(root, 390, 450));
                        leadersChoice.show();
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "An exception was thrown: cannot launch leader choice", e);
                    }
                });
            }

            @Override
            public void visitGameBoardResponse(TurnStatus turnStatus) {
                if (turnStatus.isMyTurn()) {
                    for (PawnLight pawnLight : owner.getPawnLights()) {
                        if (!pawnLight.isPlaced()) {
                            for (Circle circle : pawnList) {
                                switch (circle.getFill().toString()) {
                                    case "0x000000ff":
                                        if (PawnColor.BLACK == pawnLight.getPawnColor()) {
                                            circle.setDisable(false);
                                        }
                                        break;
                                    case "0xffffffff":
                                        if (PawnColor.WHITE == pawnLight.getPawnColor()) {
                                            circle.setDisable(false);
                                        }
                                        break;
                                    case "0xffa500ff":
                                        if (PawnColor.ORANGE == pawnLight.getPawnColor()) {
                                            circle.setDisable(false);
                                        }
                                        break;
                                    case "0xcd853fff":
                                        if (PawnColor.NEUTRAL == pawnLight.getPawnColor()) {
                                            circle.setDisable(false);
                                        }
                                        break;
                                }
                            }
                        }
                    }
                    for (Circle circle : pawnList) {
                        if (!circle.isDisabled()){
                            circle.setOnMouseClicked(event -> moveCircle(circle));
                        }
                    }
                } else {
                    for (Circle circle : pawnList) {
                        circle.setDisable(true);
                    }
                }
            }

            @Override
            public void visitGameBoardResponse(UpdateBoard updateBoard) {
                Platform.runLater(() -> {
                    clearBoard();
                    drawPawns();
                    initGreenTower();
                    initYellowTower();
                    initBlueTower();
                    initPurpleTower();
                    setExcommunications();
                    setDices();
                    otherPlayersPawns();
                    setOtherPlayersInfo();
                    checkFaithPoints();
                    setPlayersOrder();


                });
            }
        };
        gameBoardResponse.acceptGameBoardVisitor(gameBoardVisitor);
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
        spinner.getValueFactory().setValue(0);
    }

    private void initGreenTower() {
        for (ImageView aGreenTower : greenTower) {
            for (int j = 0; j < boardLight.getGreenTower().size(); j++) {
                if (!"Empty".equals(boardLight.getGreenTower().get(j).getCard().getName()) &&
                        aGreenTower.getId().toUpperCase().equals(
                                boardLight.getGreenTower().get(j).getSlotLight().getBoardIdentifier().toString())) {
                    Image newGreenCard = new Image("client/devcards/" +
                            boardLight.getGreenTower().get(j).getCard().getName() + ".png");
                    aGreenTower.setImage(newGreenCard);
                }
            }
        }
    }

    private void initYellowTower() {
        for (ImageView aYellowTower : yellowTower) {
            for (int j = 0; j < boardLight.getYellowTower().size(); j++) {
                if (!"Empty".equals(boardLight.getYellowTower().get(j).getCard().getName()) &&
                        aYellowTower.getId().toUpperCase().equals(
                                boardLight.getYellowTower().get(j).getSlotLight().getBoardIdentifier().toString())) {
                    Image newYellowCard = new Image("client/devcards/" +
                            boardLight.getYellowTower().get(j).getCard().getName() + ".png");
                    aYellowTower.setImage(newYellowCard);
                }
            }
        }
    }

    private void initBlueTower() {
        for (ImageView aBlueTower : blueTower) {
            for (int j = 0; j < boardLight.getBlueTower().size(); j++) {
                if (!"Empty".equals(boardLight.getBlueTower().get(j).getCard().getName()) &&
                        aBlueTower.getId().toUpperCase().equals(
                                boardLight.getBlueTower().get(j).getSlotLight().getBoardIdentifier().toString())) {
                    Image newBlueCard = new Image("client/devcards/" +
                            boardLight.getBlueTower().get(j).getCard().getName() + ".png");
                    aBlueTower.setImage(newBlueCard);
                }
            }
        }
    }

    private void initPurpleTower() {
        for (ImageView aPurpleTower : purpleTower) {
            for (int j = 0; j < boardLight.getPurpleTower().size(); j++) {
                if (!"Empty".equals(boardLight.getPurpleTower().get(j).getCard().getName()) &&
                        aPurpleTower.getId().toUpperCase().equals(
                                boardLight.getPurpleTower().get(j).getSlotLight().getBoardIdentifier().toString())) {
                    Image newPurpleCard = new Image("client/devcards/" +
                            boardLight.getPurpleTower().get(j).getCard().getName() + ".png");
                    aPurpleTower.setImage(newPurpleCard);
                }
            }
        }
    }

    private void setExcommunications() {
        for (int i = 0; i < boardLight.getExcommunicationTiles().size(); i++) {
            String excomName = boardLight.getExcommunicationTiles().get(i).getName();
            if (excomName.startsWith("1.")) {
                Image firstExcom = new Image(
                        "client/excomtiles/" + excomName + ".png");
                excom1.setImage(firstExcom);
            }
            if (excomName.startsWith("2.")) {
                Image secondExcom = new Image(
                        "client/excomtiles/" + excomName + ".png");
                excom2.setImage(secondExcom);
            } else {
                Image thirdExcom = new Image(
                        "client/excomtiles/" + excomName + ".png");
                excom3.setImage(thirdExcom);
            }
        }
    }

    private void setDices() {
        DiceColor color;
        int value;
        for (int i = 0; i < boardLight.getDiceLightList().size(); i++) {
            color = boardLight.getDiceLightList().get(i).getDiceColor();
            value = boardLight.getDiceLightList().get(i).getValue();
            switch (color) {
                case BLACK: blackDice.setImage(new Image(
                        "client/dices/" + color.toString() + "_" + value + ".png"));
                    break;
                case WHITE: whiteDice.setImage(new Image(
                        "client/dices/" + color.toString() + "_" + value + ".png"));
                    break;
                case ORANGE: orangeDice.setImage(new Image(
                        "client/dices/" + color.toString() + "_" + value + ".png"));
                    break;
            }
        }
    }

    private void drawPawns() {
        for (Circle pawn : pawnList) {
            root.getChildren().remove(pawn);
        }
        pawnList.clear();
        for (PawnLight pawnLight : owner.getPawnLights()) {
            if (!pawnLight.isPlaced()) {
                switch (pawnLight.getPawnColor()) {
                    case BLACK:
                        Circle blackPawn = new Circle(15.0);
                        blackPawn.setFill(Color.BLACK);
                        blackPawn.setCenterY(453.0);
                        blackPawn.setCenterX(712.0);
                        blackPawn.setStroke(Paint.valueOf(owner.getPlayerLight().getPlayerColor().toString()));
                        blackPawn.setStrokeWidth(4.0);
                        root.getChildren().add(blackPawn);
                        pawnList.add(blackPawn);
                        break;
                    case ORANGE:
                        Circle orangePawn = new Circle(15.0);
                        orangePawn.setFill(Color.ORANGE);
                        orangePawn.setCenterY(453.0);
                        orangePawn.setCenterX(755.0);
                        orangePawn.setStroke(Paint.valueOf(owner.getPlayerLight().getPlayerColor().toString()));
                        orangePawn.setStrokeWidth(4.0);
                        root.getChildren().add(orangePawn);
                        pawnList.add(orangePawn);
                        break;
                    case WHITE:
                        Circle whitePawn = new Circle(15.0);
                        whitePawn.setFill(Color.WHITE);
                        whitePawn.setCenterY(453.0);
                        whitePawn.setCenterX(800.0);
                        whitePawn.setStroke(Paint.valueOf(owner.getPlayerLight().getPlayerColor().toString()));
                        whitePawn.setStrokeWidth(4.0);
                        root.getChildren().add(whitePawn);
                        pawnList.add(whitePawn);
                        break;
                    case NEUTRAL:
                        Circle neutralPawn = new Circle(15.0);
                        neutralPawn.setFill(Color.PERU);
                        neutralPawn.setCenterY(453.0);
                        neutralPawn.setCenterX(845.0);
                        neutralPawn.setStroke(Paint.valueOf(owner.getPlayerLight().getPlayerColor().toString()));
                        neutralPawn.setStrokeWidth(4.0);
                        root.getChildren().add(neutralPawn);
                        pawnList.add(neutralPawn);
                        break;
                }
            }
        }
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

    private void addPawn(BoardIdentifier pawnIdentifier, PawnLight placedPawn, GeneralColor playerColor) {
        Circle c1 = new Circle();
        if (boardPositions.containsKey(pawnIdentifier)) {
            StackPane pawnStack = boardPositions.get(pawnIdentifier);
            c1 = new Circle(pawnStack.getWidth() / 2, pawnStack.getHeight() / 2, 15.0);
            pawnStack.getChildren().add(c1);
        } else {
            switch (pawnIdentifier) {
                case HARVEST_2:
                    harvest2.getChildren().add(c1);
                    c1.setRadius(15.0);
                    harvest2.setAlignment(Pos.CENTER_LEFT);
                    c1.setTranslateX(7.0*(harvest2.getChildren().size()-1));
                    break;
                case PRODUCTION_2:
                    production2.getChildren().add(c1);
                    c1.setRadius(15.0);
                    production2.setAlignment(Pos.CENTER_LEFT);
                    c1.setTranslateX(7.0*(production2.getChildren().size()-1));
                    break;
                case COUNCIL_PALACE:
                    council_palace.getChildren().add(c1);
                    c1.setRadius(15.0);
                    StackPane.setAlignment(c1, Pos.CENTER_LEFT);
                    c1.setTranslateX(7.5*(council_palace.getChildren().size()-1));
                    break;
            }
        }
        c1.setStroke(Paint.valueOf(pawnColors.get(playerColor)));
        if (placedPawn.getPawnColor() == PawnColor.NEUTRAL) {
            c1.setFill(Paint.valueOf("PERU"));
        } else {
            c1.setFill(Paint.valueOf(placedPawn.getPawnColor().toString()));
        }
        c1.setStrokeType(StrokeType.INSIDE);
        c1.setStrokeWidth(4.0);
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

    private void setOtherPlayersInfo() {
        List<Label> labels = new ArrayList<>();
        labels.add(infoplayer1);
        labels.add(infoplayer2);
        labels.add(infoplayer3);
        int labelIndex = 0;
        for (PlayerLight player : boardLight.getPlayerLights()) {
            if (!player.getPlayerName().equals(ClientInformation.getPlayerName())) {
                StringBuilder text = new StringBuilder();
                text.append(player.getPlayerName() + "\n");
                if (!player.getActivatedLeaders().isEmpty()) {
                    player.getActivatedLeaders().forEach(leader -> text.append(leader.getName() + "\n"));
                }
                text.append("Victory P: " + player.getNumberOfPoints().get(PointsLight.VICTORY_POINTS) + "\n");
                text.append("Military P: " + player.getNumberOfPoints().get(PointsLight.MILITARY_POINTS) + "\n");
                text.append("Faith P: " + player.getNumberOfPoints().get(PointsLight.FAITH_POINTS));
                labels.get(labelIndex).setText(text.toString());
                labels.get(labelIndex).setTextFill(Paint.valueOf(pawnColors.get(player.getPlayerColor())));
                labelIndex++;
            }
        }
        playerName.setText(ClientInformation.getPlayerName());
        playerName.setMinWidth(Region.USE_PREF_SIZE);
        StringBuilder text1 = new StringBuilder();
        owner.getPlayerLight().getActivatedLeaders().forEach(leader -> text1.append(leader.getName() + "\n"));
        text1.append("Victory P: " + owner.getPlayerLight().getNumberOfPoints().get(PointsLight.VICTORY_POINTS) + "\n");
        text1.append("Military P: " + owner.getPlayerLight().getNumberOfPoints().get(PointsLight.MILITARY_POINTS) + "\n");
        text1.append("Faith P: " + owner.getPlayerLight().getNumberOfPoints().get(PointsLight.FAITH_POINTS));
        infoOwner.setText(text1.toString());
        infoOwner.setTextFill(Paint.valueOf(ClientInformation.getPlayerColor().toString()));
    }

    private void checkFaithPoints() {
        for (int i = 0; i < boardLight.getPlayerLights().size(); i++) {
            for (int j = 0; j < faithPath.getChildren().size(); j++) {
                if (boardLight.getPlayerLights().get(i).getPlayerColor().toString().equals(
                        faithPath.getChildren().get(j).getId())) {
                    ((Circle) faithPath.getChildren().get(j)).setFill(Paint.valueOf(
                            boardLight.getPlayerLights().get(i).getPlayerColor().toString()));
                }
            }
        }
        for (int i = 0; i < boardLight.getPlayerLights().size(); i++) {
            setFaith(boardLight.getPlayerLights().get(i).getNumberOfPoints().get(PointsLight.FAITH_POINTS),
                    boardLight.getPlayerLights().get(i).getPlayerColor());
        }
    }

    private void setFaith(int faithPoints, GeneralColor player) {
        double newLayoutX;
        for (int i = 0; i < boardLight.getPlayerLights().size(); i++) {
            if (faithPath.getChildren().get(i).getId().equals(player.toString())) {
                faithPath.getChildren().get(i).setTranslateX(FAITH_OFFSET*faithPoints);
                newLayoutX = faithPath.getChildren().get(i).getLayoutX();
                if (faithPoints == CRITICAL_FAITH_1 || faithPoints == CRITICAL_FAITH_2 ||
                        faithPoints == CRITICAL_FAITH_3){
                    faithPath.getChildren().get(i).setTranslateX(newLayoutX + ADDED_OFFSET);
                }
            }
        }
    }

    private void setPlayersOrder() {
        order.add(first);
        order.add(second);
        order.add(third);
        order.add(fourth);
        for (int i = 0; i < order.size() && i < boardLight.getPlayerLights().size(); i++) {
            order.get(i).setFill(Paint.valueOf(
                    boardLight.getPlayerLights().get(i).getPlayerColor().toString().toLowerCase()));
        }
    }

    private void moveCircle(Circle circle) {
        circle.setOnMousePressed((MouseEvent e) -> {
            Circle c = (Circle) (e.getSource());
            c.setCursor(Cursor.HAND);
            startX = e.getSceneX();
            startY = e.getSceneY();
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
                                        case "0xffa500ff":
                                            pawnColor = PawnColor.ORANGE;
                                            break;
                                        case "0xcd853fff":
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

    private void sendBoardAction(BasicAction basicAction, PawnColor pawnColor, int numberOfServant) {
        actionDescription = new BoardAction(basicAction, pawnColor, numberOfServant);
        ClientInformation.setLastBoardAction((BoardAction)actionDescription);
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new PawnPlacement(baseInformation, actionDescription));
    }

    private void setCouncil_palace(Circle circle) {
        council_palace.getChildren().add(circle);
        council_palace.setAlignment(circle, Pos.CENTER_LEFT);
        circle.setTranslateX(7.5 * (council_palace.getChildren().size() - 1));
        PawnColor pawnColor = PawnColor.UNCOLORED;
        String color = circle.getFill().toString();
        switch (color) {
            case "0xffffffff":
                pawnColor = PawnColor.WHITE;
                break;
            case "0x000000ff":
                pawnColor = PawnColor.BLACK;
                break;
            case "0xcd853fff":
                pawnColor = PawnColor.NEUTRAL;
                break;
            case "0xff8706ff":
                pawnColor = PawnColor.ORANGE;
                break;
        }
        BasicAction basicAction = new BasicAction(ActionType.COUNCIL_PALACE, BoardIdentifier.COUNCIL_PALACE,
                Utils.pawnValueGivenPawnColor(pawnColor));
        BoardAction boardAction = new BoardAction(basicAction, pawnColor, 0);
        ClientInformation.setLastBoardAction(boardAction);
        ClientInformation.getNumberOfCouncilPrivilegeToChoose().set(1);
        startCouncilPrivilegeChoice();
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

    private void startCouncilPrivilegeChoice() {
        Platform.runLater(() ->  {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/gui/councilConvert.fxml"));
                Parent root = fxmlLoader.load();
                Stage councilPrivilegeChoice = new Stage();
                councilPrivilegeChoice.setScene(new Scene(root, 350, 500));
                councilPrivilegeChoice.show();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An exception was thrown: cannot launch council convert", e);
            }
        });
    }

    @FXML
    public void passTurn() {
        ClientSender clientSender = new ClientSenderHandler();
        clientSender.sendToServer(new EndTurn(new BaseInformation(ClientInformation.getCurrentGameId(), ClientInformation.getPlayerName())));
    }

}

