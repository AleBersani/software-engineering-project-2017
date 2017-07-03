package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.gameelements.BoardInformation;
import it.polimi.ingsw.server.gamelogic.board.*;
import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.shared.model.GeneralColor;

import java.util.*;

/**
 * //
 */
public class Period extends Observable implements Observer {
    private ExcommunicationTile actualExcommunicationTile;
    private List<DevelopmentCard> developmentCards;

    private List<SemiPeriod> semiPeriods;
    private boolean current;

    public Period(ExcommunicationTile actualExcommunicationTile, List<DevelopmentCard> developmentCards) {
        this.actualExcommunicationTile = actualExcommunicationTile;
        this.developmentCards = developmentCards;
        semiPeriods = new ArrayList<>();
        current = false;
    }

    @Override
    public void update(Observable o, Object arg) {
        /*

         */
    }

    /**
     * Public method that set a new SemiPeriod up and launches it.
     */
    public void setupSemiPeriod() {
        List<Player> previousOrderPlayers = semiPeriods.get(semiPeriods.size()-1).getPlayers();
        Board board = semiPeriods.get(semiPeriods.size()-1).getBoard();
        List<DevelopmentCard> developmentCardsForSemiPeriod = getDevelopmentCardsForSemiPeriod();
        List<Player> playersForSemiPeriod = calculatePlayerOrder(previousOrderPlayers, board);
        semiPeriods.add(new SemiPeriod(developmentCardsForSemiPeriod,
                playersForSemiPeriod,
                cleanBoard(board)));
        initSemiPeriod(semiPeriods.get(semiPeriods.size() - 1));
        /*calcolo ordine di gioco (pattern su scomunica)*/
    }

    /**
     * Private method to clean the Board of the last SemiPeriod Ended.
     * @return Cleaned Board from DevelopmentCards and player of ActionSpaces.
     */
    private Board cleanBoard(Board board) {
        List<Tower> towers;
        towers = board.getTowers();

        for (Tower T : towers)
            T.getTowerSlots().forEach((TowerSlot slot) -> {slot.setDevelopmentCard(null);
                slot.getSpace().setPlayerPawn(null);
                slot.getSpace().setAlreadyTaken(false);});

        board.getCouncilPalace().setPlayerOrder(new ArrayList<>());
        cleanBoardSpaces(board.getBoardActionSpaces());
        return board;
    }

    /**
     * Private method to clean BoardActionSpaces in the Board of the last SemiPeriod ended.
     * @param boardActionSpaces BoardActionSpaces to clean
     */
    private void cleanBoardSpaces(BoardActionSpaces boardActionSpaces) {
        boardActionSpaces.getProductionArea()
                .forEach((ProductionHarvestSpace actionSpace) -> actionSpace.getSpace()
                        .setPlayerPawn(new PlayerPawn()));
        boardActionSpaces.getHarvestArea()
                .forEach((ProductionHarvestSpace actionSpace) -> actionSpace.getSpace()
                        .setPlayerPawn(new PlayerPawn()));
        boardActionSpaces.getMarketArea()
                .forEach((MarketSpace actionSpace) -> actionSpace.getSpace()
                        .setPlayerPawn(new PlayerPawn()));
    }

    /**
     * Private method that calculates a new Player Order, based of the order of players placed on CouncilPalace.
     * @param previousOrderPlayers List of previous SemiPeriod's players order.
     * @param board Board from previous SemiPeriod, not cleaned yet, where to get new players order.
     * @return new List of Players representing new players order.
     */
    private List<Player> calculatePlayerOrder(List<Player> previousOrderPlayers, Board board) {
        List<Player> newPlayerOrder = new ArrayList<>();
        Optional<Player> playerToAdd;
        List<PlayerDetails> councilPlayerOrder = board.getCouncilPalace().getPlayerOrder();
        for (PlayerDetails playerDetails : councilPlayerOrder) {
            playerToAdd = previousOrderPlayers.stream()
                    .filter((Player player) -> player.getPlayerDetails()
                            .getPlayerName()
                            .equals(playerDetails
                                    .getPlayerName())).findFirst();
            if (playerToAdd.isPresent() && !newPlayerOrder.contains(playerToAdd.get()))
                newPlayerOrder.add(playerToAdd.get());
        }
        for (Player remainingPlayer : previousOrderPlayers) {
            if (!newPlayerOrder.contains(remainingPlayer))
                newPlayerOrder.add(remainingPlayer);
        }
        return newPlayerOrder;
    }

    /**
     * TODO
     * @param semiPeriod
     */
    public void initSemiPeriod(SemiPeriod semiPeriod) {
        /*
        metodo
         */
    }

    /**
     * TODO
     */
    public void churchSupport() {
        int i =0;

        List<Player> players;
        players = semiPeriods.get(semiPeriods.size()-1).getPlayers();
        int faithPointsRequired = BoardInformation.getFaithPointsToAvoidExcommunication()
                .get(actualExcommunicationTile.getPeriod());
        for (Player player : players) {
            if (player.getPlayerGoods().getPoints().getFaith() < faithPointsRequired)
                i++;
                //roba del visitor
            else
                i--;
            //fai scegliere al player
        }
        /*
        supporto della chiesa (command sulla update del game)
         */
    }

    /**
     * Private method that extracts randomly 16 DevelopmentCards (4 for each color) to be passed to new SemiPeriod.
     * It controls also that the cards from which extract new cards to be played, have not been placed yet.
     * If so, already played cards are removed.
     * @return A List of DevelopmentCard to be played in a new SemiPeriod.
     */
    private List<DevelopmentCard> getDevelopmentCardsForSemiPeriod() {
        final int CARDS_FOR_SEMIPERIOD = 16;
        int green=0, blue=0, purple=0, yellow=0;
        DevelopmentCard card;
        GeneralColor colorCard;
        List<DevelopmentCard> cardsToExtract = new ArrayList(developmentCards), cardsToReturn = new ArrayList<>();

        if (!semiPeriods.isEmpty())
            cardsToExtract.removeAll(semiPeriods.get(semiPeriods.size()-1).getDevelopmentCards());
        Collections.shuffle(cardsToExtract);
        for (int i = 0; i < cardsToExtract.size() && cardsToReturn.size() < CARDS_FOR_SEMIPERIOD; i++) {
            card = cardsToExtract.get(i);
            colorCard = card.getBasicDevelopmentCard().getCardInformation().getCardColor();
            if (colorCard.equals(GeneralColor.GREEN) && green < CARDS_FOR_SEMIPERIOD/4){
                cardsToReturn.add(card);
                green++;
            }
            else if (colorCard.equals(GeneralColor.YELLOW) && yellow < CARDS_FOR_SEMIPERIOD/4){
                cardsToReturn.add(card);
                yellow++;
            }
            else if (colorCard.equals(GeneralColor.BLUE) && blue < CARDS_FOR_SEMIPERIOD/4){
                cardsToReturn.add(card);
                blue++;
            }
            else if (colorCard.equals(GeneralColor.PURPLE) && purple < CARDS_FOR_SEMIPERIOD/4){
                cardsToReturn.add(card);
                purple++;
            }
        }
        return cardsToReturn;
    }

    public ExcommunicationTile getActualExcommunicationTile() {
        return actualExcommunicationTile;
    }

    public void setActualExcommunicationTile(ExcommunicationTile actualExcommunicationTile) {
        this.actualExcommunicationTile = actualExcommunicationTile;
    }

    public List<SemiPeriod> getSemiPeriods() {
        return semiPeriods;
    }

    public void setSemiPeriods(List<SemiPeriod> semiPeriods) {
        this.semiPeriods = semiPeriods;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
