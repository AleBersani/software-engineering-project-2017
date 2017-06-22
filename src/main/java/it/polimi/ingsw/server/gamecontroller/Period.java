package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.server.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * //
 */
public class Period extends Observable implements Observer {
    private ExcommunicationTile actualExcommunicationTile;
    private List<DevelopmentCard> developmentCards;

    private List<SemiPeriod> semiPeriods;
    private boolean current;

    public Period(ExcommunicationTile actualExcommunicationTile, List<DevelopmentCard> developmentCards) {
        this.actualExcommunicationTile = actualExcommunicationTile;
        this.developmentCards = developmentCards;
        semiPeriods = new ArrayList<>();
        current = false;
    }

    @Override
    public void update(Observable o, Object arg) {
        /*

         */
    }

    public void setupSemiPeriod() {
        /*
        calcolo ordine di gioco (pattern su scomunica)
         */
    }

    public void initSemiPeriod() {
        /*
        metodo
         */
    }

    public void churchSupport() {
        /*
        supporto della chiesa (command sulla update del game)
         */
    }

    public List<DevelopmentCard> getDevelopmentCardsForPeriod(PeriodNumber periodNumber) {
        /*
        ritorna la lista di dev card da mettere nel semiperiodo
         */
        return null;
    }

    public ExcommunicationTile getActualExcommunicationTile() {
        return actualExcommunicationTile;
    }

    public void setActualExcommunicationTile(ExcommunicationTile actualExcommunicationTile) {
        this.actualExcommunicationTile = actualExcommunicationTile;
    }

    public List<SemiPeriod> getSemiPeriods() {
        return semiPeriods;
    }

    public void setSemiPeriods(List<SemiPeriod> semiPeriods) {
        this.semiPeriods = semiPeriods;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }
}
