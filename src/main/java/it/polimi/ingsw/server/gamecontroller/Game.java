package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.connection.ConnectedClient;
import it.polimi.ingsw.server.gamelogic.board.Board;
import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.server.gamelogic.player.Player;

import java.util.*;

/**
 * //
 */
public class Game implements Runnable, Observer {
    private final int gameId;
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
        //board = new Board();
    }

    @Override
    public void run() {
        System.out.println("Game!");
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
