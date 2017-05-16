package it.polimi.ingsw.gamelogic.cards;

public class ExcommunicationTile {

    private ExcommunicationTileDetails excommunicationTileDetails;
    private String decoratorIdentifier;
    private String decoratorBehaviour;

    public ExcommunicationTileDetails getExcommunicationTileDetails() {
        return excommunicationTileDetails;
    }

    public void setExcommunicationTileDetails(ExcommunicationTileDetails excommunicationTileDetails) {
        this.excommunicationTileDetails = excommunicationTileDetails;
    }

    public String getDecoratorIdentifier() {
        return decoratorIdentifier;
    }

    public void setDecoratorIdentifier(String decoratorIdentifier) {
        this.decoratorIdentifier = decoratorIdentifier;
    }

    public String getDecoratorBehaviour() {
        return decoratorBehaviour;
    }

    public void setDecoratorBehaviour(String decoratorBehaviour) {
        this.decoratorBehaviour = decoratorBehaviour;
    }
}
