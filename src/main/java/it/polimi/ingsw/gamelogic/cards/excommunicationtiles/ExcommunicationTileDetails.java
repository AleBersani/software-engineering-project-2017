package it.polimi.ingsw.gamelogic.cards.excommunicationtiles;

import it.polimi.ingsw.gamelogic.enums.PeriodNumber;

public class ExcommunicationTileDetails {
    private String excommunicationTileName;
    private PeriodNumber period;

    public ExcommunicationTileDetails(String excommunicationTileName, PeriodNumber period) {
        this.excommunicationTileName = excommunicationTileName;
        this.period = period;
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
