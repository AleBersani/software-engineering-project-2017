package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.gameelements.BoardInformation;
import it.polimi.ingsw.server.gameelements.Cards;
import it.polimi.ingsw.server.gamelogic.basics.GameConfiguration;
import it.polimi.ingsw.server.gamelogic.basics.PlayerConfiguration;
import it.polimi.ingsw.server.gamelogic.board.*;
import it.polimi.ingsw.server.gamelogic.cards.development.*;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.server.gamelogic.player.BonusTiles;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.server.gamelogic.player.PlayerBoard;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.server.middleware.ServerSender;
import it.polimi.ingsw.server.middleware.ServerSenderHandler;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.requests.serverclient.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Game implements Runnable, Observer {
    private final static Logger LOGGER = Logger.getLogger(Game.class.getName());

    private int gameId;
    private List<ConnectedClient> connectedClients;

    private List<Player> players;
    private List<PlayerDetails> playersOrder;
    private List<Period> periods;
    private List<DevelopmentCard> developmentCards;
    private Board board;

    private LeaderCardChoiceHandler leaderCardChoiceHandler;
    private BonusTileChoiceHandler bonusTileChoiceHandler;

    public Game(int gameId, Queue<ConnectedClient> connectedClients) {
        this.gameId = gameId;
        this.connectedClients = new ArrayList<>(connectedClients);
        players = new ArrayList<>();
        playersOrder = new ArrayList<>();
        periods = new ArrayList<>();
        developmentCards = new ArrayList<>();
        board = new Board();
        leaderCardChoiceHandler = new LeaderCardChoiceHandler();
        bonusTileChoiceHandler = new BonusTileChoiceHandler();
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO,() -> "New game started! ID: " + gameId);
        setupGame();
    }

    private void setupGame() {
        sendGameIdToPlayers();
        setupTowers();
        setupCouncilPalace();
        setupActionSpaces();
        basicSetupPlayers();
        setupPeriods();
        sendGameInitToPlayers();
    }

    private void sendGameInitToPlayers() {
        LOGGER.info("Sending game init to players");
        players.forEach(p -> sendTo(p.getPlayerDetails().getPlayerName(),
                new ChosenGameResponse(true, p.getPlayerDetails().getPlayerColor())));
    }

    private void sendGameIdToPlayers() {
        LOGGER.log(Level.INFO, () -> "Sending game ID to players in game: " + gameId);
        sendToAll(new UpdateGameId(gameId));
    }

    private void setupTowers() {
        LOGGER.info("Setup towers");
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
        LOGGER.info("Setup council palace");
        board.setCouncilPalace(new CouncilPalace(BoardInformation.getCouncilPalace()));
    }

    private void setupActionSpaces() {
        LOGGER.info("Setup action spaces");
        board.getBoardActionSpaces().setProductionArea(BoardInformation.getProductionArea());
        board.getBoardActionSpaces().setHarvestArea(BoardInformation.getHarvestArea());
        board.getBoardActionSpaces().setMarketArea(BoardInformation.getMarketArea());
    }

    private void basicSetupPlayers() {
        LOGGER.info("Setup players");
        int playerCounter = 0;
        for (ConnectedClient connectedClient : connectedClients) {
            players.add(new Player(
                    new PlayerDetails(connectedClient.getPlayerName(), GeneralColor.values()[playerCounter]),
                    new PlayerBoard(PlayerConfiguration.getStartingGoods().get(playerCounter))));
            playerCounter ++;
        }
    }

    private void setupPeriods() {
        Map<PeriodNumber, List<DevelopmentCard>> developmentCardsForPeriod = generateDevelopmentCardsForPeriod();

        for (int i = 0; i < GameConfiguration.getNumberOfPeriods(); i++) {
            PeriodNumber periodNumber = PeriodNumber.values()[i];
            periods.add(new Period(getExcommunicationTilePerPeriodNumber(periodNumber),
                    developmentCardsForPeriod.get(periodNumber), periodNumber));
        }
        periods.get(0).setPlayersOrder(playersOrder);
    }

    private Map<PeriodNumber, List<DevelopmentCard>> generateDevelopmentCardsForPeriod() {
        Map<PeriodNumber, List<DevelopmentCard>> developmentCardsForPeriod = new EnumMap<>(PeriodNumber.class);

        for (int i = 0; i < GameConfiguration.getNumberOfPeriods(); i++) {
            List<DevelopmentCard> developmentCards = new ArrayList<>();

            DevelopmentCardsFilter<Territory> territories = new DevelopmentCardsFilter<>(Cards.getTerritories());
            developmentCards.addAll(territories.returnDevelopmentCardsForPeriod(i));

            DevelopmentCardsFilter<Building> buildings = new DevelopmentCardsFilter<>(Cards.getBuildings());
            developmentCards.addAll(buildings.returnDevelopmentCardsForPeriod(i));

            DevelopmentCardsFilter<Character> characters = new DevelopmentCardsFilter<>(Cards.getCharacters());
            developmentCards.addAll(characters.returnDevelopmentCardsForPeriod(i));

            DevelopmentCardsFilter<Venture> ventures = new DevelopmentCardsFilter<>(Cards.getVentures());
            developmentCards.addAll(ventures.returnDevelopmentCardsForPeriod(i));

            developmentCardsForPeriod.put(PeriodNumber.values()[i], developmentCards);
        }

        return developmentCardsForPeriod;
    }

    private ExcommunicationTile getExcommunicationTilePerPeriodNumber(PeriodNumber periodNumber) {
        UniqueRandomGenerator uniqueRandomGenerator = new UniqueRandomGenerator(
                Cards.getExcommunicationTiles().size() / GameConfiguration.getNumberOfPeriods());
        int excommunicationTilePosition = uniqueRandomGenerator.generateRandom();
        List<ExcommunicationTile> excommunicationTilesForPeriod = Cards.getExcommunicationTiles().stream()
                .filter(e -> e.getPeriod() == periodNumber)
                .collect(Collectors.toList());
        return excommunicationTilesForPeriod.get(excommunicationTilePosition);
    }

    @Override
    public void update(Observable o, Object arg) {}

    public void checkAndStartLeaderChoice() {
        int numberOfPlayersReady = leaderCardChoiceHandler.getNumberOfPlayersReady().incrementAndGet();
        if (numberOfPlayersReady == connectedClients.size()) {
            LOGGER.info("Players are ready, starting leaders choice!");
            startLeaderChoice();
        }
    }

    private void startLeaderChoice() {
        List<String> playersName = new ArrayList<>();
        for (ConnectedClient connectedClient : connectedClients) {
            playersName.add(connectedClient.getPlayerName());
        }

        leaderCardChoiceHandler.setup(leaderCardsExtraction(), playersName);
        sendLeaderChoiceToPlayers();
    }

    private List<LeaderCard> leaderCardsExtraction() {
        LOGGER.info("Starting leader cards choice");
        List<LeaderCard> leaderCards = new ArrayList<>();
        UniqueRandomGenerator uniqueRandomGenerator = new UniqueRandomGenerator(Cards.getLeaderCards().size());
        List<Integer> leadersPositions = uniqueRandomGenerator.generateRandoms(
                players.size() * PlayerConfiguration.getNumberOfLeaders());
        leadersPositions.forEach((Integer integer) -> leaderCards.add(Cards.getLeaderCards().get(integer)));

        return leaderCards;
    }

    private void sendLeaderChoiceToPlayers() {
        for (Map.Entry<String, List<LeaderCard>> entry :
                leaderCardChoiceHandler.getInitialLeaderCardsForPlayers().entrySet()) {
            List<String> leaderNames = new ArrayList<>();
            entry.getValue().forEach(leaderCard -> leaderNames.add(leaderCard.getLeaderName()));
            sendTo(entry.getKey(), new LeadersChoice(leaderNames));
        }
    }

    public void addChosenLeaderToPlayer(String playerName, String leaderName) {
        boolean needToResend = leaderCardChoiceHandler.addChosenLeaderToPlayer(playerName, leaderName);
        if (needToResend) {
            sendLeaderChoiceToPlayers();
        }
        if (leaderCardChoiceHandler.phaseEnded()) {
            LOGGER.info("Players have chosen leaders!");
            addLeaderCardsToPlayers();
            sendToAll(new EndLeadersChoicePhase());
        }
    }

    private void addLeaderCardsToPlayers() {
        for (Map.Entry<String, List<LeaderCard>> entry : leaderCardChoiceHandler.getLeaderCardsForPlayers().entrySet()) {
            players.forEach(p -> {
                if (p.getPlayerDetails().getPlayerName().equals(entry.getKey())) {
                    p.setLeaderCards(entry.getValue());
                }
            });
        }
    }

    public void bonusTilesSetup() {
        int numberOfPlayersReady = bonusTileChoiceHandler.getNumberOfPlayersReady().incrementAndGet();
        if (numberOfPlayersReady == connectedClients.size()) {
            LOGGER.info("Players are ready, starting bonus tile choice");
            startTileChoice();
        }
    }

    private void startTileChoice() {
        List<String> playersName = new ArrayList<>();
        connectedClients.forEach(c -> playersName.add(c.getPlayerName()));
        Collections.reverse(playersName);

        bonusTileChoiceHandler.setup(playersName);
        sendBonusTileChoiceToNextPlayer();
    }

    public void sendBonusTileChoiceToNextPlayer() {
        sendTo(bonusTileChoiceHandler.getNextPlayer(), new TileChoice(bonusTileChoiceHandler.getRemainingBonusTiles()));
    }

    public void addBonusTileToPlayer(String playerName, String bonusTileIdentifier) {
        bonusTileChoiceHandler.addBonusTileToPlayer(playerName, bonusTileIdentifier);
        if (bonusTileChoiceHandler.phaseEnded()) {
            LOGGER.info("Players have chosen their bonus tile!");
            addBonusTilesToPlayers();
            sendToAll(new TileChoice(null));
        } else {
            sendBonusTileChoiceToNextPlayer();
        }
    }

    private void addBonusTilesToPlayers() {
        for (Map.Entry<String, BonusTiles> entry : bonusTileChoiceHandler.getBonusTileForPlayer().entrySet()) {
            players.forEach(p -> {
                if (p.getPlayerDetails().getPlayerName().equals(entry.getKey())) {
                    p.getPlayerBoard().setBonusTiles(entry.getValue());
                }
            });
        }
    }

    public void initPeriod() {
        for (Period period : periods) {
            if (!period.isCurrent()) {
                setPeriodInformation(period);
                if (PeriodNumber.FIRST != period.getPeriodNumber()) {
                    setNewStartingPlayerOrderForPeriod(period);
                }
                LOGGER.log(Level.INFO, () -> "Starting Period: " + period.getPeriodNumber());
                period.startSemiPeriod();
                break;
            }
        }
    }

    private void setPeriodInformation(Period period) {
        period.setConnectedClients(connectedClients);
        period.setPlayers(players);
        period.setBoard(board);
        period.setCurrent(true);
    }

    private void setNewStartingPlayerOrderForPeriod(Period period) {
        //period.calculateNewPlayerOrder();
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public List<ConnectedClient> getConnectedClients() {
        return connectedClients;
    }

    public void setConnectedClients(List<ConnectedClient> connectedClients) {
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

    public LeaderCardChoiceHandler getLeaderCardChoiceHandler() {
        return leaderCardChoiceHandler;
    }

    public void setLeaderCardChoiceHandler(LeaderCardChoiceHandler leaderCardChoiceHandler) {
        this.leaderCardChoiceHandler = leaderCardChoiceHandler;
    }

    public BonusTileChoiceHandler getBonusTileChoiceHandler() {
        return bonusTileChoiceHandler;
    }

    public void setBonusTileChoiceHandler(BonusTileChoiceHandler bonusTileChoiceHandler) {
        this.bonusTileChoiceHandler = bonusTileChoiceHandler;
    }
}
