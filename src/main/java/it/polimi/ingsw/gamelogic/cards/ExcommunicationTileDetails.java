package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.enums.PeriodEnum;

public class ExcommunicationTileDetails {
    private String excommunicationTileName;
    private PeriodEnum period;

    public String getExcommunicationTileName() {
        return excommunicationTileName;
    }

    public void setExcommunicationTileName(String excommunicationTileName) {
        this.excommunicationTileName = excommunicationTileName;
    }

    public PeriodEnum getPeriod() {
        return period;
    }

    public void setPeriod(PeriodEnum period) {
        this.period = period;
    }
}
