package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.gamelogic.board.Board;
import it.polimi.ingsw.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.gamelogic.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * //
 */
public class SemiPeriod extends Observable implements Observer {
    private List<DevelopmentCard> developmentCards;
    private List<Player> players;
    private Board board;

    private List<Round> rounds;
    private boolean current;

    public SemiPeriod(List<DevelopmentCard> developmentCards, List<Player> players, Board board) {
        this.developmentCards = developmentCards;
        this.players = players;
        this.board = board;
        rounds = new ArrayList<>();
        current = false;
    }

    @Override
    public void update(Observable o, Object arg) {
        /*

         */
    }

    public void setupBoard() {

    }

    public void setupRound() {
        /*

         */
    }

    public void initRound() {
        /*

         */
    }

    public void endSemiPeriod() {
        /*
        calcola turno giocatori
         */
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

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
