package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.client.model.enums.PointsLight;
import it.polimi.ingsw.client.model.enums.ResourcesLight;
import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.gamecontroller.helpers.rewards.BasicRewardsGenerator;
import it.polimi.ingsw.server.gamecontroller.helpers.requirements.RequirementsGenerator;
import it.polimi.ingsw.server.gamecontroller.helpers.requirements.RequirementsSupport;
import it.polimi.ingsw.server.gameelements.Cards;
import it.polimi.ingsw.server.gamelogic.actionsdescription.ActionDescription;
import it.polimi.ingsw.server.gamelogic.actionsdescription.CardAction;
import it.polimi.ingsw.server.gamelogic.board.*;
import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.server.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.LeaderRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.TowerActionRequirements;
import it.polimi.ingsw.server.gamelogic.player.Pawn;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.server.middleware.ServerSender;
import it.polimi.ingsw.server.middleware.ServerSenderHandler;
import it.polimi.ingsw.shared.model.DiceColor;
import it.polimi.ingsw.shared.model.GeneralColor;
import it.polimi.ingsw.shared.model.PawnColor;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;
import it.polimi.ingsw.shared.model.actionsdescription.LeaderAction;
import it.polimi.ingsw.shared.requests.serverclient.ServerClientRequest;
import it.polimi.ingsw.shared.requests.serverclient.UpdateGameBoard;
import it.polimi.ingsw.shared.requests.serverclient.UpdatePlayerBoard;
import it.polimi.ingsw.shared.requests.serverclient.YourTurn;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SemiPeriod extends Observable implements Observer, ActionVisitor {
    private final static Logger LOGGER = Logger.getLogger(SemiPeriod.class.getName());

    private List<DevelopmentCard> developmentCards;
    private List<Player> players;
    private Board board;

    private List<ConnectedClient> connectedClients;
    private List<PlayerDetails> playersOrder;
    private List<PlayerDetails> basePlayersOrder;
    private Map<PlayerDetails, List<ActionDescription>> actionsForPlayer;
    private boolean current;

    public SemiPeriod(List<DevelopmentCard> developmentCards, List<Player> players, Board board) {
        this.developmentCards = developmentCards;
        this.players = players;
        this.board = board;
        connectedClients = new ArrayList<>();
        playersOrder = new ArrayList<>();
        basePlayersOrder = new ArrayList<>();
        actionsForPlayer = new HashMap<>();
        current = false;
    }

    public void initSemiPeriod() {
        LOGGER.info("Init semi period started");
        putDevelopmentCardsOnTowers();
        rollDices();
        givePawnsToPlayers();
        calculatePlayersOrder();
        LOGGER.info("Init semi period ended, sending game board to players...");
        sendBoardToPlayers();
        LOGGER.info("Board sent to players, now sending players boards..");
        sendPlayerBoardToEachSeparatePlayer();
        sendTo(playersOrder.get(0).getPlayerName(), new YourTurn(true));
        LOGGER.log(Level.INFO, () -> "Turn token given to: " + players.get(0).getPlayerDetails().getPlayerName());
    }

    private void putDevelopmentCardsOnTowers() {
        List<DevelopmentCard> territories;
        List<DevelopmentCard> buildings;
        List<DevelopmentCard> characters;
        List<DevelopmentCard> ventures;
        territories = getParticularCardsList(GeneralColor.GREEN);
        buildings = getParticularCardsList(GeneralColor.YELLOW);
        characters = getParticularCardsList(GeneralColor.BLUE);
        ventures = getParticularCardsList(GeneralColor.PURPLE);
        putDevelopmentCardsOnSpecificTower(territories, GeneralColor.GREEN);
        putDevelopmentCardsOnSpecificTower(buildings, GeneralColor.YELLOW);
        putDevelopmentCardsOnSpecificTower(characters, GeneralColor.BLUE);
        putDevelopmentCardsOnSpecificTower(ventures, GeneralColor.PURPLE);
    }

    private List<DevelopmentCard> getParticularCardsList(GeneralColor color) {
        return developmentCards.stream()
                .filter(card -> color.equals(card.getCardInformation().getCardColor()))
                .collect(Collectors.toList());
    }

    private void putDevelopmentCardsOnSpecificTower(List<DevelopmentCard> cardsToAdd, GeneralColor color) {
        for (Tower tower : board.getTowers()) {
            if (tower.getColor() == color) {
                for (int i = 0; i < tower.getTowerSlots().size(); i++) {
                    tower.getTowerSlots().get(i).setDevelopmentCard(cardsToAdd.get(i));
                }
                break;
            }
        }
    }

    private void rollDices() {
        List<DiceColor> colors = new ArrayList<>();
        colors.add(DiceColor.BLACK);
        colors.add(DiceColor.ORANGE);
        colors.add(DiceColor.WHITE);

        Random random = new Random();

        for (DiceColor diceColor : colors) {
            Dice dice = new Dice(diceColor, random.nextInt(5) + 1);
            board.getDices().add(dice);
        }
    }

    private void givePawnsToPlayers() {
        for (Dice dice : board.getDices()) {
            for (Player player : players) {
                player.getPlayerBoard().getPawns().add(
                        new Pawn(dice.getValue(), PawnColor.valueOf(dice.getDiceColor().toString())));
            }
        }
        for (Player player : players) {
            player.getPlayerBoard().getPawns().add(new Pawn(0, PawnColor.NEUTRAL));
        }
    }

    private void calculatePlayersOrder() {
        Map<Integer, PlayerDetails> playerDetailsMap = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < basePlayersOrder.size(); j++) {
                playerDetailsMap.put(j + (i * basePlayersOrder.size()), basePlayersOrder.get(j));
            }
        }

        Map<Integer, PlayerDetails> orderedCopy = new HashMap<>();

        int counter = 0;
        for (Map.Entry<Integer, PlayerDetails> entry : playerDetailsMap.entrySet()) {
            if (counter < players.size()) {
                for (Player p : players) {
                    if (p.getPlayerDetails().equals(entry.getValue())) {
                        orderedCopy.put(entry.getKey() + p.getPlayerOrderWeight(), p.getPlayerDetails());
                    }
                }
            } else {
                orderedCopy.put(entry.getKey(), entry.getValue());
            }
            counter++;
        }

        playersOrder.clear();
        Map<Integer, PlayerDetails> treeMap = new TreeMap<>(orderedCopy);
        playersOrder.addAll(treeMap.values());
    }

    private void sendBoardToPlayers() {
        UpdateGameBoard updateGameBoard = setupUpdateGameBoard();
        sendToAll(updateGameBoard);
    }

    private UpdateGameBoard setupUpdateGameBoard() {
        UpdateGameBoard updateGameBoard = new UpdateGameBoard();

        updateGameBoard.setNewGreenTower(copyTowersInformation(board.getTowers().get(0).getTowerSlots()));
        updateGameBoard.setNewYellowTower(copyTowersInformation(board.getTowers().get(1).getTowerSlots()));
        updateGameBoard.setNewBlueTower(copyTowersInformation(board.getTowers().get(2).getTowerSlots()));
        updateGameBoard.setNewPurpleTower(copyTowersInformation(board.getTowers().get(3).getTowerSlots()));

        updateGameBoard.setNewProduction(copyProductionHarvestSpaces(board.getBoardActionSpaces().getProductionArea()));
        updateGameBoard.setNewHarvest(copyProductionHarvestSpaces(board.getBoardActionSpaces().getHarvestArea()));

        updateGameBoard.setNewMarket(copyMarket());

        updateGameBoard.getNewCouncilPalaceLight().setPawnLightList(copyCouncilPalacePawns());

        updateGameBoard.setNewPlayerLights(copyPlayersInformation());

        updateGameBoard.setDiceLightList(copyDices());

        updateGameBoard.setNewExcommunicationTiles(copyExcommunicationTiles());

        return updateGameBoard;
    }

    private List<TowerSlotLight> copyTowersInformation(List<TowerSlot> towerSlots) {
        List<TowerSlotLight> towerSlotLightList = new ArrayList<>();
        for (TowerSlot t : towerSlots) {
            PlayerPawn playerPawn = t.getSpace().getPlayerPawn();
            PawnLight pawnLight = null;
            if (PawnColor.UNCOLORED != playerPawn.getPawnColor()) {
                pawnLight = new PawnLight(playerPawn.getPlayerDetails().getPlayerName(), playerPawn.getPawnColor(), true);
            }
            TowerSlotLight towerSlotLight;
            if (t.getDevelopmentCard() != null) {
                towerSlotLight = new TowerSlotLight(
                        new SlotLight(t.getSpace().getBoardIdentifier(), t.getSpace().getRequestedValue(), pawnLight),
                        new Card(t.getDevelopmentCard().getBasicDevelopmentCard().getCardInformation().getName()));
            } else {
                towerSlotLight = new TowerSlotLight(
                        new SlotLight(t.getSpace().getBoardIdentifier(), t.getSpace().getRequestedValue(), pawnLight),
                        new Card("Empty"));
                if (PawnColor.UNCOLORED != t.getSpace().getPlayerPawn().getPawnColor()) {
                    towerSlotLight.getSlotLight().setPawnLight(
                            new PawnLight(t.getSpace().getPlayerPawn().getPlayerDetails().getPlayerName(),
                                    t.getSpace().getPlayerPawn().getPawnColor(), true));
                    System.out.println("Ci sono: " + towerSlotLight.getSlotLight().getPawnLight().toString());
                }
            }
            towerSlotLightList.add(towerSlotLight);
        }
        return towerSlotLightList;
    }

    private List<SlotLight> copyProductionHarvestSpaces(List<ProductionHarvestSpace> productionHarvestSpaceList) {
        List<SlotLight> slotLightList = new ArrayList<>();
        for (ProductionHarvestSpace productionHarvestSpace : productionHarvestSpaceList) {
            PlayerPawn playerPawn = productionHarvestSpace.getSpace().getPlayerPawn();
            PawnLight pawnLight = null;
            if (PawnColor.UNCOLORED != playerPawn.getPawnColor()) {
                pawnLight = new PawnLight(playerPawn.getPlayerDetails().getPlayerName(),
                        playerPawn.getPawnColor(), true);
            }
            slotLightList.add(new SlotLight(productionHarvestSpace.getSpace().getBoardIdentifier(),
                    productionHarvestSpace.getSpace().getRequestedValue(), pawnLight));
        }
        return slotLightList;
    }

    private List<SlotLight> copyMarket() {
        List<SlotLight> newMarket = new ArrayList<>();
        for (MarketSpace m : board.getBoardActionSpaces().getMarketArea()) {
            PlayerPawn playerPawn = m.getSpace().getPlayerPawn();
            PawnLight pawnLight = null;
            if (PawnColor.UNCOLORED != playerPawn.getPawnColor()) {
                pawnLight = new PawnLight(playerPawn.getPlayerDetails().getPlayerName(), playerPawn.getPawnColor(), true);
            }
            newMarket.add(new SlotLight(m.getSpace().getBoardIdentifier(), m.getSpace().getRequestedValue(), pawnLight));
        }
        return newMarket;
    }

    private List<PawnLight> copyCouncilPalacePawns() {
        List<PawnLight> councilPalacePawns = new ArrayList<>();
        for (PlayerPawn playerPawn : board.getCouncilPalace().getPlayerPawnList()) {
            councilPalacePawns.add(new PawnLight(playerPawn.getPlayerDetails().getPlayerName(),
                    playerPawn.getPawnColor(), true));
        }
        return councilPalacePawns;
    }

    private List<PlayerLight> copyPlayersInformation() {
        List<PlayerLight> playerLightList = new ArrayList<>();
        for (PlayerDetails playerDetails : basePlayersOrder) {
            for (Player player : players) {
                if (playerDetails.equals(player.getPlayerDetails())) {
                    PlayerLight playerLight = new PlayerLight(player.getPlayerDetails().getPlayerName(),
                            player.getPlayerDetails().getPlayerColor());
                    playerLight.setActivatedLeaders(copyActivatedLeadersOfPlayer(player));
                    playerLight.setNumberOfPoints(copyPointsOfPlayer(player));
                    playerLightList.add(playerLight);
                    break;
                }
            }
        }

        return playerLightList;
    }

    private List<Card> copyActivatedLeadersOfPlayer(Player player) {
        List<Card> activatedLeaders = new ArrayList<>();
        for (LeaderCard leaderCard : player.getLeaderCards()) {
            if (leaderCard.isPlacedOnBoard()) {
                activatedLeaders.add(new Card(leaderCard.getLeaderName()));
            }
        }
        return activatedLeaders;
    }

    private Map<PointsLight, Integer> copyPointsOfPlayer(Player player) {
        Map<PointsLight, Integer> pointsOfPlayer = new EnumMap<>(PointsLight.class);
        pointsOfPlayer.put(PointsLight.VICTORY_POINTS, player.getPlayerGoods().getPoints().getVictory());
        pointsOfPlayer.put(PointsLight.MILITARY_POINTS, player.getPlayerGoods().getPoints().getMilitary());
        pointsOfPlayer.put(PointsLight.FAITH_POINTS, player.getPlayerGoods().getPoints().getFaith());
        return pointsOfPlayer;
    }

    private List<DiceLight> copyDices() {
        List<DiceLight> newDiceLightList = new ArrayList<>();
        for (Dice d : board.getDices()) {
            newDiceLightList.add(new DiceLight(d.getDiceColor(), d.getValue()));
        }
        return newDiceLightList;
    }

    private List<Card> copyExcommunicationTiles() {
        List<Card> excommunicationTiles = new ArrayList<>();
        for (ExcommunicationTile excommunicationTile : board.getExcommunicationTiles()) {
            excommunicationTiles.add(new Card(excommunicationTile.getExcommunicationTileName()));
        }
        return excommunicationTiles;
    }

    private void sendPlayerBoardToEachSeparatePlayer() {
        for (Player player : players) {
            sendTo(player.getPlayerDetails().getPlayerName(), setupUpdatePlayerBoard(player));
        }
    }

    private UpdatePlayerBoard setupUpdatePlayerBoard(Player player) {
        String copyOfBonusTileIdentifier = player.getPlayerBoard().getBonusTiles().getBonusTileIdentifier();

        return new UpdatePlayerBoard(copyActivatedLeadersOfPlayer(player), copyPointsOfPlayer(player),
                copyOfBonusTileIdentifier, copyDeckLight(player), copyResourcesOfPlayer(player));
    }

    private DeckLight copyDeckLight(Player player) {
        List<Card> territories = new ArrayList<>();
        player.getPlayerBoard().getDeck().getTerritories()
                .forEach(card -> territories.add(new Card(card.getBasicDevelopmentCard().getCardInformation().getName())));
        List<Card> buildings = new ArrayList<>();
        player.getPlayerBoard().getDeck().getBuildings()
                .forEach(card -> buildings.add(new Card(card.getBasicDevelopmentCard().getCardInformation().getName())));
        List<Card> characters = new ArrayList<>();
        player.getPlayerBoard().getDeck().getCharacters()
                .forEach(card -> characters.add(new Card(card.getBasicDevelopmentCard().getCardInformation().getName())));
        List<Card> ventures = new ArrayList<>();
        player.getPlayerBoard().getDeck().getVentures()
                .forEach(card -> ventures.add(new Card(card.getBasicDevelopmentCard().getCardInformation().getName())));
        List<Card> leaders = new ArrayList<>();
        player.getLeaderCards().forEach(card -> leaders.add(new Card(card.getLeaderName())));

        return new DeckLight(territories, buildings, characters, ventures, leaders);
    }

    private Map<ResourcesLight, Integer> copyResourcesOfPlayer(Player player) {
        Map<ResourcesLight, Integer> numberOfResources = new EnumMap<>(ResourcesLight.class);
        numberOfResources.put(ResourcesLight.WOODS, player.getPlayerGoods().getResources().getWoods());
        numberOfResources.put(ResourcesLight.STONES, player.getPlayerGoods().getResources().getStones());
        numberOfResources.put(ResourcesLight.SERVANTS, player.getPlayerGoods().getResources().getServants());
        numberOfResources.put(ResourcesLight.COINS, player.getPlayerGoods().getResources().getCoins());
        return numberOfResources;
    }

    @Override
    public void update(Observable o, Object arg) {}

    @Override
    public void visitActionDescription(BoardAction boardAction) {
        LOGGER.info("Action: board action");
        for (Player player : players) {
            if (player.getPlayerDetails().getPlayerName().equals(playersOrder.get(0).getPlayerName())) {
                RequirementsGenerator requirementsGenerator =
                        new RequirementsGenerator(board, player, boardAction.getBasicAction().getBoardIdentifier());
                if (runRequirements(requirementsGenerator.generateRequirements(boardAction), player)) {
                    LOGGER.info("Action: player has requirements!");
                    BasicRewardsGenerator basicRewardsGenerator = new BasicRewardsGenerator(board);
                    basicRewardsGenerator.generateRewards(player, boardAction);
                    sendBoardToPlayers();
                    sendPlayerBoardToEachSeparatePlayer();
                } else {
                    // TODO: player non ha requisiti
                }
                break;
            }
        }
    }

    private boolean runRequirements(RequirementsSupport requirementsSupport, Player player) {
        Optional<TowerActionRequirements> optionalTowerActionRequirements =
                requirementsSupport.getTowerActionRequirements();
        Optional<BoardActionRequirements> optionalBoardActionRequirements =
                requirementsSupport.getBoardActionRequirements();
        if (optionalTowerActionRequirements.isPresent()) {
            TowerActionRequirements towerActionRequirements = optionalTowerActionRequirements.get();
            player.getRequirementsModifiers().forEach(m -> m.modifyRequirements(towerActionRequirements));
            return towerActionRequirements.hasRequirements(player);
        } else if (optionalBoardActionRequirements.isPresent()) {
            BoardActionRequirements boardActionRequirements = optionalBoardActionRequirements.get();
            player.getRequirementsModifiers().forEach(m -> m.modifyRequirements(boardActionRequirements));
            return boardActionRequirements.hasRequirements(player);
        }
        return false;
    }

    @Override
    public void visitActionDescription(CardAction cardAction) {

    }

    @Override
    public void visitActionDescription(LeaderAction leaderAction) {
        List<LeaderCard> leaderCards = Cards.getLeaderCards();
        for (LeaderCard leaderCard : leaderCards) {
            if (leaderCard.getLeaderName().equals(leaderAction.getLeaderName())) {
                for (Player player : players) {
                    if (player.getPlayerDetails().getPlayerName().equals(playersOrder.get(0).getPlayerName())) {
                        for (LeaderCost leaderCost : leaderCard.getLeaderCosts()) {
                            LeaderRequirements leaderRequirements =
                                    new LeaderRequirements(leaderAction.getActionType(), leaderAction.getLeaderName(), leaderCost);
                            if (leaderRequirements.hasRequirements(player)) {

                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public void endSemiPeriod() {}

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

    public List<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public void setDevelopmentCards(List<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<ConnectedClient> getConnectedClients() {
        return connectedClients;
    }

    public void setConnectedClients(List<ConnectedClient> connectedClients) {
        this.connectedClients = connectedClients;
    }

    public List<PlayerDetails> getPlayersOrder() {
        return playersOrder;
    }

    public void setPlayersOrder(List<PlayerDetails> playersOrder) {
        this.playersOrder = playersOrder;
    }

    public List<PlayerDetails> getBasePlayersOrder() {
        return basePlayersOrder;
    }

    public void setBasePlayersOrder(List<PlayerDetails> basePlayersOrder) {
        this.basePlayersOrder = basePlayersOrder;
    }

    public Map<PlayerDetails, List<ActionDescription>> getActionsForPlayer() {
        return actionsForPlayer;
    }

    public void setActionsForPlayer(Map<PlayerDetails, List<ActionDescription>> actionsForPlayer) {
        this.actionsForPlayer = actionsForPlayer;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
