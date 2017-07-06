package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.gamelogic.board.Board;
import it.polimi.ingsw.server.gamelogic.board.Dice;
import it.polimi.ingsw.server.gamelogic.board.Tower;
import it.polimi.ingsw.server.gamelogic.board.TowerSlot;
import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.server.gamelogic.enums.DiceColor;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.server.gamelogic.player.PlayerDetails;
import it.polimi.ingsw.shared.model.GeneralColor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * //
 */
public class SemiPeriod extends Observable implements Observer {
    private List<DevelopmentCard> developmentCards;
    private List<Player> players;
    private List<PlayerDetails> playersOrder;
    private List<PlayerDetails> basePlayersOrder;
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
        List<Dice> newDices;
        newDices = extractDices();
        board.setDices(newDices);
        setBoardCards();
    }

    private List<Dice> extractDices() {
        List<DiceColor> colors = new ArrayList<>();
        colors.add(DiceColor.BLACK);
        colors.add(DiceColor.ORANGE);
        colors.add(DiceColor.WHITE);
        Random random = new Random();
        List<Dice> dices = new ArrayList<>();
        for (DiceColor diceColor : colors) {
            dices.add( new Dice(diceColor, random.nextInt(5) + 1) );
        }

        return dices;
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

    private List<DevelopmentCard> getParticularCardsList(GeneralColor color) {
        return developmentCards.stream()
                               .filter(card -> color.equals(card.getCardInformation().getCardColor()))
                               .collect(Collectors.toList());
    }

    private void setTowerCards(List<DevelopmentCard> cardsToAdd, GeneralColor color) {
        Optional<Tower> optionalTowerToFill = board.getTowers().stream()
                .filter(tower -> color.equals(tower.getColor()))
                .findFirst();
        if (optionalTowerToFill.isPresent()) {
            Tower towerToFill = optionalTowerToFill.get();
            int i=0;
            for (TowerSlot towerSlot : towerToFill.getTowerSlots()) {
                towerSlot.setDevelopmentCard(cardsToAdd.get(i));
            }
        }
    }

    public void setupRound() {
        List<Player> orderedPlayerForRound = new ArrayList<>(); //COME CALCOLARLI PER TURNO?
        rounds.add(new Round(orderedPlayerForRound));
        initRound();
    }

    public void initRound() {
        /*

         */
    }

    public void endSemiPeriod() {
        /*
         */
    }

    public void calculateTotalPlayer(){
        //Calcola turno giocatori
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
