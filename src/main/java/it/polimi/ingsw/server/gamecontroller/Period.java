package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.gamecontroller.helpers.Sender;
import it.polimi.ingsw.server.gameelements.BoardInformation;
import it.polimi.ingsw.server.gamelogic.board.Board;
import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.shared.model.GeneralColor;

import java.util.*;
import java.util.logging.Logger;

public class Period extends Observable implements Observer {
    private final static Logger LOGGER = Logger.getLogger(Period.class.getName());

    private ExcommunicationTile actualExcommunicationTile;
    private List<DevelopmentCard> developmentCards;

    private List<ConnectedClient> connectedClients;
    private List<Player> players;
    private List<PlayerDetails> playersOrder;
    private Board board;
    private boolean current;
    private List<SemiPeriod> semiPeriods;
    private PeriodNumber periodNumber;

    public Period(ExcommunicationTile actualExcommunicationTile,
                  List<DevelopmentCard> developmentCards,
                  PeriodNumber periodNumber) {
        this.actualExcommunicationTile = actualExcommunicationTile;
        this.developmentCards = developmentCards;
        this.periodNumber = periodNumber;
        connectedClients = new ArrayList<>();
        players = new ArrayList<>();
        playersOrder = new ArrayList<>();
        board = new Board();
        current = false;
        semiPeriods = new ArrayList<>();
    }

    /**
     * Method that set a new SemiPeriod and launches it.
     */
    public void startSemiPeriod() {
        SemiPeriod semiPeriod = new SemiPeriod(getDevelopmentCardsForSemiPeriod(), players, new Board(board));
        semiPeriod.setConnectedClients(connectedClients);
        semiPeriod.setCurrent(true);
        semiPeriods.add(semiPeriod);

        if (semiPeriods.isEmpty()) {
            semiPeriod.setBasePlayersOrder(playersOrder);
        } else {
            getSecondLastSemiPeriod().setCurrent(false);
            semiPeriod.setBasePlayersOrder(calculateNewPlayerOrder(getSecondLastSemiPeriod().getBasePlayersOrder(),
                    getSecondLastSemiPeriod().getBoard().getCouncilPalace().getPlayerOrder()));
        }

        semiPeriod.addObserver(this);

        semiPeriod.initSemiPeriod();
    }

    /**
     * Method that extracts randomly 16 DevelopmentCards (4 for each color) to be passed to new SemiPeriod.
     * It controls also that the cards from which extract new cards to be played, have not been placed yet.
     * If so, already played cards are removed.
     * @return A List of DevelopmentCard to be played in a new SemiPeriod.
     */
    private List<DevelopmentCard> getDevelopmentCardsForSemiPeriod() {
        LOGGER.info("Extract development cards for semi period");
        final int CARDS_FOR_SEMI_PERIOD = 16;
        final int CARDS_FOR_TOWER = 4;
        int green = 0;
        int blue = 0;
        int purple = 0;
        int yellow = 0;
        DevelopmentCard card;
        GeneralColor colorCard;
        List<DevelopmentCard> cardsToExtract = new ArrayList<>(developmentCards);
        List<DevelopmentCard> cardsToReturn = new ArrayList<>();

        Collections.shuffle(cardsToExtract);
        for (int i = 0; i < cardsToExtract.size() && cardsToReturn.size() < CARDS_FOR_SEMI_PERIOD; i++) {
            card = cardsToExtract.get(i);
            colorCard = card.getBasicDevelopmentCard().getCardInformation().getCardColor();
            if (colorCard.equals(GeneralColor.GREEN) && green < CARDS_FOR_SEMI_PERIOD / CARDS_FOR_TOWER){
                cardsToReturn.add(card);
                green++;
            }
            else if (colorCard.equals(GeneralColor.YELLOW) && yellow < CARDS_FOR_SEMI_PERIOD / CARDS_FOR_TOWER){
                cardsToReturn.add(card);
                yellow++;
            }
            else if (colorCard.equals(GeneralColor.BLUE) && blue < CARDS_FOR_SEMI_PERIOD / CARDS_FOR_TOWER){
                cardsToReturn.add(card);
                blue++;
            }
            else if (colorCard.equals(GeneralColor.PURPLE) && purple < CARDS_FOR_SEMI_PERIOD / CARDS_FOR_TOWER){
                cardsToReturn.add(card);
                purple++;
            }
        }

        developmentCards.removeAll(cardsToReturn);
        return cardsToReturn;
    }

    /**
     * TODO: JavaDoc + Test
     * @return
     */
    public SemiPeriod getSecondLastSemiPeriod() {
        return semiPeriods.get(semiPeriods.size() - 2);
    }

    /**
     * TODO: JavaDoc + Test
     * @param basePlayersOrder
     * @param councilPlayerOrder
     * @return
     */
    public List<PlayerDetails> calculateNewPlayerOrder(List<PlayerDetails> basePlayersOrder,
                                                       List<PlayerDetails> councilPlayerOrder) {
        List<PlayerDetails> newPlayerOrder = new ArrayList<>();
        newPlayerOrder.addAll(councilPlayerOrder);
        for (PlayerDetails remainingPlayer : basePlayersOrder) {
            if (!newPlayerOrder.contains(remainingPlayer))
                newPlayerOrder.add(remainingPlayer);
        }
        return newPlayerOrder;
    }

    @Override
    public void update(Observable o, Object arg) {
        LOGGER.info("Starting new Semiperiod...");
        startSemiPeriod();
    }

    public void churchSupport() {
        Sender sender = new Sender(connectedClients);
        for (Player player : players) {
            if (player.getPlayerGoods().getPoints().getFaith() < BoardInformation
                    .getFaithPointsToAvoidExcommunication().get(periodNumber)) {
                //sender.sendTo(player.getPlayerDetails().getPlayerName(), new );
            } else {
                //
            }
        }
    }

    /*
        GETTERS AND SETTERS
     */

    public SemiPeriod getLastSemiperiod() {
        return semiPeriods.get(semiPeriods.size() - 1);
    }

    public ExcommunicationTile getActualExcommunicationTile() {
        return actualExcommunicationTile;
    }

    public void setActualExcommunicationTile(ExcommunicationTile actualExcommunicationTile) {
        this.actualExcommunicationTile = actualExcommunicationTile;
    }

    public List<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public void setDevelopmentCards(List<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
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

    public List<PlayerDetails> getPlayersOrder() {
        return playersOrder;
    }

    public void setPlayersOrder(List<PlayerDetails> playersOrder) {
        this.playersOrder = playersOrder;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public List<SemiPeriod> getSemiPeriods() {
        return semiPeriods;
    }

    public void setSemiPeriods(List<SemiPeriod> semiPeriods) {
        this.semiPeriods = semiPeriods;
    }

    public PeriodNumber getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(PeriodNumber periodNumber) {
        this.periodNumber = periodNumber;
    }
}
