package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.client.model.enums.PointsLight;
import it.polimi.ingsw.client.model.enums.ResourcesLight;
import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.gameelements.Cards;
import it.polimi.ingsw.server.gamelogic.actionsdescription.ActionDescription;
import it.polimi.ingsw.server.gamelogic.actionsdescription.CardAction;
import it.polimi.ingsw.server.gamelogic.board.*;
import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;
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
        setBoardCards();
        board.setDices(extractDicesAndSetPawns());
        calculatePlayersOrder();
        sendBoardsToPlayers();
        LOGGER.info("Init semi period ended");
        sendTo(playersOrder.get(0).getPlayerName(), new YourTurn(true));
        LOGGER.log(Level.INFO, () -> "Turn token given to: " + players.get(0).getPlayerDetails().getPlayerName());
    }

    private void sendBoardsToPlayers() {
        for (Player player : players) {
            sendTo(player.getPlayerDetails().getPlayerName(), setupUpdatePlayerBoard(player));
        }
        sendToAll(setupUpdateGameBoard());
    }

    private UpdatePlayerBoard setupUpdatePlayerBoard(Player player) {
        List<Card> newActivatedLeaders = new ArrayList<>();
        board.getLeaderInformationList().forEach(l -> newActivatedLeaders.add(new Card(l.getName())));

        Map<PointsLight, Integer> newNumberOfPoints = new EnumMap<>(PointsLight.class);
        newNumberOfPoints.put(PointsLight.VICTORY_POINTS, player.getPlayerGoods().getPoints().getVictory());
        newNumberOfPoints.put(PointsLight.MILITARY_POINTS, player.getPlayerGoods().getPoints().getMilitary());
        newNumberOfPoints.put(PointsLight.FAITH_POINTS, player.getPlayerGoods().getPoints().getFaith());

        String newBonusTileIdentifier = player.getPlayerBoard().getBonusTiles().getBonusTileIdentifier();

        DeckLight newDeckLight = setupDeckLight(player);

        Map<ResourcesLight, Integer> newNumberOfResources = new EnumMap<>(ResourcesLight.class);
        newNumberOfResources.put(ResourcesLight.WOODS, player.getPlayerGoods().getResources().getWoods());
        newNumberOfResources.put(ResourcesLight.STONES, player.getPlayerGoods().getResources().getStones());
        newNumberOfResources.put(ResourcesLight.SERVANTS, player.getPlayerGoods().getResources().getServants());
        newNumberOfResources.put(ResourcesLight.COINS, player.getPlayerGoods().getResources().getCoins());

        return new UpdatePlayerBoard(newActivatedLeaders, newNumberOfPoints, newBonusTileIdentifier,
                newDeckLight, newNumberOfResources);
    }

    private DeckLight setupDeckLight(Player player) {
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

    private void setBoardCards() {
        List<DevelopmentCard> territories;
        List<DevelopmentCard> buildings;
        List<DevelopmentCard> characters;
        List<DevelopmentCard> ventures;
        territories = getParticularCardsList(GeneralColor.GREEN);
        buildings = getParticularCardsList(GeneralColor.YELLOW);
        characters = getParticularCardsList(GeneralColor.BLUE);
        ventures = getParticularCardsList(GeneralColor.PURPLE);
        setTowerCards(territories, GeneralColor.GREEN);
        setTowerCards(buildings, GeneralColor.YELLOW);
        setTowerCards(characters, GeneralColor.BLUE);
        setTowerCards(ventures, GeneralColor.PURPLE);
    }

    private UpdateGameBoard setupUpdateGameBoard() {
        UpdateGameBoard updateGameBoard = new UpdateGameBoard();
        updateGameBoard.setNewGreenTower(copyTowersInformation(board.getTowers().get(0).getTowerSlots()));
        for (TowerSlotLight towerSlotLight : updateGameBoard.getNewGreenTower()) {
            System.out.println(towerSlotLight.getCard().getName() + " " + towerSlotLight.getSlotLight().getBoardIdentifier());
        }

        updateGameBoard.setNewYellowTower(copyTowersInformation(board.getTowers().get(1).getTowerSlots()));

        updateGameBoard.setNewBlueTower(copyTowersInformation(board.getTowers().get(2).getTowerSlots()));

        updateGameBoard.setNewPurpleTower(copyTowersInformation(board.getTowers().get(3).getTowerSlots()));

        updateGameBoard.setNewProduction(copyProductionHarvestSpaces(board.getBoardActionSpaces().getProductionArea()));

        updateGameBoard.setNewHarvest(copyProductionHarvestSpaces(board.getBoardActionSpaces().getHarvestArea()));

        List<SlotLight> newMarket = new ArrayList<>();
        for (MarketSpace m : board.getBoardActionSpaces().getMarketArea()) {
            PlayerPawn playerPawn = m.getSpace().getPlayerPawn();
            PawnLight pawnLight = null;
            if (PawnColor.UNCOLORED != playerPawn.getPawnColor()) {
                pawnLight = new PawnLight(playerPawn.getPlayerDetails().getPlayerName(), playerPawn.getPawnColor(), true);
            }
            newMarket.add(new SlotLight(m.getSpace().getBoardIdentifier(), m.getSpace().getRequestedValue(), pawnLight));
        }
        updateGameBoard.setNewMarket(newMarket);

        List<PawnLight> pawnLightList = new ArrayList<>();
        for (PlayerPawn playerPawn : board.getCouncilPalace().getPlayerPawnList()) {
            pawnLightList.add(new PawnLight(playerPawn.getPlayerDetails().getPlayerName(), playerPawn.getPawnColor(), true));
        }
        updateGameBoard.getNewCouncilPalaceLight().setPawnLightList(pawnLightList);

        List<PlayerLight> newPlayerLights = new ArrayList<>();
        for (Player player : players) {
            newPlayerLights.add(new PlayerLight(player.getPlayerDetails().getPlayerName(), player.getPlayerDetails().getPlayerColor()));
        }
        updateGameBoard.setNewPlayerLights(newPlayerLights);

        List<DiceLight> newDiceLightList = new ArrayList<>();
        for (Dice d : board.getDices()) {
            newDiceLightList.add(new DiceLight(d.getDiceColor(), d.getValue()));
        }
        updateGameBoard.setDiceLightList(newDiceLightList);

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
            }
            towerSlotLightList.add(towerSlotLight);
        }
        return towerSlotLightList;
    }

    private List<SlotLight> copyProductionHarvestSpaces(List<ProductionHarvestSpace> productionHarvestSpaceList) {
        List<SlotLight> slotLightList = new ArrayList<>();
        for (ProductionHarvestSpace s : productionHarvestSpaceList) {
            PlayerPawn playerPawn = s.getSpace().getPlayerPawn();
            PawnLight pawnLight = null;
            if (PawnColor.UNCOLORED != playerPawn.getPawnColor()) {
                pawnLight = new PawnLight(playerPawn.getPlayerDetails().getPlayerName(), playerPawn.getPawnColor(), true);
            }
            slotLightList.add(new SlotLight(s.getSpace().getBoardIdentifier(), s.getSpace().getRequestedValue(), pawnLight));
        }
        return slotLightList;
    }

    private List<DevelopmentCard> getParticularCardsList(GeneralColor color) {
        return developmentCards.stream()
                .filter(card -> color.equals(card.getCardInformation().getCardColor()))
                .collect(Collectors.toList());
    }

    public void setTowerCards(List<DevelopmentCard> cardsToAdd, GeneralColor color) {
        for (Tower tower : board.getTowers()) {
            if (tower.getColor() == color) {
                int i = 0;
                for (TowerSlot towerSlot : tower.getTowerSlots()) {
                    towerSlot.setDevelopmentCard(cardsToAdd.get(i));
                    i++;
                }
                break;
            }
        }
    }

    private List<Dice> extractDicesAndSetPawns() {
        List<DiceColor> colors = new ArrayList<>();

        colors.add(DiceColor.BLACK);
        colors.add(DiceColor.ORANGE);
        colors.add(DiceColor.WHITE);
        Random random = new Random();
        List<Dice> dices = new ArrayList<>();
        for (DiceColor diceColor : colors) {
            Dice dice = new Dice(diceColor, random.nextInt(5) + 1);
            dices.add(dice);
            for (Player player : players) {
                player.getPlayerBoard().getPawns().add(new Pawn(dice.getValue(),
                        PawnColor.valueOf(dice.getDiceColor().toString())));
            }
        }
        for (Player player : players) {
            player.getPlayerBoard().getPawns().add(new Pawn(0, PawnColor.NEUTRAL));
        }
        return dices;
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
        for (PlayerDetails p : treeMap.values()) {
            playersOrder.add(p);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        /*

         */
    }

    @Override
    public void visitActionDescription(BoardAction boardAction) {
        LOGGER.info("Action: board action");
        for (Player player : players) {
            if (player.getPlayerDetails().getPlayerName().equals(playersOrder.get(0).getPlayerName())) {
                System.out.println("Ci sono1");
                RequirementsGenerator requirementsGenerator =
                        new RequirementsGenerator(board, player, boardAction.getBasicAction().getBoardIdentifier());
                if (runRequirements(requirementsGenerator.generateRequirements(boardAction), player)) {
                    System.out.println("Ci sono2");
                    LOGGER.info("Action: player has requirements!");
                    BasicRewardsGenerator basicRewardsGenerator = new BasicRewardsGenerator(board);
                    basicRewardsGenerator.generateRewards(player, boardAction);
                    sendBoardsToPlayers();
                } else {
                    System.out.println("Ci sono3");
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
