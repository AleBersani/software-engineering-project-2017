package it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles;

import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;

import java.util.Objects;

/**
 * Class that describes the basic information of the Excommunication Tiles
 */
public class ExcommunicationTile {
    private String excommunicationTileName;
    private PeriodNumber period;

    public ExcommunicationTile(String excommunicationTileName, PeriodNumber period) {
        this.excommunicationTileName = excommunicationTileName;
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ExcommunicationTile that = (ExcommunicationTile) o;
        return Objects.equals(getExcommunicationTileName(), that.getExcommunicationTileName()) &&
                getPeriod() == that.getPeriod();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExcommunicationTileName(), getPeriod());
    }

    public String getExcommunicationTileName() {
        return excommunicationTileName;
    }

    public void setExcommunicationTileName(String excommunicationTileName) {
        this.excommunicationTileName = excommunicationTileName;
    }

    public PeriodNumber getPeriod() {
        return period;
    }

    public void setPeriod(PeriodNumber period) {
        this.period = period;
    }
}
