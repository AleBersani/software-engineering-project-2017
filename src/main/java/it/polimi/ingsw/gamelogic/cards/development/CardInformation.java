package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;

/**
 * Class that describes the basic information of each Development card
 */
public class CardInformation {
    private int number;
    private String name;
    private PeriodNumber period;
    private GeneralColor cardColor;

    public CardInformation(int number, String name, PeriodNumber period, GeneralColor cardColor) {
        this.number = number;
        this.name = name;
        this.period = period;
        this.cardColor = cardColor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PeriodNumber getPeriod() {
        return period;
    }

    public void setPeriod(PeriodNumber period) {
        this.period = period;
    }

    public GeneralColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(GeneralColor cardColor) {
        this.cardColor = cardColor;
    }
}
