package it.polimi.ingsw.gamecontroller;

import it.polimi.ingsw.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * TODO: JavaDoc
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
        TODO
         */
    }

    public void setupSemiPeriod() {
        /*
        TODO: calcolo ordine di gioco (pattern su scomunica)
         */
    }

    public void initSemiPeriod() {
        /*
        TODO: metodo
         */
    }

    public void churchSupport() {
        /*
        TODO: supporto della chiesa (command sulla update del game)
         */
    }

    public List<DevelopmentCard> getDevelopmentCardsForPeriod(PeriodNumber periodNumber) {
        /*
        TODO: ritorna la lista di dev card da mettere nel semiperiodo
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
