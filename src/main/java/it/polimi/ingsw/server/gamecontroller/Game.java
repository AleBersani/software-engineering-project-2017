package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.gamecontroller.helpers.DevelopmentCardsFilter;
import it.polimi.ingsw.server.gamecontroller.helpers.Sender;
import it.polimi.ingsw.server.gamecontroller.helpers.UniqueRandomGenerator;
import it.polimi.ingsw.server.gamecontroller.helpers.choices.BonusTileChoiceHandler;
import it.polimi.ingsw.server.gamecontroller.helpers.choices.CouncilPrivilegeChoiceHandler;
import it.polimi.ingsw.server.gamecontroller.helpers.choices.LeaderCardChoiceHandler;
import it.polimi.ingsw.server.gameelements.AdditionalInfoMaps;
import it.polimi.ingsw.server.gameelements.BoardInformation;
import it.polimi.ingsw.server.gameelements.Cards;
import it.polimi.ingsw.server.gamelogic.actionsdescription.ActionDescription;
import it.polimi.ingsw.server.gamelogic.basics.GameConfiguration;
import it.polimi.ingsw.server.gamelogic.basics.PlayerConfiguration;
import it.polimi.ingsw.server.gamelogic.board.*;
import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.AdditionalCardInfo;
import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.CardFlashAction;
import it.polimi.ingsw.server.gamelogic.cards.development.*;
import it.polimi.ingsw.server.gamelogic.cards.development.Character;
import it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.server.gamelogic.player.*;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.model.PawnColor;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;
import it.polimi.ingsw.shared.requests.serverclient.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Main class responsible for the set up of every game with its elements
 */

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
    private AtomicInteger numberOfPlayersReadyToPlay;

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
        numberOfPlayersReadyToPlay = new AtomicInteger(0);
    }

    /*
        RUN: INITIAL SETUP
     */

    @Override
    public void run() {
        LOGGER.log(Level.INFO,() -> "New game started! ID: " + gameId);
        setupGame();
    }

    /**
     * Sets up the game calling all the methods for the single components
     */
    private void setupGame() {
        sendGameIdToPlayers();
        setupTowers();
        setupCouncilPalace();
        setupActionSpaces();
        basicSetupPlayers();
        constructPeriods();
        sendGameInitToPlayers();
    }

    /**
     * Sends the game Id to the players
     */
    private void sendGameIdToPlayers() {
        LOGGER.log(Level.INFO, () -> "Sending game ID to players in game: " + gameId);
        Sender sender = new Sender(connectedClients);
        sender.sendToAll(new UpdateGameId(gameId));
    }

    /**
     * Sets up the towers, putting a space and the board information in each tower slot
     * TODO: Test
     */
    public void setupTowers() {
        LOGGER.info("Setup towers");
        List<Tower> towers = new ArrayList<>();

        List<TowerSlot> greenTowerSlot = new ArrayList<>();
        for (Space space : BoardInformation.getGreenTower().keySet()) {
            greenTowerSlot.add(new TowerSlot(space, BoardInformation.getGreenTower().get(space)));
        }
        Tower greenTower = new Tower(GeneralColor.GREEN, greenTowerSlot);
        towers.add(greenTower);

        List<TowerSlot> yellowTowerSlot = new ArrayList<>();
        for (Space space : BoardInformation.getYellowTower().keySet()) {
            yellowTowerSlot.add(new TowerSlot(space, BoardInformation.getYellowTower().get(space)));
        }
        Tower yellowTower = new Tower(GeneralColor.YELLOW, yellowTowerSlot);
        towers.add(yellowTower);

        List<TowerSlot> blueTowerSlot = new ArrayList<>();
        for (Space space : BoardInformation.getBlueTower().keySet()) {
            blueTowerSlot.add(new TowerSlot(space, BoardInformation.getBlueTower().get(space)));
        }
        Tower blueTower = new Tower(GeneralColor.BLUE, blueTowerSlot);
        towers.add(blueTower);

        List<TowerSlot> purpleTowerSlot = new ArrayList<>();
        for (Space space : BoardInformation.getPurpleTower().keySet()) {
            purpleTowerSlot.add(new TowerSlot(space, BoardInformation.getPurpleTower().get(space)));
        }
        Tower purpleTower = new Tower(GeneralColor.PURPLE, purpleTowerSlot);
        towers.add(purpleTower);

        board.setTowers(towers);
    }

    /**
     * Sets up the Council Palace with the board information
     * TODO: Test
     */
    public void setupCouncilPalace() {
        LOGGER.info("Setup council palace");
        board.setCouncilPalace(new CouncilPalace(BoardInformation.getCouncilPalace()));
    }

    /**
     * Sets up the the action spaces with the board information
     * TODO: Test
     */
    public void setupActionSpaces() {
        LOGGER.info("Setup action spaces");
        board.getBoardActionSpaces().setProductionArea(BoardInformation.getProductionArea());
        board.getBoardActionSpaces().setHarvestArea(BoardInformation.getHarvestArea());
        board.getBoardActionSpaces().setMarketArea(BoardInformation.getMarketArea());
    }

    /**
     * Sets up players' data and increments the number of players in the playerCounter
     */
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

    /**
     * Sets up the game configurations, with the period, the excommunication tiles an the development cards of
     * each period
     * TODO: Test
     */
    public void constructPeriods() {
        LOGGER.info("Construct periods");
        Map<PeriodNumber, List<DevelopmentCard>> developmentCardsForPeriod = generateDevelopmentCardsForPeriod();

        for (int i = 0; i < GameConfiguration.getNumberOfPeriods(); i++) {
            PeriodNumber periodNumber = PeriodNumber.values()[i];
            ExcommunicationTile excommunicationTile = getExcommunicationTilePerPeriodNumber(periodNumber);
            periods.add(new Period(excommunicationTile, developmentCardsForPeriod.get(periodNumber), periodNumber));
            board.getExcommunicationTiles().add(excommunicationTile);
        }

        generateInitialPlayerOrder();
        periods.get(0).setPlayersOrder(playersOrder);
    }

    /**
     * Generates and selects the development cards of each period
     * TODO: Test
     */
    public Map<PeriodNumber, List<DevelopmentCard>> generateDevelopmentCardsForPeriod() {
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

    /**
     * TODO: JavaDoc + Test
     */
    public ExcommunicationTile getExcommunicationTilePerPeriodNumber(PeriodNumber periodNumber) {
        UniqueRandomGenerator uniqueRandomGenerator = new UniqueRandomGenerator(
                Cards.getExcommunicationTiles().size() / GameConfiguration.getNumberOfPeriods());
        int excommunicationTilePosition = uniqueRandomGenerator.generateRandom();
        List<ExcommunicationTile> excommunicationTilesForPeriod = Cards.getExcommunicationTiles().stream()
                .filter(e -> e.getPeriod() == periodNumber)
                .collect(Collectors.toList());
        return excommunicationTilesForPeriod.get(excommunicationTilePosition);
    }

    /**
     * TODO: JavaDoc + Test
     */
    public void generateInitialPlayerOrder() {
        for (Player player : players) {
            playersOrder.add(player.getPlayerDetails());
        }
    }

    /**
     * TODO: JavaDoc
     */
    private void sendGameInitToPlayers() {
        LOGGER.info("Sending game init to players");
        Sender sender = new Sender(connectedClients);
        players.forEach(p -> sender.sendTo(p.getPlayerDetails().getPlayerName(),
                new ChosenGameResponse(true, p.getPlayerDetails().getPlayerColor())));
        sender.sendToAll(new YourTurn(false));
    }

    /**
     * TODO: JavaDoc
     * @param o Actual period
     * @param arg optional arguments passed by the notifier
     */
    @Override
    public void update(Observable o, Object arg) {
        boolean endGame = false;
        for (Period period : periods) {
            if (PeriodNumber.THIRD == period.getPeriodNumber() && period.isCurrent()) {
                LOGGER.info("END GAME");
                endGame = true;
            }
        }
        if (!endGame) {
            setupAndStartNewPeriod();
        }
    }

    /*
        METHODS CALLED BY MIDDLEWARE
     */

    /**
     * TODO: JavaDoc
     */
    public void checkIfPlayersAreReadyAndStartLeadersChoice() {
        if (checkIfPlayersAreReady()) {
            LOGGER.info("Players are ready, starting leaders choice!");
            startLeaderChoice();
        }
    }

    /**
     * TODO: JavaDoc
     */
    private boolean checkIfPlayersAreReady() {
        int numberOfPlayersReady = leaderCardChoiceHandler.getNumberOfPlayersReady().incrementAndGet();
        return numberOfPlayersReady == connectedClients.size();
    }

    /**
     * TODO: JavaDoc
     */
    private void startLeaderChoice() {
        LOGGER.info("Start leader cards choice");
        List<String> playersName = new ArrayList<>();
        for (ConnectedClient connectedClient : connectedClients) {
            playersName.add(connectedClient.getPlayerName());
        }

        leaderCardChoiceHandler.setup(leaderCardsExtraction(), playersName);
        sendLeaderChoiceToPlayers();
    }

    /**
     * TODO: JavaDoc + Test
     */
    public List<LeaderCard> leaderCardsExtraction() {
        LOGGER.info("Extract random leader cards");
        List<LeaderCard> leaderCards = new ArrayList<>();
        UniqueRandomGenerator uniqueRandomGenerator = new UniqueRandomGenerator(Cards.getLeaderCards().size());
        List<Integer> leadersPositions = uniqueRandomGenerator.generateRandoms(
                players.size() * PlayerConfiguration.getNumberOfLeaders());
        leadersPositions.forEach((Integer integer) -> leaderCards.add(Cards.getLeaderCards().get(integer)));

        return leaderCards;
    }

    /**
     * TODO: JavaDoc
     */
    private void sendLeaderChoiceToPlayers() {
        Sender sender = new Sender(connectedClients);
        for (Map.Entry<String, List<LeaderCard>> entry :
                leaderCardChoiceHandler.getInitialLeaderCardsForPlayers().entrySet()) {
            List<String> leaderNames = new ArrayList<>();
            entry.getValue().forEach(leaderCard -> leaderNames.add(leaderCard.getLeaderName()));
            sender.sendTo(entry.getKey(), new LeadersChoice(leaderNames));
        }
    }

    /**
     * TODO: JavaDoc
     */
    public void addChosenLeaderToPlayer(String playerName, String leaderName) {
        boolean needToResend = leaderCardChoiceHandler.addChosenLeaderToPlayer(playerName, leaderName);
        if (needToResend) {
            sendLeaderChoiceToPlayers();
        }
        if (leaderCardChoiceHandler.phaseEnded()) {
            LOGGER.info("Players have chosen leaders!");
            addLeaderCardsToPlayers();
            Sender sender = new Sender(connectedClients);
            sender.sendToAll(new EndLeadersChoicePhase());
        }
    }

    /**
     * TODO: JavaDoc + Test
     */
    public void addLeaderCardsToPlayers() {
        for (Map.Entry<String, List<LeaderCard>> entry : leaderCardChoiceHandler.getLeaderCardsForPlayers().entrySet()) {
            players.forEach(p -> {
                if (p.getPlayerDetails().getPlayerName().equals(entry.getKey())) {
                    p.setLeaderCards(entry.getValue());
                }
            });
        }
    }

    /**
     * TODO: JavaDoc
     */
    public void bonusTilesSetup() {
        int numberOfPlayersReady = bonusTileChoiceHandler.getNumberOfPlayersReady().incrementAndGet();
        if (numberOfPlayersReady == connectedClients.size()) {
            LOGGER.info("Players are ready, starting bonus tile choice");
            startTileChoice();
        }
    }

    /**
     * TODO: JavaDoc
     */
    private void startTileChoice() {
        List<String> playersName = new ArrayList<>();
        connectedClients.forEach(c -> playersName.add(c.getPlayerName()));
        Collections.reverse(playersName);

        bonusTileChoiceHandler.setup(playersName);
        sendBonusTileChoiceToNextPlayer();
    }

    /**
     * TODO: JavaDoc
     */
    private void sendBonusTileChoiceToNextPlayer() {
        Sender sender = new Sender(connectedClients);
        sender.sendTo(bonusTileChoiceHandler.getNextPlayer(),
                new TileChoice(bonusTileChoiceHandler.getRemainingBonusTiles()));
    }

    /**
     * TODO: JavaDoc
     */
    public void addBonusTileToPlayer(String playerName, String bonusTileIdentifier) {
        bonusTileChoiceHandler.addBonusTileToPlayer(playerName, bonusTileIdentifier);
        if (bonusTileChoiceHandler.phaseEnded()) {
            LOGGER.info("Players have chosen their bonus tile!");
            addBonusTilesToPlayers();
            Sender sender = new Sender(connectedClients);
            sender.sendToAll(new TileChoice(null));
        } else {
            sendBonusTileChoiceToNextPlayer();
        }
    }

    /**
     * TODO: JavaDoc + Test
     */
    public void addBonusTilesToPlayers() {
        for (Map.Entry<String, BonusTiles> entry : bonusTileChoiceHandler.getBonusTileForPlayer().entrySet()) {
            players.forEach(p -> {
                if (p.getPlayerDetails().getPlayerName().equals(entry.getKey())) {
                    p.getPlayerBoard().setBonusTiles(entry.getValue());
                }
            });
        }
    }

    /**
     * TODO: JavaDoc
     */
    public void setupAndStartFirstPeriod() {
        if (numberOfPlayersReadyToPlay.incrementAndGet() == players.size())  {
            setupAndStartNewPeriod();
        }
    }

    /**
     * TODO: JavaDoc
     */
    private void setupAndStartNewPeriod() {
        for (Period period : periods) {
            if (!period.isCurrent()) {
                setPeriodInformation(period);
                if (PeriodNumber.FIRST != period.getPeriodNumber()) {
                    setNewStartingPlayerOrderForPeriod();
                }
                LOGGER.log(Level.INFO, () -> "Starting Period: " + period.getPeriodNumber());
                period.startSemiPeriod();
                break;
            }
        }
    }

    /**
     * TODO: JavaDoc + Test
     */
    public void setPeriodInformation(Period period) {
        period.setConnectedClients(connectedClients);
        period.setPlayers(players);
        period.setBoard(board);
        period.setCurrent(true);
    }

    /**
     * TODO: JavaDoc + Test
     */
    public void setNewStartingPlayerOrderForPeriod() {
        for (int i = 0; i < periods.size(); i++) {
            if (periods.get(i).isCurrent()) {
                Period secondLastPeriod = periods.get(i - 1);
                List<PlayerDetails> basePlayerOrder = secondLastPeriod.calculateNewPlayerOrder(secondLastPeriod.getLastSemiperiod().getBasePlayersOrder(),
                        secondLastPeriod.getLastSemiperiod().getBoard().getCouncilPalace().getPlayerOrder());
                periods.get(i).setPlayersOrder(basePlayerOrder);
            }
        }
    }

    /**
     * TODO: JavaDoc
     */
    public void activatePlayerAction(ActionDescription actionDescription) {
        periods.forEach(p -> p.getSemiPeriods().forEach(sp -> {
            if (sp.isCurrent()) {
                actionDescription.acceptActionVisitor(sp);
            }
        }));
    }

    /**
     * TODO: JavaDoc
     */
    public void executeCouncilPrivilegeChoice(String playerName, List<Integer> choices, BoardAction boardAction) {
        for (Player player : players) {
            if (player.getPlayerDetails().getPlayerName().equals(playerName)) {
                CouncilPrivilegeChoiceHandler councilPrivilegeChoiceHandler = new CouncilPrivilegeChoiceHandler(
                        choices, player, boardAction);
                councilPrivilegeChoiceHandler.addModifiedCouncilPrivilegeChoices();
                updateClientsSemiPeriod();
                break;
            }
        }
    }

    public void updateClientsSemiPeriod() {
        periods.forEach(period -> {
            if (period.isCurrent()) {
                period.getSemiPeriods().forEach(semiPeriod -> {
                    if (semiPeriod.isCurrent()) {
                        semiPeriod.sendBoardToPlayers();
                        semiPeriod.sendPlayerBoardToEachSeparatePlayer();
                    }
                });
            }
        });
    }

    public void swapLorenzo(String playerName, String chosenLeader) {
        for (Player player : players) {
            if (player.getPlayerDetails().getPlayerName().equals(playerName)) {
                player.getLeaderCards().forEach(leaderCard -> {
                    if ("Lorenzo de' Medici".equals(leaderCard.getLeaderName())) {
                        for (LeaderCard lc : Cards.getLeaderCards()) {
                            if (lc.getLeaderName().equals(chosenLeader)) {
                                player.getLeaderCards().add(lc);
                                player.getLeaderCards().remove(leaderCard);
                            }
                        }
                    }
                });
            }
        }
    }

    public void executeChosenConsumableAction(String playerName, BoardAction boardAction, String cardName) {
        searchCard(playerName, AdditionalInfoMaps.getPermanentEffectsOnChoice(), boardAction);
        searchCard(playerName, AdditionalInfoMaps.getFlashEffectsOnChoice(), boardAction);
        for (Player player : players) {
            if (player.getPlayerDetails().getPlayerName().equals(playerName)) {
                Pawn pawn = new Pawn(boardAction.getBasicAction().getActionValue(), PawnColor.UNCOLORED);
                player.getPlayerBoard().getPawns().add(pawn);
                periods.forEach(period -> {
                    if (period.isCurrent()) {
                        period.getSemiPeriods().forEach(semiPeriod -> {
                            if (semiPeriod.isCurrent()) {
                                semiPeriod.visitActionDescription(boardAction);
                                player.getPlayerBoard().getPawns().remove(pawn);
                                semiPeriod.sendBoardToPlayers();
                                semiPeriod.sendPlayerBoardToEachSeparatePlayer();
                            }
                        });
                    }
                });
            }
        }
    }

    public void searchCard(String cardName, Map<String, List<AdditionalCardInfo>> additionalEffectsMap, BoardAction boardAction) {
        for (Map.Entry<String, List<AdditionalCardInfo>> entry : additionalEffectsMap.entrySet()) {
            if (entry.getKey().equals(cardName)) {
                if (entry.getValue().get(0) instanceof CardFlashAction) {
                    CardFlashAction cardFlashAction = (CardFlashAction)entry.getValue().get(0);
                    boardAction.getBasicAction().setActionValue(cardFlashAction.getActionValue());
                }
            }
        }
    }

    public void playerEndTurn(String playerName) {
        periods.forEach(period -> {
            if (period.isCurrent()) {
                period.getSemiPeriods().forEach(semiPeriod -> {
                    if (semiPeriod.isCurrent()) {
                        semiPeriod.passTurn();
                    }
                });
            }
        });
    }

    /*
        GETTERS AND SETTERS
     */

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
