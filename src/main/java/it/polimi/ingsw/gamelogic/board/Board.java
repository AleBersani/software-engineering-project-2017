package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.gamelogic.cards.excommunicationtiles.ExcommunicationTileDetails;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderInformation;
import it.polimi.ingsw.gamelogic.enums.DiceColor;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: JavaDoc
 */
public class Board {
    private List<Tower> towers;
    private CouncilPalace councilPalace;
    private BoardActionSpaces boardActionSpaces;

    private List<ExcommunicationTileDetails> excommunicationTiles;
    private List<Dice> dices;
    private List<LeaderInformation> leaderInformationList;

    public Board(List<Tower> towers, CouncilPalace councilPalace, BoardActionSpaces boardActionSpaces) {
        this.towers = towers;
        this.councilPalace = councilPalace;
        this.boardActionSpaces = boardActionSpaces;
        excommunicationTiles = new ArrayList<>();
        dices = new ArrayList<>();
        leaderInformationList = new ArrayList<>();
    }

    /*
        TODO: auxiliaries methods
     */

    /**
     * Get Excommunication Tile by passing the number of the period as parameter
     * @param periodNumber
     * @return selected Excommunication Tile
     */
    public ExcommunicationTile getExcommunicationTileGivenPeriod(PeriodNumber periodNumber) {
        // TODO
        return null;
        /*
        return excommunicationTiles.stream()
                .filter(e -> e.equals(periodNumber))
                .findFirst()
                .get();*/
    }

    /**
     * Methods to get the value associated to a specific coloured Dice
     * @param color type of color
     * @return value associated to a specific coloured Dice
     */
    public int getDiceValueGivenColor(DiceColor color) {
        return dices.stream()
                .filter(e -> e.equals(color))
                .findFirst()
                .get()
                .getValue();
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public void setTowers(List<Tower> towers) {
        this.towers = towers;
    }

    public CouncilPalace getCouncilPalace() {
        return councilPalace;
    }

    public void setCouncilPalace(CouncilPalace councilPalace) {
        this.councilPalace = councilPalace;
    }

    public BoardActionSpaces getBoardActionSpaces() {
        return boardActionSpaces;
    }

    public void setBoardActionSpaces(BoardActionSpaces boardActionSpaces) {
        this.boardActionSpaces = boardActionSpaces;
    }

    public List<ExcommunicationTileDetails> getExcommunicationTiles() {
        return excommunicationTiles;
    }

    public void setExcommunicationTiles(List<ExcommunicationTileDetails> excommunicationTiles) {
        this.excommunicationTiles = excommunicationTiles;
    }

    public List<Dice> getDices() {
        return dices;
    }

    public void setDices(List<Dice> dices) {
        this.dices = dices;
    }

    public List<LeaderInformation> getLeaderInformationList() {
        return leaderInformationList;
    }

    public void setLeaderInformationList(List<LeaderInformation> leaderInformationList) {
        this.leaderInformationList = leaderInformationList;
    }
}
