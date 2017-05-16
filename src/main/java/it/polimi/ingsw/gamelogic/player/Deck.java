package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.cards.ExcommunicationTile;
import it.polimi.ingsw.gamelogic.cards.LeaderCard;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<LeaderCard> leaderCards;
    private List<ExcommunicationTile> excommunicationTiles;

    public Deck() {
        leaderCards = new ArrayList<>();
        excommunicationTiles = new ArrayList<>();
    }

    public List<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public void setLeaderCards(List<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }

    public List<ExcommunicationTile> getExcommunicationTiles() {
        return excommunicationTiles;
    }

    public void setExcommunicationTiles(List<ExcommunicationTile> excommunicationTiles) {
        this.excommunicationTiles = excommunicationTiles;
    }
}
