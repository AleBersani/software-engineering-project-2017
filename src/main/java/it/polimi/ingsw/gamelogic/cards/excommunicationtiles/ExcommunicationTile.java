package it.polimi.ingsw.gamelogic.cards.excommunicationtiles;

/**
 * TODO: JavaDoc
 */
public class ExcommunicationTile<T> {
    private ExcommunicationTileDetails excommunicationTileDetails;
    private T decorator;

    public ExcommunicationTile(ExcommunicationTileDetails excommunicationTileDetails, T decorator) {
        this.excommunicationTileDetails = excommunicationTileDetails;
        this.decorator = decorator;
    }

    public ExcommunicationTileDetails getExcommunicationTileDetails() {
        return excommunicationTileDetails;
    }

    public void setExcommunicationTileDetails(ExcommunicationTileDetails excommunicationTileDetails) {
        this.excommunicationTileDetails = excommunicationTileDetails;
    }

    public T getDecorator() {
        return decorator;
    }

    public void setDecorator(T decorator) {
        this.decorator = decorator;
    }
}
