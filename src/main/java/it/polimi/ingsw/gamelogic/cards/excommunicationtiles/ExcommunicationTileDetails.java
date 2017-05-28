package it.polimi.ingsw.gamelogic.cards.excommunicationtiles;

import it.polimi.ingsw.gamelogic.enums.ExcommunicationContext;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;

public class ExcommunicationTileDetails {
    private String excommunicationTileName;
    private PeriodNumber period;
    private ExcommunicationContext excommunicationContext;

    public ExcommunicationTileDetails(String excommunicationTileName, PeriodNumber period, ExcommunicationContext excommunicationContext) {
        this.excommunicationTileName = excommunicationTileName;
        this.period = period;
        this.excommunicationContext = excommunicationContext;
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

    public ExcommunicationContext getExcommunicationContext() {
        return excommunicationContext;
    }

    public void setExcommunicationContext(ExcommunicationContext excommunicationContext) {
        this.excommunicationContext = excommunicationContext;
    }
}
