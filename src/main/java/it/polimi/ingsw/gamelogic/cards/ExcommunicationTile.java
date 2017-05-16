package it.polimi.ingsw.gamelogic.cards;

public class ExcommunicationTile {
    private ExcommunicationTileDetails excommunicationTileDetails;
    /*
    TODO: add the instantiated decorator and then refactor Constructor
     */

    public ExcommunicationTile(ExcommunicationTileDetails excommunicationTileDetails) {
        this.excommunicationTileDetails = excommunicationTileDetails;
    }

    public ExcommunicationTileDetails getExcommunicationTileDetails() {
        return excommunicationTileDetails;
    }

    public void setExcommunicationTileDetails(ExcommunicationTileDetails excommunicationTileDetails) {
        this.excommunicationTileDetails = excommunicationTileDetails;
    }
}
