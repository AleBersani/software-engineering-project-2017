package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.client.model.Card;
import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.gameelements.BoardInformation;
import it.polimi.ingsw.server.gameelements.Cards;
import it.polimi.ingsw.server.gamelogic.basics.BoardConfiguration;
import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.PlayerConfiguration;
import it.polimi.ingsw.server.gamelogic.board.Board;
import it.polimi.ingsw.server.gamelogic.board.Space;
import it.polimi.ingsw.server.gamelogic.board.Tower;
import it.polimi.ingsw.server.gamelogic.board.TowerSlot;
import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.server.gamelogic.player.BonusTiles;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.server.gamelogic.player.PlayerBoard;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.server.middleware.ServerSender;
import it.polimi.ingsw.server.middleware.ServerSenderHandler;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.requests.serverclient.LeadersChoice;
import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;

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

    private LeaderCardChoiceHandler leaderCardChoiceHandler;
    private List<BonusTiles> bonusTiles;

    public static void main(String argv[]) {
        System.out.println(GeneralColor.values()[1]);
    }

    public Game(int gameId, Queue<ConnectedClient> connectedClients) {
        this.gameId = gameId;
        this.connectedClients = connectedClients;
        players = new ArrayList<>();
        periods = new ArrayList<>();
        developmentCards = new ArrayList<>();
        board = new Board();
        leaderCardChoiceHandler = new LeaderCardChoiceHandler();
        bonusTiles = new ArrayList<>();
    }

    @Override
    public void run() {
        LOGGER.info("New game started! ID: " + gameId);
        setupBoard();
    }

    private void setupBoard() {
        setupTowers();
        setupCouncilPalace();
        setupActionSpaces();
        basicSetupPlayers();
        leaderCardsExtraction();
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

    private void basicSetupPlayers() {
        int playerCounter = 0;
        for (ConnectedClient connectedClient : connectedClients) {
            players.add(new Player(
                    new PlayerDetails(connectedClient.getPlayerName(), GeneralColor.values()[playerCounter]),
                    new PlayerBoard(PlayerConfiguration.getStartingGoods().get(playerCounter))));
            playerCounter ++;
        }
    }

    private void leaderCardsExtraction() {
        List<LeaderCard> leaderCards = new ArrayList<>();
        UniqueRandomGenerator uniqueRandomGenerator = new UniqueRandomGenerator(Cards.getLeaderCards().size());
        List<Integer> leadersPositions = uniqueRandomGenerator.generateRandoms(
                players.size() * PlayerConfiguration.getNumberOfLeaders());
        leadersPositions.forEach((Integer integer) -> leaderCards.add(Cards.getLeaderCards().get(integer)));
        List<String> playersName = new ArrayList<>();
        for (ConnectedClient connectedClient : connectedClients) {
            playersName.add(connectedClient.getPlayerName());
        }
        Map<String, List<LeaderCard>> initialLeadersForPlayer = leaderCardChoiceHandler.setup(leaderCards, playersName);
        for (Map.Entry<String, List<LeaderCard>> entry : initialLeadersForPlayer.entrySet()) {
            List<String> leaderNames = new ArrayList<>();
            for (LeaderCard leaderCard : entry.getValue()) {
                leaderNames.add(leaderCard.getLeaderName());
            }
            sendTo(entry.getKey(), new LeadersChoice(leaderNames));
        }
    }

    private void bonusTilesSetup() {
        Map<String, List<ExchangingGoods>> bonusTilesDetails = BoardInformation.getBonusTiles();
        bonusTilesDetails.forEach((string, exchangingGoods) -> bonusTiles.add(new BonusTiles()));
    }

    @Override
    public void update(Observable o, Object arg) {
        /*
        avvia e setuppa altro periodo, se non è fine game
         */
    }

    private void sendToAll(ServerClientRequest serverClientRequest) {
        for (ConnectedClient connectedClient : connectedClients) {
            ServerSender serverSender = new ServerSenderHandler();
            serverSender.sendToClient(connectedClient.getConnectionStream(), serverClientRequest);
        }
    }

    private void sendTo(String playerName, ServerClientRequest serverClientRequest) {
        for (ConnectedClient connectedClient : connectedClients) {
            if (connectedClient.getPlayerName().equals(playerName)) {
                ServerSender serverSender = new ServerSenderHandler();
                serverSender.sendToClient(connectedClient.getConnectionStream(), serverClientRequest);
            }
        }
    }

    public void addChosenLeaderToPlayer(String leaderName) {

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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Queue<ConnectedClient> getConnectedClients() {
        return connectedClients;
    }

    public void setConnectedClients(Queue<ConnectedClient> connectedClients) {
        this.connectedClients = connectedClients;
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
