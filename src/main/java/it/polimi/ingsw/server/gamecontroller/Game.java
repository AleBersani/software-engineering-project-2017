package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.gameelements.BoardInformation;
import it.polimi.ingsw.server.gamelogic.board.Board;
import it.polimi.ingsw.server.gamelogic.board.Space;
import it.polimi.ingsw.server.gamelogic.board.Tower;
import it.polimi.ingsw.server.gamelogic.board.TowerSlot;
import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.GeneralColor;

import java.util.*;
import java.util.logging.Logger;

/**
 * //
 */
public class Game implements Runnable, Observer {
    private final static Logger LOGGER = Logger.getLogger(Game.class.getName());

    private int gameId;
    private Queue<ConnectedClient> connectedClients;

    private List<Player> players;
    private List<Period> periods;
    private List<DevelopmentCard> developmentCards;
    private Board board;

    public Game(int gameId, Queue<ConnectedClient> connectedClients) {
        this.gameId = gameId;
        this.connectedClients = connectedClients;
        players = new ArrayList<>();
        periods = new ArrayList<>();
        developmentCards = new ArrayList<>();
        board = new Board();
    }

    @Override
    public void run() {
        LOGGER.info("New game started! ID: " + gameId);
        
    }

    private void setupBoard(int numberOfPlayer) {
        setupTowers();
        setupCouncilPalace();
        setupActionSpaces();
    }

    private void setupTowers() {
        List<TowerSlot> greenTowerSlot = new ArrayList<>();
        for (Space space : BoardInformation.getGreenTower().keySet()) {
            greenTowerSlot.add(new TowerSlot(space, BoardInformation.getGreenTower().get(space)));
        }
        Tower greenTower = new Tower(GeneralColor.GREEN, greenTowerSlot);

        List<TowerSlot> blueTowerSlot = new ArrayList<>();
        for (Space space : BoardInformation.getBlueTower().keySet()) {
            greenTowerSlot.add(new TowerSlot(space, BoardInformation.getBlueTower().get(space)));
        }
        Tower blueTower = new Tower(GeneralColor.BLUE, blueTowerSlot);

        List<TowerSlot> yellowTowerSlot = new ArrayList<>();
        for (Space space : BoardInformation.getYellowTower().keySet()) {
            greenTowerSlot.add(new TowerSlot(space, BoardInformation.getYellowTower().get(space)));
        }
        Tower buildingTower = new Tower(GeneralColor.YELLOW, yellowTowerSlot);

        List<TowerSlot> purpleTowerSlot = new ArrayList<>();
        for (Space space : BoardInformation.getPurpleTower().keySet()) {
            greenTowerSlot.add(new TowerSlot(space, BoardInformation.getPurpleTower().get(space)));
        }
        Tower purpleTower = new Tower(GeneralColor.PURPLE, purpleTowerSlot);

        List<Tower> towers = new ArrayList<>();
        towers.add(greenTower);
        towers.add(blueTower);
        towers.add(buildingTower);
        towers.add(purpleTower);
        board.setTowers(towers);
    }

    private void setupCouncilPalace() {
        board.setCouncilPalace(BoardInformation.getCouncilPalace());
    }

    private void setupActionSpaces() {
        board.getBoardActionSpaces().setProductionArea(BoardInformation.getProductionArea());
        board.getBoardActionSpaces().setHarvestArea(BoardInformation.getHarvestArea());
        board.getBoardActionSpaces().setMarketArea(BoardInformation.getMarketArea());
    }

    @Override
    public void update(Observable o, Object arg) {
        /*
        avvia e setuppa altro periodo, se non è fine game
         */
    }

    public void setupGame() {
        /*
        calcolo ordine di gioco, setup carte leader, scelta bonus tiles, setup goods players (sono metodi diversi)
         */
    }

    public void setupPeriods() {
        /*
        creazione dei periodi, scelta delle scomuniche
         */
    }

    public void initPeriod() {
        /*
        metodo
         */
    }

    public List<DevelopmentCard> getDevelopmentCardsForPeriod(PeriodNumber periodNumber) {
        /*
        ritorna la lista di dev card del periodo selezionato (algoritmo come in player)
         */
        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public List<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public void setDevelopmentCards(List<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
